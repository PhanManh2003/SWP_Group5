/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Admin.Course;

import DAO.CourseDAO;
import Model.Course;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class EditCourseController extends HttpServlet {

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
            out.println("<title>Servlet EditCourseController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditCourseController at " + request.getContextPath() + "</h1>");
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
        int courseID = Integer.parseInt(request.getParameter("courseID"));
        CourseDAO courseDAO = new CourseDAO();
        Course course = courseDAO.getCourse(courseID);
        request.setAttribute("course", course);
        RequestDispatcher dispatcher = request.getRequestDispatcher("./view/admin/course/edit.jsp");
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
        int courseID = 0;
        try {
            courseID = Integer.parseInt(request.getParameter("courseID").trim());
        }catch(Exception e) {
            response.sendRedirect("ListCourseController?error=Can not found course");
            return;
        }
        String courseName = request.getParameter("courseName").trim();
        String courseCode = request.getParameter("courseCode").trim();
        int status = Integer.parseInt(request.getParameter("status").trim());

        if (courseName == null || courseCode == null || courseName.isEmpty() || courseCode.isEmpty()) {
            response.sendRedirect("EditCourseController?courseID=" + courseID + "&error=Please fill all field");
            return;
        }

        CourseDAO courseDAO = new CourseDAO();
        if (courseDAO.isCourseCodeExists(courseCode, courseID)) {
            response.sendRedirect("EditCourseController?courseID=" + courseID + "&error=Code is exist");
            return;
        }

        Course course = new Course();
        course.setCourseID(courseID);
        course.setCourseName(courseName);
        course.setCourseCode(courseCode);
        course.setStatus(status);

        courseDAO.updateCourse(course);

        response.sendRedirect("ListCourseController?success=Edit successfully");
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
