/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Admin.Teacher;

import DAO.AdminDAO;
import DAO.StudentDAO;
import DAO.TeacherDAO;
import Model.Admin;
import Model.Teacher;
import Utils.Validation;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author HP
 */
public class AddTeacherController extends HttpServlet {

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
            out.println("<title>Servlet AddTeacherController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddTeacherController at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        if(session.getAttribute("adminLogin") == null) {
            response.sendRedirect("LoginController?error=Your account can not login here");
            return;
        }
        request.getRequestDispatcher("./view/admin/teacher/add.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        Admin admin = (Admin)session.getAttribute("adminLogin");
        if (admin == null) {
            response.sendRedirect("LoginController?error=Your account can not login here");
            return;
        }
        String name = request.getParameter("name").trim();
        String email = request.getParameter("email").trim();
        String phone = request.getParameter("phone").trim();
        String statusStr = request.getParameter("status").trim();

        if (name == null || email == null || phone == null || statusStr == null
                || name.isEmpty() || email.isEmpty() || phone.isEmpty() || statusStr.isEmpty()) {
            response.sendRedirect("AddTeacherController?error=Please fill all field");
            return;
        }

        int status;
        Validation validate = new Validation();
        try {
            status = Integer.parseInt(statusStr);
        } catch (NumberFormatException e) {
            response.sendRedirect("AddTeacherController?error=Status is not valid");
            return;
        }

        if (!validate.isValidEmail(email)) {
            response.sendRedirect("AddTeacherController?error=Email is not valid");
            return;
        }

        if (!validate.isValidPhoneNumber(phone)) {
            response.sendRedirect("AddTeacherController?error=Phone is not valid");
            return;
        }
        TeacherDAO teacherDAO = new TeacherDAO();
        if (teacherDAO.isEmailExists(email, 0)) {
            response.sendRedirect("AddTeacherController?error=Email is exist");
            return;
        }
        AdminDAO adminDao = new AdminDAO();
        if (adminDao.isEmailExists(email, 0)) {
            response.sendRedirect("AddTeacherController?error=Email is exist in system");
            return;
        }
        StudentDAO studentDao = new StudentDAO();
        if (studentDao.isEmailExists(email, 0)) {
            response.sendRedirect("AddTeacherController?error=Email is exist in system");
            return;
        }
        if (teacherDAO.isPhoneExists(phone, 0)) {
            response.sendRedirect("AddTeacherController?error=Phone is exist");
            return;
        }

        Teacher teacher = new Teacher();
        teacher.setName(name);
        teacher.setEmail(email);
        teacher.setPhone(phone);
        teacher.setStatus(status);
        teacher.setAdminID(admin.getId());
        teacherDAO.addTeacher(teacher);
        response.sendRedirect("ListTeachersController?success=Add new successs");
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
