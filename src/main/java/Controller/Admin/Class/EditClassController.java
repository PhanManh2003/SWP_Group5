/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Admin.Class;

import DAO.ClassDAO;
import DAO.CourseDAO;
import DAO.SemesterDAO;
import DAO.TeacherDAO;
import Model.ClassInfo;
import Model.Course;
import Model.Semester;
import Model.Teacher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author HP
 */
@WebServlet(name = "EditClassController", urlPatterns = {"/EditClassController"})
public class EditClassController extends HttpServlet {

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
            out.println("<title>Servlet EditClassController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditClassController at " + request.getContextPath() + "</h1>");
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
        int classID = Integer.parseInt(request.getParameter("classID"));
        ClassDAO classDao = new ClassDAO();
        ClassInfo classInfo = classDao.getClass(classID);
        if (classInfo != null) {
            TeacherDAO teacherDAO = new TeacherDAO();
            CourseDAO courseDAO = new CourseDAO();
            SemesterDAO semesterDAO = new SemesterDAO();

            List<Teacher> teachers = teacherDAO.getAllTeachers();
            List<Course> courses = courseDAO.getAllCourses();
            List<Semester> semesters = semesterDAO.getAllSemesters();

            request.setAttribute("semesters", semesters);
            request.setAttribute("teachers", teachers);
            request.setAttribute("courses", courses);
            request.setAttribute("classInfo", classInfo);
            request.getRequestDispatcher("./admin/class/edit.jsp").forward(request, response);
        } else {
            response.sendRedirect("ListClassController?error=Class can not fount");
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
        int classID = 0;
        try {
            classID = Integer.parseInt(request.getParameter("classID").trim());
        } catch (Exception e) {
            response.sendRedirect("ListClassController?error=Class can not found");
            return;
        }
        String className = request.getParameter("className");
        int status = Integer.parseInt(request.getParameter("status"));
        int teacherID = Integer.parseInt(request.getParameter("teacherID"));
        int courseID = Integer.parseInt(request.getParameter("courseID"));
        int semesterID = Integer.parseInt(request.getParameter("semesterID"));

        if (className == null || className.trim().isEmpty()) {
            response.sendRedirect("EditClassController?classID=" + classID + "&error=Please fill all filed");
            return;
        }

        ClassDAO classDao = new ClassDAO();
        if (classDao.getExistClass(classID, className)) {
            response.sendRedirect("EditClassController?classID=" + classID + "&error=Class name is exist");
            return;
        }

        ClassInfo classInfo = new ClassInfo();
        classInfo.setClassID(classID);
        classInfo.setClassName(className);
        classInfo.setStatus(status);
        classInfo.setTeacherID(teacherID);
        classInfo.setCourseID(courseID);
        classInfo.setSemesterID(semesterID);

        int result = classDao.updateClass(classInfo);

        if (result > 0) {
            response.sendRedirect("ListClassController?success=Update successfully");
        } else {
            response.sendRedirect("EditClassController?classID=" + classID + "&error=Update fail");
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
