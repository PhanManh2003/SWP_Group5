/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller.Student;

import DAO.CourseDAO;
import DAO.StudentDAO;
import DAO.StudentLessionDAO;
import Model.Course;
import Model.Student;
import Model.StudentLession;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/progress")
public class ProgressController extends HttpServlet {
    private StudentLessionDAO studentLessionDAO;

    @Override
    public void init() {
        studentLessionDAO = new StudentLessionDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "viewProgress";
        }
        HttpSession session = request.getSession();
        int studentId = session.getAttribute("studentID") == null ? -1 : (int) session.getAttribute("studentID");
        StudentDAO studentDao = new StudentDAO();
        Student studentLogin = studentDao.getStudent(studentId);

        if (session.getAttribute("studentID") == null || studentLogin == null) {
            response.sendRedirect("LoginController?error=Your account can not login here");
            return;
        }
        try {
             viewProgress(request, response, studentId);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    private void viewProgress(HttpServletRequest request, HttpServletResponse response, int studentId)
            throws SQLException, ServletException, IOException {
        CourseDAO courseDao = new CourseDAO();
        List<Course> completedLessions = courseDao.getCourseByStudent(studentId);
        request.setAttribute("completedLessions", completedLessions);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/student/progress/list.jsp");
        dispatcher.forward(request, response);
    }
}
