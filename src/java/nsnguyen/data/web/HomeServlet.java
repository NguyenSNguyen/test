/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package nsnguyen.data.web;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import nsnguyen.data.dao.Database;
import nsnguyen.data.model.Category;
import nsnguyen.data.model.Product;

/**
 *
 * @author ADMIN
 */
public class HomeServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet HomeServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HomeServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       List<Category> listCategory = Database.getCategoryDao().findAll();
       request.setAttribute("listCategory", listCategory);
       List<Product> listProduct = Database.getProductDao().findAll();
       request.setAttribute("listProduct", listProduct);
       String id_category = request.getParameter("id_category");
       request.setAttribute("id_category", id_category);
        
        addProductToCart(request);
        
        request.setAttribute("title", "Home page");
       request.getRequestDispatcher("./Views/home.jsp").include(request, response);
    } 

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
      void addProductToCart(HttpServletRequest request){
      int id_product;
      try{
         id_product = Integer.parseInt(request.getParameter("id_product"));
      
      }catch(Exception e){
      id_product=0;
      }
      List<Product> cart = (List<Product>) request.getSession().getAttribute("cart");
      if(cart==null) cart = new ArrayList<>();
      if(id_product>0){
      Product product = Database.getProductDao().findProduct(id_product);
      boolean isProductInCart=false;
       for(Product pro: cart)
           if(pro.getId()==id_product){
             pro.setQuantity(pro.getQuantity()+1);
             isProductInCart=true;
           }
       if(!isProductInCart) cart.add(product);
      }
      request.getSession().setAttribute("cart", cart);
      }
}


