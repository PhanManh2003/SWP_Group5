/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Admin.Admin;

import DAO.AdminDAO;
import DAO.StudentDAO;
import DAO.TeacherDAO;
import Model.Admin;
import Utils.Validation;
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
@WebServlet(name = "AdminController", urlPatterns = {"/AdminController"})
public class AdminController extends HttpServlet {

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
            out.println("<title>Servlet AdminController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminController at " + request.getContextPath() + "</h1>");
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
        String action = request.getParameter("action");
        action = action != null ? action : "";
        AdminDAO adminDao = new AdminDAO();
        switch (action) {
            case "add":
                request.getRequestDispatcher("./view/admin/admin/add.jsp").forward(request, response);
                break;
            case "edit":
                try {
                int idAdmin = Integer.parseInt(request.getParameter("adminID"));
                Admin currentAdmin = adminDao.getAdmin(idAdmin);
                if (currentAdmin != null) {
                    request.setAttribute("currentAdmin", currentAdmin);
                    request.getRequestDispatcher("./view/admin/admin/edit.jsp").forward(request, response);
                } else {
                    response.sendRedirect("AdminController?error=Can not found this account");
                }
            } catch (Exception e) {
                response.sendRedirect("AdminController?error=Admin id is not valid");
            }
            break;
            case "update-profile":
                Admin admin = (Admin) session.getAttribute("adminLogin");

                if (admin == null) {
                    response.sendRedirect("LoginControler?error=Can not found this account");
                    return;
                }
                Admin currentAdminU = adminDao.getAdmin(admin.getId());
                if (currentAdminU != null) {
                    request.setAttribute("currentAdmin", currentAdminU);
                    request.getRequestDispatcher("./view/admin/admin/profile.jsp").forward(request, response);
                } else {
                    response.sendRedirect("LoginControler?error=Can not found this account");
                }
                break;
            case "delete":
                try {
                int idAdmin = Integer.parseInt(request.getParameter("adminID"));
                Admin currentAdmin = adminDao.getAdmin(idAdmin);
                if (currentAdmin != null) {
                    int result = adminDao.deleteAdmin(idAdmin);
                    if (result > 0) {
                        response.sendRedirect("AdminController?success=Delete account successfully");
                    } else {
                        response.sendRedirect("AdminController?error=Delete account fail");
                    }
                } else {
                    response.sendRedirect("AdminController?error=Can not found this account");
                }
            } catch (Exception e) {
                response.sendRedirect("AdminController?error=Admin id is not valid");
            }
            break;
            default:
                List<Admin> admins = adminDao.getAllAdmin();
                request.setAttribute("admins", admins);
                request.getRequestDispatcher("./view/admin/admin/list.jsp").forward(request, response);

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
        String action = request.getParameter("action");
        action = action != null ? action : "";
        switch (action) {
            case "add":
                this.addAdmin(request, response);
                break;
            case "edit":
                this.editAdmin(request, response);
                break;
            default:
                throw new AssertionError();
        }
    }

