/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Admin.Student;

import DAO.StudentDAO;
import DAO.TeacherDAO;
import Model.Student;
import Utils.Validation;
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
@WebServlet(name = "AddStudentController", urlPatterns = {"/AddStudentController"})
public class AddStudentController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddStudentController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddStudentController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
        request.getRequestDispatcher("./admin/teacher/add.jsp").forward(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name").trim();
        String email = request.getParameter("email").trim();
        String phone = request.getParameter("phone").trim();
        String statusStr = request.getParameter("status").trim();

        if (name == null || email == null || phone == null || statusStr == null
                || name.isEmpty() || email.isEmpty() || phone.isEmpty() || statusStr.isEmpty()) {
            response.sendRedirect("AddStudentController?error=Please fill all field");
            return;
        }

        int status;
        try {
            status = Integer.parseInt(statusStr);
        } catch (NumberFormatException e) {
            response.sendRedirect("AddStudentController?error=Status is not valid");
            return;
        }
        Validation validate = new Validation();
        if (!validate.isValidEmail(email)) {
            response.sendRedirect("AddStudentController?error=Email is not valid");
            return;
        }

        if (!validate.isValidPhoneNumber(phone)) {
            response.sendRedirect("AddStudentController?error=Phone is not valid");
            return;
        }
        StudentDAO studentDAO = new StudentDAO();
        if (studentDAO.isEmailExists(email, 0)) {
            response.sendRedirect("AddStudentController?error=Email is exist");
            return;
        }
        if (studentDAO.isPhoneExists(phone, 0)) {
            response.sendRedirect("AddStudentController?error=Phone is exist");
            return;
        }

        Student student = new Student();
        student.setName(name);
        student.setEmail(email);
        student.setPhone(phone);
        student.setStatus(status);

        studentDAO.addStudent(student);

        response.sendRedirect("ListStudentsController?success=Add successfully");
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
