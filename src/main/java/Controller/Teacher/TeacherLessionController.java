/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Teacher;

import DAO.ClassDAO;
import DAO.StudentDAO;
import DAO.TeacherDAO;
import Model.ClassInfo;
import Model.Student;
import Model.Teacher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author HP
 */
@WebServlet(name = "TeacherLessionController", urlPatterns = {"/TeacherLessionController"})
public class TeacherLessionController extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TeacherLessionController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TeacherLessionController at " + request.getContextPath() + "</h1>");
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
        int idTeacher = session.getAttribute("teacherID") == null ? -1 : (int) session.getAttribute("teacherID");
        TeacherDAO teacherDAO = new TeacherDAO();
        Teacher teacherLogin = teacherDAO.getTeacher(idTeacher);
        if (session.getAttribute("teacherID") == null || teacherLogin == null) {
            response.sendRedirect("LoginController?error=Your account can not login here");
            return;
        }
        ClassDAO classDao = new ClassDAO();
        if (teacherLogin != null) {
            try {
                String classId = request.getParameter("classID");
                int classIdIn = Integer.parseInt(classId);
                ClassInfo currentClass = classDao.getClasseActiveByTeacherById(classIdIn);
                if (currentClass != null) {
                    StudentDAO studentDao = new StudentDAO();
                    List<Student> students = studentDao.getAllStudentsByClass(classIdIn);
                    request.setAttribute("students", students);
                    request.setAttribute("currentClass", currentClass);
                    request.getRequestDispatcher("./view/teacher/class/view.jsp").forward(request, response);
                } else {
                    response.sendRedirect("TeacherClassController?error=Can not found class");
                }
            } catch (Exception e) {
                response.sendRedirect("TeacherClassController?error=ID class invalid");
            }

        } else {
            response.sendRedirect("LoginController");
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
        processRequest(request, response);
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