    private void editAdmin(HttpServletRequest request, HttpServletResponse response) {
        try {
            TeacherDAO teacherDao = new TeacherDAO();
            String idAdmin = request.getParameter("idAdmin").trim();
            String name = request.getParameter("name").trim();
            String email = request.getParameter("email").trim();
            String password = request.getParameter("password").trim();
            String phone = request.getParameter("phone").trim();
            String statusStr = request.getParameter("status").trim();
            int idAdminNumber;
            try {
                idAdminNumber = Integer.parseInt(idAdmin);
            } catch (NumberFormatException e) {
                response.sendRedirect("AdminController?error=Admin id is not valid");
                return;
            }
            if (name == null || email == null || phone == null || statusStr == null
                    || name.isEmpty() || email.isEmpty() || phone.isEmpty() || statusStr.isEmpty()) {
                response.sendRedirect("AdminController?action=edit&adminID=" + idAdmin + "&error=Please fill all field");
                return;
            }

            if (password.length() == 0) {
                password = request.getParameter("oldPassword");
            } else if (!password.equals(request.getParameter("confirm-password"))) {
                response.sendRedirect("AdminController?action=edit&adminID=" + idAdmin + "&error=Confirm password  is not valid");
                return;
            }
            int status;

            try {
                status = Integer.parseInt(statusStr);
            } catch (NumberFormatException e) {
                response.sendRedirect("AdminControlleraction=edit&adminID=" + idAdmin + "&error=Invalid status");
                return;
            }
            Validation validate = new Validation();
            if (!validate.isValidEmail(email)) {
                response.sendRedirect("AdminController?action=edit&adminID=" + idAdmin + "&error=Invalid email");
                return;
            }
            
            if (teacherDao.isEmailExists(email,0)) {
                response.sendRedirect("AdminController?action=edit&adminID=" + idAdmin + "&error=Email is exist in system");
                return;
            }
            StudentDAO studentDao = new StudentDAO();
            if (studentDao.isEmailExists(email,0)) {
                response.sendRedirect("AdminController?action=edit&adminID=" + idAdmin + "&error=Email is exist in system");
                return;
            }

            if (!validate.isValidPhoneNumber(phone)) {
                response.sendRedirect("AdminController?action=edit&adminID=" + idAdmin + "&error=Invalid phone");
                return;
            }

            if (!(password.length() > 7) || password.contains(" ")) {
                response.sendRedirect("AdminController?action=edit&adminID=" + idAdmin + "&error=Password must be from 8 character and no space");
                return;
            }

            AdminDAO adminDao = new AdminDAO();
            if (adminDao.isEmailExists(email, idAdminNumber)) {
                response.sendRedirect("AdminController?action=edit&adminID=" + idAdmin + "&error=Email exists");
                return;
            }
            if (adminDao.isPhoneExists(phone, idAdminNumber)) {
                response.sendRedirect("AdminController?action=edit&adminID=" + idAdmin + "&error=Phone exists");
                return;
            }
            Admin admin = new Admin(idAdminNumber, name, email, phone, password, status);
            int result = adminDao.updateAdmin(admin);
            if (result > 0) {
                if (request.getParameter("type") != null) {
                    response.sendRedirect("AdminController?action=update-profile&success=Update successfully");
                } else {
                    response.sendRedirect("AdminController?success=Update successfully");
                }
            } else {
                response.sendRedirect("AdminController?&error=Update fail");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    private void addAdmin(HttpServletRequest request, HttpServletResponse response) {
        try {
            String name = request.getParameter("name").trim();
            String email = request.getParameter("email").trim();
            String password = request.getParameter("password").trim();
            String phone = request.getParameter("phone").trim();
            String statusStr = request.getParameter("status").trim();

            if (name == null || email == null || phone == null || statusStr == null
                    || name.isEmpty() || email.isEmpty() || phone.isEmpty() || statusStr.isEmpty()) {
                response.sendRedirect("AdminController?action=add&error=Please fill all field");
                return;
            }

            int status;
            try {
                status = Integer.parseInt(statusStr);
            } catch (NumberFormatException e) {
                response.sendRedirect("AdminController?action=add&error=Invalid status");
                return;
            }
            Validation validate = new Validation();
            if (!validate.isValidEmail(email)) {
                response.sendRedirect("AdminController?action=add&error=Invalid email");
                return;
            }
            TeacherDAO teacherDao = new TeacherDAO();
            if (teacherDao.isEmailExists(email, 0)) {
                response.sendRedirect("AdminController?action=add&error=Email is exist in system");
                return;
            }
            
            StudentDAO studentDao = new StudentDAO();
            if (studentDao.isEmailExists(email, 0)) {
                response.sendRedirect("AdminController?action=add&error=Email is exist in system");
                return;
            }
            
            if (!validate.isValidPhoneNumber(phone)) {
                response.sendRedirect("AdminController?action=add&error=Invalid phone");
                return;
            }

            if (!(password.length() > 7) || password.contains(" ")) {
                response.sendRedirect("AdminController?action=add&error=Password must be from 8 characters, contains number, lower and upper character and special character");
                return;
            }
            
            if(!validatePassword(password).equals(""))  {
                response.sendRedirect("AdminController?action=add&error=Password must be from 8 characters, contains number, lower and upper character and special character");
                return;
            }

            AdminDAO adminDao = new AdminDAO();
            if (adminDao.isEmailExists(email, 0)) {
                response.sendRedirect("AdminController?action=add&error=Email exists");
                return;
            }
            if (adminDao.isPhoneExists(phone, 0)) {
                response.sendRedirect("AdminController?action=add&error=Phone exists");
                return;
            }
            Admin admin = new Admin(0, name, email, phone, password, status);
            int result = adminDao.addAdmin(admin);
            if (result > 0) {
                response.sendRedirect("AdminController?&success=Add new successfully");
            } else {
                response.sendRedirect("AdminController?&error=Add new fail");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    private String validatePassword(String password) {
        // Kiểm tra độ dài tối đa 8 ký tự
        if (password.length() < 8) {
            return "Password must have at least 8 characters.";
        }
        // Kiểm tra có ít nhất một chữ cái viết hoa
        if (!password.matches(".*[A-Z].*")) {
            return "Password must contain at least one uppercase letter.";
        }
        // Kiểm tra có ít nhất một chữ cái viết thường
        if (!password.matches(".*[a-z].*")) {
            return "Password must contain at least one lowercase letter.";
        }
        // Kiểm tra có ít nhất một chữ số
        if (!password.matches(".*[0-9].*")) {
            return "Password must contain at least one number.";
        }
        // Kiểm tra có ít nhất một ký tự đặc biệt
        if (!password.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
            return "Password must contain at least one special character.";
        }
        return ""; // Không có lỗi
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
