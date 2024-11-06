/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Student;

import DAO.AssignmentDAO;
import DAO.ClassDAO;
import DAO.StudentClassDAO;
import DAO.StudentDAO;
import Model.Assignment;
import Model.ClassInfo;
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
@WebServlet(name = "StudentClassController", urlPatterns = {"/StudentClassController"})
public class StudentClassController extends HttpServlet {

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
            out.println("<title>Servlet StudentClassController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StudentClassController at " + request.getContextPath() + "</h1>");
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
//      check session here
        HttpSession session = request.getSession();
        int studentId = session.getAttribute("studentID") == null ? -1 : (int) session.getAttribute("studentID");
        StudentDAO studentDao = new StudentDAO();
        Student studentLogin = studentDao.getStudent(studentId);
        if (session.getAttribute("studentID") == null || studentLogin == null) {
            response.sendRedirect("LoginController?error=Your account can not login here");
            return;
        }
        ClassDAO classDao = new ClassDAO();
        StudentClassDAO studentClassDao = new StudentClassDAO();
        if (studentLogin != null) {
            String action = request.getParameter("action");
            action = action != null ? action : "";
            switch (action) {
                case "view":
                    try {
                    String classId = request.getParameter("classID");
                    int classIdIn = Integer.parseInt(classId);
                    ClassInfo currentClass = classDao.getClasseActiveByStudentById(classIdIn);
                    if (currentClass != null) {
                        StudentClass studentJoin = studentClassDao.getStudentsByClassIdAndStudent(classIdIn, studentId);
                        request.setAttribute("studentJoin", studentJoin);
                        request.setAttribute("currentClass", currentClass);
                        request.getRequestDispatcher("./view/student/class/view.jsp").forward(request, response);
                    } else {
                        response.sendRedirect("StudentClassController?error=Can not found class");
                    }
                } catch (Exception e) {
                    response.sendRedirect("StudentClassController?error=ID class invalid");
                }
                break;
                case "ask-join":
                    try {
                    String classId = request.getParameter("classID");
                    int classIdIn = Integer.parseInt(classId);
                    ClassInfo currentClass = classDao.getClasseActiveByStudentById(classIdIn);
                    if (currentClass != null) {
                        StudentClass studentJoin = studentClassDao.getStudentsByClassIdAndStudent(classIdIn, studentId);
                        if (studentJoin == null) {
                            int result = studentClassDao.addToJoinClass(studentId, classIdIn);
                            if (result > 0) {
                                String message = "Request join class successfully. Waiting for teacher accept.";
                                response.sendRedirect("StudentClassController?action=view&classID=" + classId + "&success=" + message);
                            } else {
                                String message = "Request join class fail. Try again.";
                                response.sendRedirect("StudentClassController?action=view&classID=" + classId + "&error=" + message);
                            }
                        } else {
                            String message = "You was joined this class or waiting accept.";
                            response.sendRedirect("StudentClassController?action=view&classID=" + classId + "&error=" + message);
                        }
                    } else {
                        response.sendRedirect("StudentClassController?error=Can not found class");
                    }
                } catch (Exception e) {
                    response.sendRedirect("StudentClassController?error=ID class invalid");
                }
                break;
                case "assignment":
                   try {
                    String classId = request.getParameter("classID");
                    int classIdIn = Integer.parseInt(classId);
                    ClassInfo currentClass = classDao.getClasseActiveByTeacherById(classIdIn);
                    if (currentClass != null) {
                        StudentClass studentClass = studentClassDao.getStudentsByClassIdAndStudent(classIdIn, studentId);
                        if (studentClass == null) {
                            response.sendRedirect("StudentClassController?error=You not join this class");
                            return;
                        }
                        AssignmentDAO assignmentDao = new AssignmentDAO();
                        List<Assignment> assignments = assignmentDao.getAllAssignmentsByClassIdActive(classIdIn);
                        request.setAttribute("assignments", assignments);
                        request.setAttribute("currentClass", currentClass);
                        request.getRequestDispatcher("./view/student/assignment/list.jsp").forward(request, response);
                    } else {
                        response.sendRedirect("StudentClassController?error=Can not found class");
                    }
                } catch (Exception e) {
                    response.sendRedirect("StudentClassController?error=ID class invalid");
                }
                break;
                case "joined":
                    request.setAttribute("joined", "joined");
                    List<ClassInfo> classInforJoined = classDao.getAllClassesActiveJoin(studentId);
                    request.setAttribute("classInfors", classInforJoined);
                    request.getRequestDispatcher("./view/student/class/class.jsp").forward(request, response);
                    break;
                default:
                    String searchQuery = request.getParameter("searchQuery");
                    List<ClassInfo> classInfors = classDao.getAllClassesActiveSearch(searchQuery);
                    request.setAttribute("searchQuery", searchQuery);
                    request.setAttribute("classInfors", classInfors);
                    request.getRequestDispatcher("./view/student/class/class.jsp").forward(request, response);
                    break;
            }
        } else {
            response.sendRedirect("LoginStudentController");
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
        HttpSession session = request.getSession();
        int studentId = session.getAttribute("studentID") == null ? -1 : (int) session.getAttribute("studentID");
        StudentDAO studentDao = new StudentDAO();
        Student studentLogin = studentDao.getStudent(studentId);
        if (session.getAttribute("studentID") == null || studentLogin == null) {
            response.sendRedirect("LoginController?error=Your account can not login here");
            return;
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
