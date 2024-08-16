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
import java.nio.file.StandardCopyOption;
import nsnguyen.data.dao.ProductDao;
import nsnguyen.data.impl.ProductImpl;
import nsnguyen.data.model.Product;

@MultipartConfig
public class EditProductServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy các tham số từ form
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String quantity = request.getParameter("quantity");
        String status = request.getParameter("status");
        String id_category = request.getParameter("id_category");
        
        // Kiểm tra các tham số có null không
        if (id == null || name == null || price == null || quantity == null || status == null) {
            response.sendRedirect("errorPage.jsp"); // Chuyển hướng đến trang lỗi nếu có tham số null
            return;
        }

        // Lấy phần tử file từ request
        Part filePart = request.getPart("image");
        String fileName = null; // Giá trị mặc định nếu không có ảnh mới

        if (filePart != null && filePart.getSize() > 0) { // Kiểm tra nếu có ảnh mới được tải lên
            fileName = filePart.getSubmittedFileName();
            String uploadDir = getServletContext().getRealPath("") + File.separator + "assets/images";
            File uploadFile = new File(uploadDir, fileName);

            // Tạo thư mục nếu chưa tồn tại
            if (!uploadFile.getParentFile().exists()) {
                uploadFile.getParentFile().mkdirs();
            }

            // Sao chép file vào thư mục đích
            try (InputStream input = filePart.getInputStream()) {
                Files.copy(input, uploadFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new ServletException("File upload failed", e);
            }
        } else {
            // Nếu không có ảnh mới, lấy tên ảnh cũ từ cơ sở dữ liệu
            ProductDao productDao = new ProductImpl();
            Product existingProduct = productDao.findProduct(Integer.parseInt(id));
            if (existingProduct != null) {
                fileName = existingProduct.getImage(); // Giữ lại tên ảnh cũ
            }
        }

        // Cập nhật thông tin sản phẩm vào cơ sở dữ liệu
        ProductDao productDao = new ProductImpl();
        Product product = new Product(
            Integer.parseInt(id),
            Integer.parseInt(id_category), // Cập nhật id_category từ request
            name,
            fileName,
            Double.parseDouble(price),
            Integer.parseInt(quantity),
            Boolean.parseBoolean(status)
        );

        productDao.updateProduct(product);
        
        response.sendRedirect("listProduct");
    }
}
