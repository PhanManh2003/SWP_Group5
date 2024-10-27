/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Admin.Student;

import DAO.StudentDAO;
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
@WebServlet(name = "EditStudentController", urlPatterns = {"/EditStudentController"})
public class EditStudentController extends HttpServlet {

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
            out.println("<title>Servlet EditStudentController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditStudentController at " + request.getContextPath() + "</h1>");
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
        int studentID = Integer.parseInt(request.getParameter("studentID"));

        StudentDAO studentDAO = new StudentDAO();
        Student student = studentDAO.getStudent(studentID);

        if (student != null) {
            request.setAttribute("student", student);
            request.getRequestDispatcher("./admin/student/edit.jsp").forward(request, response);
        } else {
            response.sendRedirect("ListStudentsController?error=Can not found this student");
        }
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
        int studentID = 0;
        try {
            studentID = Integer.parseInt(request.getParameter("studentID").trim());
        }catch(Exception e) {
            response.sendRedirect("ListStudentsController?error=Can not found student");
            return;
        }
        String name = request.getParameter("name").trim();
        String email = request.getParameter("email").trim();
        String phone = request.getParameter("phone").trim();
        String statusStr = request.getParameter("status").trim();

        if (name == null || email == null || phone == null || statusStr == null
                || name.isEmpty() || email.isEmpty() || phone.isEmpty() || statusStr.isEmpty()) {
            response.sendRedirect("EditStudentController?studentID=" + request.getParameter("studentID") + "&error=Please fill all field");
            return;
        }

        int status;
        try {
            status = Integer.parseInt(statusStr);
        } catch (NumberFormatException e) {
            response.sendRedirect("EditStudentController?studentID=" + request.getParameter("studentID") + "&error=Status invalid");
            return;
        }
        Validation validate = new Validation();
        if (!validate.isValidEmail(email)) {
            response.sendRedirect("EditStudentController?teacherID=" + request.getParameter("studentID") + "&error=Email invalid");
            return;
        }

        if (!validate.isValidPhoneNumber(phone)) {
            response.sendRedirect("EditStudentController?studentID=" + request.getParameter("studentID") + "&error=Phone invalid");
            return;
        }
        
        StudentDAO studentDAO = new StudentDAO();
        if (studentDAO.isEmailExists(email, studentID)) {
            response.sendRedirect("EditStudentController?studentID=" + request.getParameter("studentID") + "&error=Email exist");
            return;
        }
        if (studentDAO.isPhoneExists(phone, studentID)) {
            response.sendRedirect("EditStudentController?studentID=" + request.getParameter("studentID") + "&error=Phone exist");
            return;
        }

        Student student = new Student();
        student.setStudentID(studentID);
        student.setName(name);
        student.setEmail(email);
        student.setPhone(phone);
        student.setStatus(status);

        
        studentDAO.updateStudent(student);
        response.sendRedirect("ListStudentsController?success=Update successfully");
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
