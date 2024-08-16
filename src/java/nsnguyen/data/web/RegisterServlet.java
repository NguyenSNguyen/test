/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package nsnguyen.data.web;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import nsnguyen.data.dao.Database;
import nsnguyen.data.model.User;
import nsnguyen.data.utils.API;

/**
 *
 * @author nsngu
 */
public class RegisterServlet extends HttpServlet {


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("title", "Register Page");
        request.getRequestDispatcher("./Views/register.jsp").include(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String err_email="",err_phone="",err_repassword="";
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");
        String Email_Regex = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
        String Phone_Regex="^\\d{10}$";
        boolean err = false;
        if(!email.matches(Email_Regex)){
            err_email = "Email is invalid!";
            request.getSession().setAttribute("err_email", err_email);
            err=true;
        }else{
            err_email="";
            request.getSession().removeAttribute("err_email");
        }
        if(!phone.matches(Phone_Regex)){
            err_phone = "Phone has 10 digits";
            request.getSession().setAttribute("err_phone", err_phone);
            err=true;
        }else{
            err_phone="";
            request.getSession().removeAttribute("err_phone");
        }
        if(!repassword.matches(password)){
            err_repassword = "Not match password!";
            request.getSession().setAttribute("err_repassword", err_repassword);
            err=true;
        }else{
            err_repassword="";
            request.getSession().removeAttribute("err_repassword");
        }
        if(err){
            response.sendRedirect("register");
        } else{
            if(Database.getUserDao().findUser(email) != null || Database.getUserDao().findUser(phone) != null){
                request.getSession().setAttribute("exist_user", "User has existed in Database!");
                response.sendRedirect("register");
            }else{
                Database.getUserDao().insertUser(name, email, phone, API.getMd5(password));
                User user = Database.getUserDao().findUser(email);
                request.getSession().setAttribute("user", user);
                request.getSession().removeAttribute("exiset_user");
                response.sendRedirect("home");
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
