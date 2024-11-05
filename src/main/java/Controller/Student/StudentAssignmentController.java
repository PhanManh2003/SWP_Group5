/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Student;

import DAO.AssignmentDAO;
import DAO.ClassDAO;
import DAO.StudentClassDAO;
import DAO.StudentDAO;
import DAO.SubmitDAO;
import Model.Assignment;
import Model.ClassInfo;
import Model.StudentClass;
import Model.Student;
import Model.Submission;
import Utils.Upload;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;

/**
 *
 * @author HP
 */
@WebServlet(name = "StudentAssignmentController", urlPatterns = {"/StudentAssignmentController"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class StudentAssignmentController extends HttpServlet {

    private String filePath = "/upload/submition/";
    private static final long serialVersionUID = 1L;

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
            out.println("<title>Servlet StudentAssignmentController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StudentAssignmentController at " + request.getContextPath() + "</h1>");
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
        if(session.getAttribute("studentID") == null || studentLogin == null) {
            response.sendRedirect("LoginController?error=Your account can not login here");
            return;
        }
        ClassDAO classDao = new ClassDAO();
        StudentClassDAO studentClassDao = new StudentClassDAO();
        SubmitDAO submitDao = new SubmitDAO();
        if (studentLogin != null) {
            String action = request.getParameter("action");
            action = action != null ? action : "";
            switch (action) {
                case "submit":
                    try {
                    String classId = request.getParameter("classID");
                    String assignmentId = request.getParameter("assignmentID");
                    int classIdIn = Integer.parseInt(classId);
                    int assignmentIdNumber = Integer.parseInt(assignmentId);
                    ClassInfo currentClass = classDao.getClasseActiveByStudentById(classIdIn);
                    AssignmentDAO assignemtDao = new AssignmentDAO();
                    if (currentClass != null) {
                        StudentClass studentJoin = studentClassDao.getStudentsByClassIdAndStudent(currentClass.getClassID(), studentId);
                        if (studentJoin != null) {
                            Assignment ass = assignemtDao.getAssignmentSubmit(assignmentIdNumber, currentClass.getClassID());
                            if (ass == null) {
                                response.sendRedirect("StudentClassController?action=assignment&classID=" + classId + "&error=This assignment is not exist in this class");
                            } else {
                                LocalDateTime dueDate = ass.getDueDate().toLocalDateTime();
                                LocalDateTime now = LocalDateTime.now();
                                if (now.isAfter(dueDate)) {
                                    response.sendRedirect("StudentClassController?action=assignment&classID=" + classId + "&error=This assignment is expired");
                                    return;
                                }
                                Submission submit = submitDao.getSubmissionByassignment(ass.getAssignmentID(), studentId);
                                if (submit == null) {
                                    request.setAttribute("currentClass", currentClass);
                                    request.setAttribute("studentLogin", studentLogin);
                                    request.setAttribute("assignment", ass);
                                    request.getRequestDispatcher("./view/student/assignment/submit.jsp").forward(request, response);
                                } else {
                                    request.setAttribute("currentClass", currentClass);
                                    request.setAttribute("currentSubmit", submit);
                                    request.getRequestDispatcher("./view/student/assignment/edit-submit.jsp").forward(request, response);
                                }
                            }
                        } else {
                            response.sendRedirect("StudentClassController?action=view&classID=" + classId + "&error=Please join to class to submit");
                        }
                    } else {
                        response.sendRedirect("StudentClassController?error=Can not found class");
                    }
                } catch (Exception e) {
                    response.sendRedirect("StudentClassController?error=ID class invalid");
                }
                break;
                case "view-submition":
                    try {
                    String classId = request.getParameter("classID");
                    String assignmentId = request.getParameter("assignmentID");
                    int classIdIn = Integer.parseInt(classId);
                    int assignmentIdNumber = Integer.parseInt(assignmentId);
                    ClassInfo currentClass = classDao.getClasseActiveByStudentById(classIdIn);
                    AssignmentDAO assignemtDao = new AssignmentDAO();
                    if (currentClass != null) {
                        StudentClass studentJoin = studentClassDao.getStudentsByClassIdAndStudent(currentClass.getClassID(), studentId);
                        if (studentJoin != null) {
                            Assignment ass = assignemtDao.getAssignmentSubmit(assignmentIdNumber, currentClass.getClassID());
                            if (ass == null) {
                                response.sendRedirect("StudentClassController?action=assignment&classID=" + classId + "&error=This assignment is not exist in this class");
                            } else {
                                Submission submit = submitDao.getSubmissionByassignment(ass.getAssignmentID(), studentId);
                                request.setAttribute("currentClass", currentClass);
                                request.setAttribute("studentLogin", studentLogin);
                                request.setAttribute("assignment", ass);
                                request.setAttribute("submit", submit);
                                request.getRequestDispatcher("./view/student/assignment/view-submit.jsp").forward(request, response);
                            }
                        } else {
                            response.sendRedirect("StudentClassController?action=view&classID=" + classId + "&error=Please join to class to submit");
                        }
                    } else {
                        response.sendRedirect("StudentClassController?error=Can not found class");
                    }
                } catch (Exception e) {
                    response.sendRedirect("StudentClassController?error=ID class invalid");
                }
                break;
            }
        } else {
            response.sendRedirect("StudentLoginController");
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
        if(session.getAttribute("studentID") == null || studentLogin == null) {
            response.sendRedirect("LoginController?error=Your account can not login here");
            return;
        }
        String action = request.getParameter("action");
        if ("submit".equals(action)) {
            this.handleFileUpload(request, response);
        } else if ("edit-submit".equals(action)) {
            this.handleEditFileUpload(request, response);
        }
    }

    private void handleFileUpload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int classID = 0;
        try {
            classID = Integer.parseInt(request.getParameter("classID"));
            int studentID = Integer.parseInt(request.getParameter("studentID"));
            int assignmentID = Integer.parseInt(request.getParameter("assignmentID"));
            Part filePart = request.getPart("file");
            String fileName = extractFileName(filePart);

            if (!fileName.endsWith(".zip")) {
                String message = "Only ZIP files are allowed!";
                response.sendRedirect("StudentClassController?action=submit&classID=" + classID +"&assignmentID=" +assignmentID+ "&error=" + message);
                return;
            }
            
            if (filePart.getSize() > 1024 * 1024 * 10) {
                String message = "File size exceeds the maximum limit of 10MB!";
                response.sendRedirect("StudentClassController?action=submit&classID=" + classID +"&assignmentID=" +assignmentID+ "&error=" + message);
                return;
            }

            String uploadPath = getServletContext().getRealPath(filePath);
            Upload upload = new Upload();
            String namePathSaveDB = filePath + upload.uploadFile(filePart, uploadPath);

            Timestamp submissionDate = Timestamp.from(Instant.now());
            SubmitDAO submitDao = new SubmitDAO();
            Submission submission = new Submission(0, namePathSaveDB, studentID, assignmentID, submissionDate, -1);
            boolean isSubmit = submitDao.addSubmission(submission);

            if (isSubmit) {
                String message = "Submit successfully";
                response.sendRedirect("StudentClassController?action=assignment&classID=" + classID + "&success=" + message);
            } else {
                String message = "Submit fail. Try again";
                response.sendRedirect("StudentClassController?action=assignment&classID=" + classID + "&error=" + message);
            }
        } catch (Exception e) {
            e.printStackTrace();
            String message = "Error occurred: " + e.getMessage();
            response.sendRedirect("StudentClassController?action=assignment&classID=" + classID + "&error=" + message);
        }
    }

    private void handleEditFileUpload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            int classID = Integer.parseInt(request.getParameter("classID"));
            int submitId = Integer.parseInt(request.getParameter("submitID"));
            int studentID = Integer.parseInt(request.getParameter("studentID"));
            int assignmentID = Integer.parseInt(request.getParameter("assignmentID"));
            Part filePart = request.getPart("file");
            String fileName = extractFileName(filePart);

            if (!fileName.endsWith(".zip")) {
                String message = "Only ZIP files are allowed!";
                response.sendRedirect("StudentClassController?action=submit&classID=" + classID +"&assignmentID=" +assignmentID+ "&error=" + message);
                return;
            }
            
            if (filePart.getSize() > 1024 * 1024 * 10) {
                String message = "File size exceeds the maximum limit of 10MB!";
                response.sendRedirect("StudentClassController?action=submit&classID=" + classID +"&assignmentID=" +assignmentID+ "&error=" + message);
                return;
            }

            Upload upload = new Upload();
            String uploadPath = getServletContext().getRealPath(filePath);
            String newFile = upload.uploadFile(filePart, uploadPath);
            String namePathSaveDB = filePath + newFile;
            if (newFile == null) {
                namePathSaveDB = request.getParameter("oldSubmission");
            }
            Timestamp submissionDate = Timestamp.from(Instant.now());
            SubmitDAO submitDao = new SubmitDAO();
            Submission submition = new Submission(submitId, namePathSaveDB, studentID, assignmentID, submissionDate, -1);
            boolean isEditSubmit = submitDao.updateSubmissionStudent(submition);
            if (isEditSubmit) {
                String message = "Update submit successfully";
                response.sendRedirect("StudentClassController?action=assignment&classID=" + classID + "&success=" + message);
            } else {
                String message = "Update submit fail. Try again";
                response.sendRedirect("StudentClassController?action=assignment&classID=" + classID + "&error=" + message);
            }
        } catch (Exception e) {
            System.out.println("Edit submission fail: " + e);
        }
    }

    private String extractFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        for (String cd : contentDisposition.split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
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
