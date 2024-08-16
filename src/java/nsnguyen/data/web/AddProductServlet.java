package nsnguyen.data.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import nsnguyen.data.dao.ProductDao;
import nsnguyen.data.impl.ProductImpl;

@MultipartConfig
public class AddProductServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy các tham số từ form
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String quantity = request.getParameter("quantity");
        String status = request.getParameter("status");
        String id_category = request.getParameter("id_category");
        
        // Lấy phần tử file từ request
        Part filePart = request.getPart("image");
        String fileName = "";

        if (filePart != null && filePart.getSize() > 0) {
            fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // Lấy tên file
            
            // Sử dụng đường dẫn tuyệt đối hoặc tương đối
            // Đường dẫn tuyệt đối
            // String uploadDir = "C:/Users/YourUsername/YourProject/assets/images";
            
            // Đường dẫn tương đối
            String uploadDir = System.getProperty("user.dir") + "/assets/images";
            
            File uploadFile = new File(uploadDir, fileName);

            // Tạo thư mục nếu chưa tồn tại
            if (!uploadFile.getParentFile().exists()) {
                if (!uploadFile.getParentFile().mkdirs()) {
                    throw new ServletException("Failed to create directory for file upload.");
                }
            }

            // Sao chép file vào thư mục đích
            try (InputStream input = filePart.getInputStream()) {
                Files.copy(input, uploadFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new ServletException("File upload failed", e);
            }
        } else {
            System.out.println("No file uploaded or file is empty.");
        }

        // Lưu thông tin sản phẩm vào cơ sở dữ liệu
        try {
            ProductDao productDao = new ProductImpl();
            productDao.insertProduct(name, fileName, price, quantity, status, id_category);
        } catch (Exception e) {
            throw new ServletException("Failed to insert product into database.", e);
        }

        response.sendRedirect("listProduct");
    }
}
