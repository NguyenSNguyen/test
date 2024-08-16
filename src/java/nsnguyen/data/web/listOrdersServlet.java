package nsnguyen.data.web;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import nsnguyen.data.dao.Database;
import nsnguyen.data.model.Category;
import nsnguyen.data.model.Invoice;
import nsnguyen.data.model.Product;

public class listOrdersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Category> listCategory = Database.getCategoryDao().findAll();
        request.setAttribute("listCategory", listCategory);
        
        List<Invoice> listInvoice = Database.getInvoiceDao().findAll();
        request.setAttribute("listInvoice", listInvoice);
        
        List<Product> listProduct = Database.getProductDao().findAll();
        request.setAttribute("listProduct", listProduct);
        
        request.setAttribute("title", "Home Page");
        
        // Chuyển tiếp đến JSP để hiển thị dữ liệu
        request.getRequestDispatcher("./admin/listOrders.jsp").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);  // Xử lý yêu cầu POST như GET
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
