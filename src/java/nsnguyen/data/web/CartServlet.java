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
import nsnguyen.data.model.Product;

/**
 *
 * @author ADMIN
 */
public class CartServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CartServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CartServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getParameter("clear") !=null) request.getSession().setAttribute("cart", new ArrayList<>());
          request.setAttribute("title", "Cart Datail");
       request.getRequestDispatcher("./Views/cart.jsp").include(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        updateDelete(request,response);
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    void updateDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    String action = request.getParameter("action");
    int id_product = Integer.parseInt(request.getParameter("id_product"));
    switch(action){
        case"update":
            doUpdate(request, id_product); break;
        case"delete":
            doDelete(request, id_product); break;
     }
    request.getRequestDispatcher("./Views/cart.jsp").include(request, response);
    }
   void doUpdate(HttpServletRequest request, int id_product){
   List<Product> cart = (List<Product>)request.getSession().getAttribute("cart");
   int quantity = Integer.parseInt(request.getParameter("quantity"));
   for(Product pro: cart)
       if(pro.getId()==id_product && quantity>0){
          pro.setQuantity(quantity); break;
       }
   request.getSession().setAttribute("cart", cart);
   }
   void doDelete(HttpServletRequest request, int id_product){
   List<Product> cart = (List<Product>)request.getSession().getAttribute("cart");
   for(Product pro: cart)
       if(pro.getId()==id_product){
          cart.remove(pro); break;
       }
   request.getSession().setAttribute("cart", cart);
   }
}