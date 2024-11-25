/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Admin.Class;

import DAO.ClassDAO;
import DAO.CourseDAO;
import DAO.SemesterDAO;
import DAO.TeacherDAO;
import Model.Admin;
import Model.ClassInfo;
import Model.Course;
import Model.Semester;
import Model.Teacher;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author HP
 */
public class AddClassController extends HttpServlet {

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
            out.println("<title>Servlet AddClassController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddClassController at " + request.getContextPath() + "</h1>");
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
        if (session.getAttribute("adminLogin") == null) {
            response.sendRedirect("LoginController?error=Your account can not login here");
            return;
        }
        TeacherDAO teacherDAO = new TeacherDAO();
        CourseDAO courseDAO = new CourseDAO();
        SemesterDAO semesterDAO = new SemesterDAO();

        List<Teacher> teachers = teacherDAO.getAllTeachers();
        List<Course> courses = courseDAO.getAllCourses();
        List<Semester> semesters = semesterDAO.getAllSemesters();

        request.setAttribute("semesters", semesters);
        request.setAttribute("teachers", teachers);
        request.setAttribute("courses", courses);

        RequestDispatcher dispatcher = request.getRequestDispatcher("./view/admin/class/add.jsp");
        dispatcher.forward(request, response);
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
        String className = request.getParameter("className");
        int status = Integer.parseInt(request.getParameter("status"));
        int teacherID = Integer.parseInt(request.getParameter("teacherID"));
        int courseID = Integer.parseInt(request.getParameter("courseID"));
        int semesterID = Integer.parseInt(request.getParameter("semesterID"));

        if (className == null || className.trim().isEmpty()) {
            response.sendRedirect("AddClassController?error=Class name can not empty");
            return;
        }

        ClassDAO classDao = new ClassDAO();
        if (classDao.getExistClass(0, className)) {
            response.sendRedirect("AddClassController?error=Class name is exist");
            return;
        }

        ClassInfo classInfo = new ClassInfo();
        classInfo.setClassName(className);
        classInfo.setStatus(status);
        classInfo.setTeacherID(teacherID);
        classInfo.setCourseID(courseID);
        classInfo.setSemesterID(semesterID);
        classInfo.setAdminID(admin.getId());
        int result = classDao.addClass(classInfo);

        if (result > 0) {
            response.sendRedirect("ListClassController?success=Add successfully");
        } else {
            response.sendRedirect("AddClassController?error=Add fail");
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
