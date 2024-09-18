/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.authen;

import dal.AccountDAO;
import entity.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AuthenController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.getWriter().println("Servlet");
//        // Get data từ form đăng nhập
//        String email = request.getParameter("email");
//        String password = request.getParameter("password");
//        Account account = new Account(0, email, password, null,
//                null, null, null);
//        // Tìm kiếm với AccountDAO
//        AccountDAO dao = new AccountDAO();
//
//        if (dao.findByLogin(account) != null) {
//            request.getRequestDispatcher("SWP_OCMS/view/homepage/home.jsp")
//                    .forward(request, response);
//        } else {
//            request.setAttribute("error", "Invalid username or password!");
//            request.getRequestDispatcher("SWP_OCMS/view/authen/login.jsp")
//                    .forward(request, response);
//        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AuthenController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AuthenController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

}
