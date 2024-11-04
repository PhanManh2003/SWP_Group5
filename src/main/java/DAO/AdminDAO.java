/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DBConnection.DBConnection;
import Model.Admin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class AdminDAO {

    private Connection conn;

    public AdminDAO() {
        try {
            conn = DBConnection.connect();
        } catch (Exception e) {
            System.out.println("Connection fail: " + e);
        }
    }

    public Admin adminForget(String email) {
        String query = "SELECT * FROM Admin WHERE email = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Admin admin = new Admin();
                admin.setId(resultSet.getInt("id"));
                admin.setName(resultSet.getString("name"));
                admin.setEmail(resultSet.getString("email"));
                admin.setPhone(resultSet.getString("phone"));
                admin.setStatus(resultSet.getInt("status"));
                admin.setPassword(resultSet.getString("password"));
                return admin;
            }
        } catch (SQLException e) {
            System.out.println("Get admin email: " + e);
        }
        return null;
    }

    public boolean updateAvatar(String avatar, int id) {
        String query = "UPDATE Admin SET avatar = ? WHERE id = ?";

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, avatar);
            statement.setInt(6, id);

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.out.println("Update admin error: " + e);
        }

        return false;
    }

    public Admin adminLogin(String email, String password) {
        String query = "SELECT * FROM Admin WHERE email = ? and password=?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Admin admin = new Admin();
                admin.setId(resultSet.getInt("id"));
                admin.setName(resultSet.getString("name"));
                admin.setEmail(resultSet.getString("email"));
                admin.setPhone(resultSet.getString("phone"));
                admin.setStatus(resultSet.getInt("status"));
                admin.setPassword(resultSet.getString("password"));
                return admin;
            }
        } catch (SQLException e) {
            System.out.println("Get admin login: " + e);
        }
        return null;
    }

    public Admin getAdmin(int id) {
        String query = "SELECT * FROM Admin WHERE id = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Admin admin = new Admin();
                admin.setId(resultSet.getInt("id"));
                admin.setName(resultSet.getString("name"));
                admin.setEmail(resultSet.getString("email"));
                admin.setPhone(resultSet.getString("phone"));
                admin.setStatus(resultSet.getInt("status"));
                admin.setPassword(resultSet.getString("password"));
                return admin;
            }
        } catch (SQLException e) {
            System.out.println("Get admin: " + e);
        }
        return null;
    }

    public List<Admin> getAllAdmin() {
        List<Admin> admins = new ArrayList<>();
        String query = "SELECT * FROM Admin";
        try (PreparedStatement statement = conn.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Admin admin = new Admin();
                admin.setId(resultSet.getInt("id"));
                admin.setName(resultSet.getString("name"));
                admin.setEmail(resultSet.getString("email"));
                admin.setPhone(resultSet.getString("phone"));
                admin.setStatus(resultSet.getInt("status"));
                admin.setPassword(resultSet.getString("password"));
                admins.add(admin);
            }
        } catch (SQLException e) {
            System.out.println("Get all admin: " + e);
        }
        return admins;
    }

    public int addAdmin(Admin admin) {
        String query = "INSERT INTO Admin (email, phone, password, status, name) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, admin.getEmail());
            statement.setString(2, admin.getPhone());
            statement.setString(3, admin.getPassword());
            statement.setInt(4, admin.getStatus());
            statement.setString(5, admin.getName());
            return statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error adding admin: " + e.getMessage());
        }
        return 0;
    }

    public int updatePassword(String password, int id) {
        String query = "UPDATE Admin SET password=? WHERE id = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, password);
            statement.setInt(2, id);
            return statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Update password admin: " + e);
        }
        return 0;
    }

    public int updateAdmin(Admin admin) {
        String query = "UPDATE Admin SET email = ?, phone = ?, password=?, status = ?, name=? WHERE id = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, admin.getEmail());
            statement.setString(2, admin.getPhone());
            statement.setString(3, admin.getPassword());
            statement.setInt(4, admin.getStatus());
            statement.setString(5, admin.getName());
            statement.setInt(6, admin.getId());
            return statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Update admin: " + e);
        }
        return 0;
    }

    public boolean isEmailExists(String email, int id) {
        String sql = "SELECT * FROM Admin WHERE email = ? and id != ?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, email);
            statement.setInt(2, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            System.out.println("Check email exists: " + e);
        }
        return false;
    }

    public boolean isPhoneExists(String phone, int id) {
        String sql = "SELECT * FROM Admin WHERE phone = ? and id != ?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, phone);
            statement.setInt(2, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            System.out.println("Check phone exists: " + e);
        }
        return false;
    }

    public int deleteAdmin(int adminId) {
        String query = "DELETE FROM Admin WHERE id = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, adminId);
            return statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Delete admin: " + e);
        }
        return 0;
    }
}
