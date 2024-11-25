/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Student;

import DAO.ClassDAO;
import DAO.LessonDAO;
import DAO.StudentClassDAO;
import DAO.StudentDAO;
import DAO.StudentLessionDAO;
import Model.ClassInfo;
import Model.Lesson;
import Model.Student;
import Model.StudentClass;
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
@WebServlet(name = "LessonStudentController", urlPatterns = {"/LessonStudentController"})
public class LessonStudentController extends HttpServlet {

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
            out.println("<title>Servlet LessionStudentController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LessionStudentController at " + request.getContextPath() + "</h1>");
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
        int studentId = session.getAttribute("studentID") == null ? -1 : (int) session.getAttribute("studentID");
        StudentDAO studentDao = new StudentDAO();
        Student studentLogin = studentDao.getStudent(studentId);

        if (session.getAttribute("studentID") == null || studentLogin == null) {
            response.sendRedirect("LoginController?error=Your account can not login here");
            return;
        }

        if (studentLogin != null) {
            String action = request.getParameter("action");
            action = action != null ? action : "";
            switch (action) {
                case "learn":
                    this.getLessionDetail(request, response, studentId);
                    break;
                default:
                    this.getAllLession(request, response, studentId);
                    break;
            }
        }
    }

// Hàm lấy chi tiết bài học
    private void getLessionDetail(HttpServletRequest request, HttpServletResponse response, int studentId)
            throws ServletException, IOException {
        try {
            int lessionId = Integer.parseInt(request.getParameter("lessionId"));
            LessonDAO lessionDao = new LessonDAO();
            Lesson lession = lessionDao.getLessionByIdAndStudentId(lessionId, studentId); 

            if (lession != null) {
                request.setAttribute("currentLession", lession);
                List<Lesson> lessions = lessionDao.getLessionsByCourseId(lession.getCourseId(), studentId);
                request.setAttribute("lessions", lessions);

                request.getRequestDispatcher("./view/student/lession/view.jsp").forward(request, response);
            } else {
                response.sendRedirect("StudentClassController?error=Lession not found");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
            response.sendRedirect("StudentClassController?error=Error while loading lesson");
        }
    }

// Hàm lấy tất cả bài học của khóa học
    private void getAllLession(HttpServletRequest request, HttpServletResponse response, int studentId)
            throws ServletException, IOException {
        try {
            ClassDAO classDao = new ClassDAO();
            LessonDAO lessionDao = new LessonDAO();
            StudentClassDAO studentClassDao = new StudentClassDAO();
            String classId = request.getParameter("classID");
            int classIdIn = Integer.parseInt(classId);
            ClassInfo currentClass = classDao.getClasseActiveByStudentById(classIdIn);

            if (currentClass != null) {
                StudentClass studentJoin = studentClassDao.getStudentsByClassIdAndStudent(classIdIn, studentId);
                if (studentJoin != null) {
                    request.setAttribute("studentJoin", studentJoin);
                    request.setAttribute("currentClass", currentClass);
                    List<Lesson> lessions = lessionDao.getLessionsByCourseId(currentClass.getCourseID(), studentId);
                    request.setAttribute("lessions", lessions);
                    request.getRequestDispatcher("./view/student/lession/list.jsp").forward(request, response);
                } else {
                    response.sendRedirect("StudentClassController?error=Can not found class");
                }
            } else {
                response.sendRedirect("StudentClassController?error=Can not found class");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

// Hàm đánh dấu bài học hoàn thành
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int studentId = session.getAttribute("studentID") == null ? -1 : (int) session.getAttribute("studentID");
        StudentDAO studentDao = new StudentDAO();
        Student studentLogin = studentDao.getStudent(studentId);

        if (session.getAttribute("studentID") == null || studentLogin == null) {
            response.sendRedirect("LoginController?error=Your account can not login here");
            return;
        }
        String action = request.getParameter("action");
        if ("markCompleted".equals(action)) {
            this.markLessionCompleted(request, response, studentId);
        }
    }

    private void markLessionCompleted(HttpServletRequest request, HttpServletResponse response, int studentId)
            throws ServletException, IOException {
        try {
            int lessionId = Integer.parseInt(request.getParameter("lessionId"));

            StudentLessionDAO studentLessionDao = new StudentLessionDAO();
            boolean isMarked = studentLessionDao.markLessionCompleted(studentId, lessionId);
            
            if (isMarked) {
                response.sendRedirect("LessonStudentController?action=learn&lessionId=" + lessionId);
            } else {
                response.sendRedirect("LessonStudentController?action=learn&lessionId=" + lessionId + "&error=Could not mark completed");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
            response.sendRedirect("StudentClassController?error=Error while marking lession as completed");
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
