/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Admin.Teacher;

import DAO.TeacherDAO;
import Model.Teacher;
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
@WebServlet(name = "EditTeacherController", urlPatterns = {"/EditTeacherController"})
public class EditTeacherController extends HttpServlet {

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
            out.println("<title>Servlet EditTeacherController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditTeacherController at " + request.getContextPath() + "</h1>");
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
        int teacherID = 0;
        try {
            teacherID = Integer.parseInt(request.getParameter("teacherID"));
            TeacherDAO teacherDAO = new TeacherDAO();
            Teacher teacher = teacherDAO.getTeacher(teacherID);
            if (teacher == null) {
                response.sendRedirect("ListTeachersController?error=Can not found this teacher");
                return;
            }
            request.setAttribute("teacher", teacher);
            request.getRequestDispatcher("./view/admin/teacher/edit.jsp").forward(request, response);
        } catch (Exception e) {
            response.sendRedirect("ListTeachersController?error=Can not found this teacher");
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int teacherID = 0;
        try {
            teacherID = Integer.parseInt(request.getParameter("teacherID"));
        } catch (Exception e) {
            response.sendRedirect("ListTeachersController?error=Can not found teacher");
            return;
        }
        String name = request.getParameter("name").trim();
        String email = request.getParameter("email").trim();
        String phone = request.getParameter("phone").trim();
        String statusStr = request.getParameter("status").trim();

        if (name == null || email == null || phone == null || statusStr == null
                || name.isEmpty() || email.isEmpty() || phone.isEmpty() || statusStr.isEmpty()) {
            response.sendRedirect("EditTeacherController?teacherID=" + request.getParameter("teacherID") + "&error=Please fill all field");
            return;
        }

        int status;
        Validation validate = new Validation();

        try {
            status = Integer.parseInt(statusStr);
        } catch (NumberFormatException e) {
            response.sendRedirect("EditTeacherController?teacherID=" + request.getParameter("teacherID") + "&error=Status is not valid");
            return;
        }

        if (!validate.isValidEmail(email)) {
            response.sendRedirect("EditTeacherController?teacherID=" + request.getParameter("teacherID") + "&error=Email is not valid");
            return;
        }

        if (!validate.isValidPhoneNumber(phone)) {
            response.sendRedirect("EditTeacherController?teacherID=" + request.getParameter("teacherID") + "&error=Phone is not valid");
            return;
        }

        TeacherDAO teacherDAO = new TeacherDAO();
        if (teacherDAO.isEmailExists(email, teacherID)) {
            response.sendRedirect("EditTeacherController?teacherID=" + request.getParameter("teacherID") + "&error=Email exist");
            return;
        }
        if (teacherDAO.isPhoneExists(phone, teacherID)) {
            response.sendRedirect("EditTeacherController?teacherID=" + request.getParameter("teacherID") + "&error=Phone exist");
            return;
        }

        Teacher teacher = new Teacher();
        teacher.setTeacherID(teacherID);
        teacher.setName(name);
        teacher.setEmail(email);
        teacher.setPhone(phone);
        teacher.setStatus(status);
        teacherDAO.updateTeacher(teacher);

        response.sendRedirect("ListTeachersController?success=Update success");
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
