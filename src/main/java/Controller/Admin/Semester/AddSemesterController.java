/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Admin.Semester;

import DAO.SemesterDAO;
import Model.Semester;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author HP
 */
@WebServlet(name = "AddSemesterController", urlPatterns = {"/AddSemesterController"})
public class AddSemesterController extends HttpServlet {

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
            out.println("<title>Servlet AddSemesterController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddSemesterController at " + request.getContextPath() + "</h1>");
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
        RequestDispatcher dispatcher = request.getRequestDispatcher("./view/admin/semester/add.jsp");
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
        String semesterName = request.getParameter("semesterName").trim();
        String yearStr = request.getParameter("year").trim();
        String statusStr = request.getParameter("status").trim();

        if (semesterName == null || semesterName.isEmpty() || yearStr == null || yearStr.isEmpty() || statusStr == null || statusStr.isEmpty()) {
            request.setAttribute("errorMessage", "All fields are required.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("./view/admin/semester/add.jsp");
            dispatcher.forward(request, response);
            return;
        }

        int year;
        int status;

        try {
            year = Integer.parseInt(yearStr);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Year must be a valid number.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("./view/admin/semester/add.jsp");
            dispatcher.forward(request, response);
            return;
        }

        try {
            status = Integer.parseInt(statusStr);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Status must be a valid number.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("./view/admin/semester/add.jsp");
            dispatcher.forward(request, response);
            return;
        }
        SemesterDAO semesterDAO = new SemesterDAO();

        if (semesterDAO.getSemesterName(semesterName, 0)) {
            request.setAttribute("errorMessage", "Name semester is exist in system.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("./view/admin/semester/add.jsp");
            dispatcher.forward(request, response);
            return;
        }

        Semester semester = new Semester();
        semester.setSemesterName(semesterName);
        semester.setYear(year);
        semester.setStatus(status);

        semesterDAO.addSemester(semester);

        response.sendRedirect("ListSemestersController?success=Add successfully");
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
