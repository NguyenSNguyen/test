package nsnguyen.data.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import nsnguyen.data.dao.UserDao;
import nsnguyen.data.impl.UserImpl;
import nsnguyen.data.model.User;

import java.io.IOException;

@WebServlet("/editProfile")
public class EditProfileServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy các tham số từ form
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        // Lấy thông tin người dùng từ session
        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp"); // Redirect to login page if user is not logged in
            return;
        }

        // Cập nhật thông tin người dùng
        UserDao userDao = new UserImpl();
        user.setName(name);
        user.setEmail(email);
        user.setPhone(phone);

        // Cập nhật thông tin trong cơ sở dữ liệu
        userDao.updateUser(user);

        // Cập nhật thông tin trong session
        request.getSession().setAttribute("user", user);

        // Redirect đến trang profile hoặc trang bạn muốn
        response.sendRedirect("home");
    }
}
