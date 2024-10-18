/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller.Auth;

import DAO.AdminDAO;
import Model.Admin;
import Utils.Email;
import Utils.Generate;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author HP
 */
@WebServlet(name="AdminForgetPasswordController", urlPatterns={"/AdminForgetPasswordController"})
public class AdminForgetPasswordController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AdminForgetPasswordController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminForgetPasswordController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.getRequestDispatcher("/forget.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        AdminDAO adminDao = new AdminDAO();
        String email = request.getParameter("email");
        Admin admin = adminDao.adminForget(email);
        if(admin != null) {
            if(admin.getStatus() == 1) {
                Email emailSend = new Email();
                Generate generate = new Generate();
                String newPassword = generate.generatePassword(8);
                boolean isSendMail = emailSend.sendEmail(email, "Reset password", "Your new password: " + newPassword, null);
                int index = 0;
                while(!isSendMail && index < 5) {
                    isSendMail = emailSend.sendEmail(email, "Reset password", "Your new password: " + newPassword, null);
                    index++;
                }
                if(isSendMail) {
                    int result =  adminDao.updatePassword(newPassword, admin.getId());
                    if(result > 0) {
                        response.sendRedirect("AdminForgetPasswordController?success=Reset password success check your mail to get it.");
                    } else {
                        response.sendRedirect("AdminForgetPasswordController?error=Reset password fail. Try again.");
                    }
                } else {
                    response.sendRedirect("AdminForgetPasswordController?error=Reset password fail. Try again.");
                }
            }
        } else {
            response.sendRedirect("AdminForgetPasswordController?error=Your account can not found in system.");
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
