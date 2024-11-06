/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Teacher;

import DAO.AssignmentDAO;
import DAO.ClassDAO;
import DAO.StudentClassDAO;
import DAO.StudentDAO;
import DAO.TeacherDAO;
import Model.Assignment;
import Model.ClassInfo;
import Model.Student;
import Model.StudentClass;
import Model.Teacher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author HP
 */
public class TeacherClassController extends HttpServlet {

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
            out.println("<title>Servlet TeacherClassController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TeacherClassController at " + request.getContextPath() + "</h1>");
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
        if(session.getAttribute("teacherID") == null || teacherLogin == null) {
            response.sendRedirect("LoginController?error=Your account can not login here");
            return;
        }
        ClassDAO classDao = new ClassDAO();
        if (teacherLogin != null) {
            String action = request.getParameter("action");
            action = action != null ? action : "";
            switch (action) {
                case "view":
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
                break;
                case "assignment":
                   try {
                    String classId = request.getParameter("classID");
                    int classIdIn = Integer.parseInt(classId);
                    ClassInfo currentClass = classDao.getClasseActiveByTeacherById(classIdIn);
                    if (currentClass != null) {
                        AssignmentDAO assignmentDao = new AssignmentDAO();
                        List<Assignment> assignments = assignmentDao.getAllAssignmentsByClassId(classIdIn);
                        request.setAttribute("assignments", assignments);
                        request.setAttribute("currentClass", currentClass);
                        request.getRequestDispatcher("./view/teacher/class/assignment.jsp").forward(request, response);
                    } else {
                        response.sendRedirect("TeacherClassController?error=Can not found class");
                    }

                } catch (Exception e) {
                    response.sendRedirect("TeacherClassController?error=ID class invalid");
                }
                break;
                case "add-student":
                    try {
                    String classId = request.getParameter("classID");
                    int classIdIn = Integer.parseInt(classId);
                    ClassInfo currentClass = classDao.getClasseActiveByTeacherById(classIdIn);
                    if (currentClass != null) {
                        request.setAttribute("classId", classId);
                        request.getRequestDispatcher("./view/teacher/class/add-student.jsp").forward(request, response);
                    } else {
                        response.sendRedirect("TeacherClassController?error=Can not found class");
                    }

                } catch (Exception e) {
                    response.sendRedirect("TeacherClassController?error=ID class invalid");
                }
                break;
                default:
                    List<ClassInfo> classInfos = classDao.getClasseActiveByTeacher(idTeacher);
                    request.setAttribute("classes", classInfos);
                    request.getRequestDispatcher("./view/teacher/class/list.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        int idTeacher = session.getAttribute("teacherID") == null ? -1 : (int) session.getAttribute("teacherID");
        TeacherDAO teacherDAO = new TeacherDAO();
        Teacher teacherLogin = teacherDAO.getTeacher(idTeacher);
        if(session.getAttribute("teacherID") == null || teacherLogin == null) {
            response.sendRedirect("LoginController?error=Your account can not login here");
            return;
        }
        String action = request.getParameter("action");
        action = action != null ? action : "";
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        switch (action) {
            case "accept":
                String classId = request.getParameter("classId");
                String studentId = request.getParameter("studentId");
                try {
                    int classIdNumber = Integer.parseInt(classId);
                    int studentIdNumber = Integer.parseInt(studentId);
                    int status = 1;
                    StudentClass studentClass = new StudentClass(studentIdNumber, classIdNumber, null, status);
                    StudentClassDAO studentClassDAO = new StudentClassDAO();
                    int result = studentClassDAO.updateStudentStatus(studentClass);
                    if (result > 0) {
                        response.getWriter().write("success");
                    } else {
                        response.getWriter().write("fail");
                    }
                } catch (Exception e) {
                    response.getWriter().write("fail");
                }
                break;
            case "remove":
                String classIdRemove = request.getParameter("classId");
                String studentIdRemove = request.getParameter("studentId");
                try {
                    int classIdNumber = Integer.parseInt(classIdRemove);
                    int studentIdNumber = Integer.parseInt(studentIdRemove);
                    int status = 1;
                    StudentClassDAO studentClassDAO = new StudentClassDAO();
                    int result = studentClassDAO.removeStudentFromClass(studentIdNumber, classIdNumber);
                    if (result > 0) {
                        response.getWriter().write("success");
                    } else {
                        response.getWriter().write("fail");
                    }
                } catch (Exception e) {
                    response.getWriter().write("fail");
                }
                break;
            case "delete-assignment":
                String classIdAss = request.getParameter("classId");
                String assignmentId = request.getParameter("assignmentId");
                try {
                    int classIdNumber = Integer.parseInt(classIdAss);
                    int assignmentIdNumber = Integer.parseInt(assignmentId);
                    AssignmentDAO assigmentDao = new AssignmentDAO();
                    int result = assigmentDao.deleteAssignment(assignmentIdNumber, classIdNumber);
                    if (result > 0) {
                        response.getWriter().write("success");
                    } else {
                        response.getWriter().write("fail");
                    }
                } catch (Exception e) {
                    response.getWriter().write("fail");
                }
                break;
            case "add-student":
                this.addStudentToClass(request, response);
                break;
            default:
                throw new AssertionError();
        }
    }

    private void addStudentToClass(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String classIDString = request.getParameter("classID").trim();
            String email = request.getParameter("email").trim();
            if (classIDString == null || email == null || classIDString.isEmpty() || email.isEmpty()) {
                request.setAttribute("errorMessage", "Class ID and email are required");
                request.setAttribute("classId", classIDString);
                request.getRequestDispatcher("./view/teacher/class/add-student.jsp").forward(request, response);
                return;
            }

            if (!this.isValidEmail(email)) {
                request.setAttribute("errorMessage", "Email is not valid");
                request.setAttribute("classId", classIDString);
                request.getRequestDispatcher("./view/teacher/class/add-student.jsp").forward(request, response);
                return;
            }

            int classID;
            try {
                classID = Integer.parseInt(classIDString);
            } catch (NumberFormatException e) {
                request.setAttribute("errorMessage", "Class ID is not valid");
                request.setAttribute("classId", classIDString);
                request.getRequestDispatcher("./view/teacher/class/add-student.jsp").forward(request, response);
                return;
            }

            ClassDAO classDao = new ClassDAO();
            ClassInfo classInfo = classDao.getClass(classID);
            if (classInfo == null) {
                response.sendRedirect("TeacherClassController?error=Class not found");
                return;
            }
            StudentDAO studentDao = new StudentDAO();
            StudentClassDAO studentClassDao = new StudentClassDAO();
            Student student = studentDao.getStudentByEmail(email);
            if (student == null) {
                request.setAttribute("errorMessage", "Student is not exist in system");
                request.setAttribute("classId", classIDString);
                request.getRequestDispatcher("./view/teacher/class/add-student.jsp").forward(request, response);
            }

            if (studentClassDao.getStudentsByClassIdAndStudent(classID, student.getStudentID()) != null) {
                response.sendRedirect("TeacherClassController?error=Student already added to class");
                return;
            }

            int result = studentClassDao.addStudentToClass(student.getStudentID(), classID);
            if (result > 0) {
                response.sendRedirect("TeacherClassController?success=Student added to class successfully");
            } else {
                response.sendRedirect("TeacherClassController?error=Added to class fail");
            }
        } catch (Exception e) {
            response.sendRedirect("TeacherClassController?error=An error occurred");
        }
    }

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final Pattern pattern = Pattern.compile(EMAIL_REGEX);

    public static boolean isValidEmail(String email) {
        return pattern.matcher(email).matches();
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
