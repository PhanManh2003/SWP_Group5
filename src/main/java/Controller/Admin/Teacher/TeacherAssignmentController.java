/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Teacher;

import DAO.AssignmentDAO;
import DAO.ClassDAO;
import DAO.SubmitDAO;
import DAO.TeacherDAO;
import Model.Assignment;
import Model.ClassInfo;
import Model.Submission;
import Model.Teacher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class TeacherAssignmentController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TeacherAssignmentController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TeacherAssignmentController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
        SubmitDAO submitDao = new SubmitDAO();
        if (teacherLogin != null) {
            AssignmentDAO assignmentDao = new AssignmentDAO();
            String action = request.getParameter("action");
            action = action != null ? action : "";
            switch (action) {
                case "add":
                    try {
                    int classId = Integer.parseInt(request.getParameter("classID"));
                    ClassInfo currentClass = classDao.getClass(classId);
                    if (currentClass != null) {
                        request.setAttribute("classInfo", currentClass);
                        request.getRequestDispatcher("./view/teacher/assignment/add.jsp").forward(request, response);
                    } else {
                        response.sendRedirect("TeacherClassController?error=Can not found class");
                    }
                } catch (Exception e) {
                    response.sendRedirect("TeacherClassController?error=Can not found class");
                }
                break;
                case "edit":
                    String assignmentID = request.getParameter("assignmentID").trim();
                    if (assignmentID != null && assignmentID.matches("\\d+")) {
                        try {
                            int assignmentIdNumber = Integer.parseInt(assignmentID);
                            Assignment assignment = assignmentDao.getAssignmentByTeacher(assignmentIdNumber, teacherLogin.getTeacherID());
                            if (assignment != null) {
                                request.setAttribute("assignment", assignment);
                                request.getRequestDispatcher("./view/teacher/assignment/edit.jsp").forward(request, response);
                                return;
                            } else {
                                response.sendRedirect("TeacherClassController?error=Can not found assignment");
                            }
                        } catch (NumberFormatException e) {
                            response.sendRedirect("TeacherClassController?error=Can not found assignment");
                        }
                    } else {
                        response.sendRedirect("TeacherClassController?error=Can not found assignment");
                    }
                    break;
                case "submission":
                    //Lấy asgId 
                    String assignmentIDViewSubmit = request.getParameter("assignmentID").trim();
                    //Kiểm tra asgId hợp lệ số nguyên
                    if (assignmentIDViewSubmit != null && assignmentIDViewSubmit.matches("\\d+")) {
                        try {
                            //Lấy thông tin bài tập từ csdl
                            //Kiểm tra quyền truy cập giáo viên
                            int assignmentIdNumber = Integer.parseInt(assignmentIDViewSubmit);
                            Assignment assignment = assignmentDao.getAssignmentByTeacher(assignmentIdNumber, teacherLogin.getTeacherID());
                            if (assignment != null) {
                                List<Submission> submissions = submitDao.listAllSubmissionsByTeacher(assignment.getAssignmentID());
                                request.setAttribute("submissions", submissions);
                                request.setAttribute("assignment", assignment);
                                request.getRequestDispatcher("./view/teacher/assignment/submission.jsp").forward(request, response);
                            } else {
                                response.sendRedirect("TeacherClassController?error=Can not found assignment");
                            }
                        } catch (NumberFormatException e) {
                            response.sendRedirect("TeacherClassController?error=Can not found assignment");
                        }
                    } else {
                        response.sendRedirect("TeacherClassController?error=Can not found assignment");
                    }
                    break;
                case "grade":
                    //Lấy submitId gv cần xem
                    String submitId = request.getParameter("submitID").trim();
                    if (submitId != null && submitId.matches("\\d+")) {

                        try {
                            //Lấy thông tin bài nộp từ csdl
                            int submitIdNumber = Integer.parseInt(submitId);
                            Submission submitCurrent = submitDao.getSubmissionsBySubmitId(submitIdNumber);
                            if (submitCurrent == null) {
                                response.sendRedirect("TeacherClassController?error=Can not found this submisstion");
                                return;
                            }
                            //Lấy thông tin bài nộp từ csdl dựa trên id bài nộp
                            //Đảm bảo id có quyền truy cập
                            Assignment assignment = assignmentDao.getAssignmentByTeacher(submitCurrent.getAssignmentID(), teacherLogin.getTeacherID());
                            if (assignment != null) {
                                request.setAttribute("submission", submitCurrent);
                                request.setAttribute("assignment", assignment);
                                request.getRequestDispatcher("./view/teacher/assignment/grade.jsp").forward(request, response);
                            } else {
                                response.sendRedirect("TeacherClassController?error=Can not this submission");
                            }
                        } catch (NumberFormatException e) {
                            response.sendRedirect("TeacherClassController?error=Can not found assignment");
                        }
                    } else {
                        response.sendRedirect("TeacherClassController?error=Can not found assignment");
                    }
                    break;
                default:
                    List<Assignment> assignments = assignmentDao.getAllAssignmentsByteacherId(teacherLogin.getTeacherID());
                    request.setAttribute("assignments", assignments);
                    request.getRequestDispatcher("./view/teacher/assignment/list.jsp").forward(request, response);
                    break;
            }
        } else {
            response.sendRedirect("LoginController");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int idTeacher = session.getAttribute("teacherID") == null ? -1 : (int) session.getAttribute("teacherID");
        TeacherDAO teacherDAO = new TeacherDAO();
        Teacher teacherLogin = teacherDAO.getTeacher(idTeacher);
        if (session.getAttribute("teacherID") == null || teacherLogin == null) {
            response.sendRedirect("LoginController?error=Your account can not login here");
            return;
        }
        String action = request.getParameter("action");
        action = action != null ? action : "";
        switch (action) {
            case "add":
                this.addAssignment(request, response);
                break;
            case "edit":
                this.editAssignment(request, response);
                break;
            case "grade":
                this.gradeSubmission(request, response);
                break;
            default:
                throw new AssertionError();
        }
    }

    private void gradeSubmission(HttpServletRequest request, HttpServletResponse response) {
        try {
            //Lấy về asgId và subId
            String submissionID = request.getParameter("submissionID").trim();
            String assignmentID = request.getParameter("assignmentID").trim();
            String classID = request.getParameter("classID").trim();
            try {

                int submissionIDNumber = Integer.parseInt(submissionID); //subId số nguyên
                float grade = Float.parseFloat(request.getParameter("grade")); //grade thành kiểu float

                if (grade > 10 || grade < 0) {
                    throw new Exception("Error");
                }
                //Cập nhật csdl
                SubmitDAO submitDao = new SubmitDAO();
                boolean isGrade = submitDao.updateGrade(grade, submissionIDNumber);
                //Thông báo grade thành công or fail
                if (isGrade) {
                    response.sendRedirect("TeacherAssignmentController?action=submission&classID=" + classID + "&assignmentID=" + assignmentID + "&success=Grade successfully");
                } else {
                    response.sendRedirect("TeacherAssignmentController?action=submission&classID=" + classID + "&assignmentID=" + assignmentID + "&error=Grade fail");
                }
            } catch (Exception e) {
                response.sendRedirect("TeacherAssignmentController?action=submission&classID=" + classID + "&assignmentID=" + assignmentID + "&error=Please enter a number from 0 to 10");
            }
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }

    private void addAssignment(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            int idTeacher = session.getAttribute("teacherID") == null ? -1 : (int) session.getAttribute("teacherID");
            TeacherDAO teacherDAO = new TeacherDAO();
            Teacher teacherLogin = teacherDAO.getTeacher(idTeacher);
            if (session.getAttribute("teacherID") == null || teacherLogin == null) {
                response.sendRedirect("LoginController?error=Your account can not login here");
                return;
            }
            String classID = request.getParameter("classID").trim();
            String title = request.getParameter("title").trim();
            String desc = request.getParameter("desc").trim();
            String dueDateString = request.getParameter("duedate").trim();
            String typeString = request.getParameter("type").trim();
            String statusString = request.getParameter("status").trim();
            try {
                int classIdNumber = Integer.parseInt(classID);
                ClassDAO classDao = new ClassDAO();
                ClassInfo currentClass = classDao.getClass(classIdNumber);
                if (currentClass != null) {
                    if (title == null || desc == null || dueDateString == null || typeString == null || statusString == null) {
                        request.setAttribute("classInfo", currentClass);
                        request.setAttribute("errorMessage", "Please enter full fill");
                        request.getRequestDispatcher("./view/teacher/assignment/add.jsp").forward(request, response);
                        return;
                    }

                    if (desc.length() == 0) {
                        request.setAttribute("classInfo", currentClass);
                        request.setAttribute("errorMessage", "Description can not empty.");
                        request.getRequestDispatcher("./view/teacher/assignment/add.jsp").forward(request, response);
                        return;
                    }

                    if (title.length() == 0) {
                        request.setAttribute("classInfo", currentClass);
                        request.setAttribute("errorMessage", "Title can not empty.");
                        request.getRequestDispatcher("./view/teacher/assignment/add.jsp").forward(request, response);
                        return;
                    }
                    AssignmentDAO assignmentDao = new AssignmentDAO();
                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
                        LocalDateTime dateTime = LocalDateTime.parse(dueDateString, formatter);
                        Timestamp dueDate = Timestamp.valueOf(dateTime);
                        LocalDateTime now = LocalDateTime.now();
                        if (now.isAfter(dateTime)) {
                            request.setAttribute("classInfo", currentClass);
                            request.setAttribute("errorMessage", "Due date must be from now.");
                            request.getRequestDispatcher("./view/teacher/assignment/add.jsp").forward(request, response);
                            return;
                        }
                        int type = Integer.parseInt(typeString);
                        int status = Integer.parseInt(statusString);
                        Assignment ass = new Assignment(0, title, desc, dueDate, classIdNumber, type, status);
                        ass.setTeacherID(idTeacher);
                        int result = assignmentDao.addAssignment(ass);
                        if (result > 0) {
                            response.sendRedirect("TeacherClassController?action=assignment&classID=" + classIdNumber + "&success=Add assignment successfully");
                        } else {
                            response.sendRedirect("TeacherClassController?action=assignment&classID=" + classIdNumber + "&error=Add assignment successfully");
                        }
                    } catch (Exception e) {
                        request.setAttribute("classInfo", currentClass);
                        request.setAttribute("errorMessage", "Please choose a valid data.");
                        request.getRequestDispatcher("./view/teacher/assignment/add.jsp").forward(request, response);
                        return;
                    }

                } else {
                    response.sendRedirect("TeacherClassController?error=Can not found class");
                    return;
                }
            } catch (Exception e) {
                response.sendRedirect("TeacherClassController?error=Can not found class");
                return;
            }
        } catch (Exception e) {
            System.out.println("Add assignment: " + e);
        }
    }

    private void editAssignment(HttpServletRequest request, HttpServletResponse response) {
        try {
             HttpSession session = request.getSession();
            int idTeacher = session.getAttribute("teacherID") == null ? -1 : (int) session.getAttribute("teacherID");
            TeacherDAO teacherDAO = new TeacherDAO();
            Teacher teacherLogin = teacherDAO.getTeacher(idTeacher);
            if (session.getAttribute("teacherID") == null || teacherLogin == null) {
                response.sendRedirect("LoginController?error=Your account can not login here");
                return;
            }
            String assignmentID = request.getParameter("assignmentID").trim();
            String classID = request.getParameter("classID").trim();
            String title = request.getParameter("title").trim();
            String desc = request.getParameter("desc").trim();
            String dueDateString = request.getParameter("duedate").trim();
            String typeString = request.getParameter("type").trim();
            String statusString = request.getParameter("status").trim();

            if (assignmentID == null || classID == null || title == null || desc == null || dueDateString == null || typeString == null || statusString == null) {
                handleError("Please enter all fields.", request, response);
                return;
            }
            AssignmentDAO assignmentDao = new AssignmentDAO();
            int assignmentIdNumber;
            try {
                assignmentIdNumber = Integer.parseInt(assignmentID);
                Assignment assExist = assignmentDao.getAssignment(assignmentIdNumber);
                if (assExist == null) {
                    response.sendRedirect("TeacherClassController?error=Can not found assignment");
                    return;
                }
                request.setAttribute("assignment", assExist);
            } catch (NumberFormatException e) {
                handleError("Invalid assignment ID.", request, response);
                return;
            }
            int classIdNumber;
            try {
                classIdNumber = Integer.parseInt(classID);
                ClassDAO classDao = new ClassDAO();
                ClassInfo currentClass = classDao.getClass(classIdNumber);
                if (currentClass == null) {
                    handleError("Class not found.", request, response);
                    return;
                }
            } catch (NumberFormatException e) {
                handleError("Invalid class ID.", request, response);
                return;
            }

            if (title.length() == 0 || desc.length() == 0) {
                handleError("Title and description cannot be empty.", request, response);
                return;
            }

            LocalDateTime dateTime;
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
                dateTime = LocalDateTime.parse(dueDateString, formatter);
                LocalDateTime now = LocalDateTime.now();
                if (now.isAfter(dateTime)) {
                    handleError("Due date must be from now.", request, response);
                    return;
                }
            } catch (DateTimeParseException e) {
                handleError("Invalid due date format.", request, response);
                return;
            }
            Timestamp dueDate = Timestamp.valueOf(dateTime);
            int type, status;
            try {
                type = Integer.parseInt(typeString);
                status = Integer.parseInt(statusString);
            } catch (NumberFormatException e) {
                handleError("Invalid type or status.", request, response);
                return;
            }
            Assignment assignment = new Assignment(assignmentIdNumber, title, desc, dueDate, classIdNumber, type, status);
            assignment.setTeacherID(idTeacher);
            int result = assignmentDao.updateAssignment(assignment);
            if (result > 0) {
                response.sendRedirect("TeacherClassController?action=assignment&classID=" + classIdNumber + "&success=Assignment updated successfully");
            } else {
                handleError("Failed to update assignment.", request, response);
            }
        } catch (Exception e) {
            handleError("An error occurred.", request, response);
        }
    }

    private void handleError(String errorMessage, HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("./view/teacher/assignment/edit.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println("error: " + e);
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
