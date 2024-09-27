
package controller.dashboard;

import config.GlobalConfig;
import dal.AccountDAO;
import entity.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ProfileController", urlPatterns = {"/dashboard-profile"})
public class ProfileController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute(GlobalConfig.SESSION_ACCOUNT);

        if (account == null) {
            // Nếu không có tài khoản trong session, chuyển hướng đến trang đăng nhập
            response.sendRedirect(request.getContextPath() + "/authen");
            return;
        }

        AccountDAO accountDAO = new AccountDAO();
        Account detailedAccount = accountDAO.findByEmail(account);
        
        //TODO: neu khac = null => back ve login
        
        if (detailedAccount != null) {
            request.setAttribute("accountDetails", detailedAccount);
        } else {
            request.setAttribute("accountDetails", account);
        }

        // Chuyển hướng đến trang profile.jsp
        request.getRequestDispatcher("view/dashboard/profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute(GlobalConfig.SESSION_ACCOUNT);

        if (account == null) {
            response.sendRedirect(request.getContextPath() + "/authen");
            return;
        }

        AccountDAO accountDAO = new AccountDAO();

        // Lấy dữ liệu từ form
        String gender = request.getParameter("gender");

        // Cập nhật thông tin tài khoản
        account.setGender(Boolean.parseBoolean(gender));

        // Thêm các trường khác nếu cần
        // account.setFieldName(request.getParameter("fieldName"));
        // Cập nhật vào cơ sở dữ liệu
        boolean updateSuccess = accountDAO.update(account);

        if (updateSuccess) {
            session.setAttribute(GlobalConfig.SESSION_ACCOUNT, account);
            request.setAttribute("toastMessage", "Profile updated successfully!");
            request.setAttribute("toastType", "success");
        } else {
            request.setAttribute("toastMessage", "Failed to update profile. Please try again.");
            request.setAttribute("toastType", "error");
        }

        // Lấy thông tin chi tiết mới nhất từ cơ sở dữ liệu
        Account updatedAccount = accountDAO.findByEmail(account);
        request.setAttribute("accountDetails", updatedAccount != null ? updatedAccount : account);

        // Chuyển hướng lại trang profile
        request.getRequestDispatcher("view/dashboard/profile.jsp").forward(request, response);
    }

}
