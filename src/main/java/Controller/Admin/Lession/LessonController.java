package Controller.Admin.Lession;

import DAO.CourseDAO;
import DAO.LessonDAO;
import Model.Admin;
import Model.Course;
import Model.Lesson;
import jakarta.servlet.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet("/lessons")
@MultipartConfig
public class LessonController extends HttpServlet {

    private LessonDAO lessionDAO;

    @Override
    public void init() {
        lessionDAO = new LessonDAO();  
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Admin admin = (Admin)session.getAttribute("adminLogin");
        if (admin == null) {
            response.sendRedirect("LoginController?error=Your account can not login here");
            return;
        }
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }
        switch (action) {
            case "new":
                showNewForm(request, response);
                break;
            case "insert":
                insertLession(request, response);
                break;
            case "delete":
                deleteLession(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "update":
                updateLession(request, response);
                break;
            default:
                listLessions(request, response);
                break;
        }
    }

    private void listLessions(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CourseDAO courseDao = new CourseDAO();
        String courseIdParam = request.getParameter("courseId");
        List<Lesson> listLession;
        if (courseIdParam != null && !courseIdParam.isEmpty()) {
            int courseId = Integer.parseInt(courseIdParam);
            listLession = lessionDAO.getLessionsByCourse(courseId);
        } else {
            listLession = lessionDAO.getAllLessions();
        }

        List<Course> courses = courseDao.getAllCourses();
        request.setAttribute("listLession", listLession);
        request.setAttribute("courses", courses);

        RequestDispatcher dispatcher = request.getRequestDispatcher("./view/admin/lession/list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CourseDAO courseDao = new CourseDAO();
        List<Course> courses = courseDao.getAllCourses();
        request.setAttribute("courses", courses);
        RequestDispatcher dispatcher = request.getRequestDispatcher("./view/admin/lession/lession-form.jsp");
        dispatcher.forward(request, response);
    }

    private void insertLession(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession();
        Admin admin = (Admin)session.getAttribute("adminLogin");
        if (admin == null) {
            response.sendRedirect("LoginController?error=Your account can not login here");
            return;
        }
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        String title = request.getParameter("title");
        String videoUrl = request.getParameter("videoUrl");
        String docsLink = request.getParameter("docsLink");
        String description = request.getParameter("description");
        String status = request.getParameter("status");

        if (title.isEmpty() || description.isEmpty()) {
            request.setAttribute("error", "Title and Description cannot be empty!");
            showNewForm(request, response);
            return;
        }

        String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";

        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();  
        }

        Part videoFile = request.getPart("videoFile");
        if (videoFile != null && videoFile.getSize() > 0) {
            String videoFileName = extractFileName(videoFile);
            String savePath = uploadPath + File.separator + videoFileName;
            videoFile.write(savePath);
            videoUrl = "uploads/" + videoFileName;  
        }

        Part docsFile = request.getPart("docsFile");
        if (docsFile != null && docsFile.getSize() > 0) {
            String docsFileName = extractFileName(docsFile);
            String savePath = uploadPath + File.separator + docsFileName;
            docsFile.write(savePath);
            docsLink = "uploads/" + docsFileName;  
        }

        // Tạo mới Lesson
        Lesson lession = new Lesson();
        lession.setCourseId(courseId);
        lession.setTitle(title);
        lession.setVideoUrl(videoUrl);
        lession.setDocsLink(docsLink);
        lession.setDescription(description);
        lession.setStatus(status);
        lession.setAdminID(admin.getId());

        lessionDAO.addLession(lession);
        response.sendRedirect("lessons?action=list");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CourseDAO courseDao = new CourseDAO();
        List<Course> courses = courseDao.getAllCourses();
        request.setAttribute("courses", courses);
        int lessionId = Integer.parseInt(request.getParameter("id"));
        Lesson existingLession = lessionDAO.getLessionById(lessionId);
        RequestDispatcher dispatcher = request.getRequestDispatcher("./view/admin/lession/lession-form.jsp");
        request.setAttribute("lession", existingLession);
        dispatcher.forward(request, response);
    }

    private void updateLession(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int lessionId = Integer.parseInt(request.getParameter("id"));
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        String title = request.getParameter("title");
        String videoUrl = request.getParameter("videoUrl");
        String docsLink = request.getParameter("docsLink");
        String description = request.getParameter("description");
        String status = request.getParameter("status");

        if (title.isEmpty() || description.isEmpty()) {
            request.setAttribute("error", "Title and Description cannot be empty!");
            showEditForm(request, response);
            return;
        }
        
        String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";

        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();  
        }

        Part videoFile = request.getPart("videoFile");
        Part docsFile = request.getPart("docsFile");

        if (videoFile != null && videoFile.getSize() > 0) {
            String videoFileName = extractFileName(videoFile);
            String savePath = getServletContext().getRealPath("") + File.separator + "uploads" + File.separator + videoFileName;
            videoFile.write(savePath);
            videoUrl = "uploads/" + videoFileName;
        }

        if (docsFile != null && docsFile.getSize() > 0) {
            String docsFileName = extractFileName(docsFile);
            String savePath = getServletContext().getRealPath("") + File.separator + "uploads" + File.separator + docsFileName;
            docsFile.write(savePath);
            docsLink = "uploads/" + docsFileName;
        }

        Lesson lession = new Lesson();
        lession.setLessionId(lessionId);
        lession.setCourseId(courseId);
        lession.setTitle(title);
        lession.setVideoUrl(videoUrl);
        lession.setDocsLink(docsLink);
        lession.setDescription(description);
        lession.setStatus(status);

        lessionDAO.updateLession(lession);
        response.sendRedirect("lessons?action=list");
    }

    private void deleteLession(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int lessionId = Integer.parseInt(request.getParameter("id"));
        lessionDAO.deleteLession(lessionId);
        response.sendRedirect("lessons?action=list");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }
}
