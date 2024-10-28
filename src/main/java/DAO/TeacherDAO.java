/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import java.util.*;
import DBConnection.DBConnection;
import Model.Teacher;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class TeacherDAO {

    private Connection conn;

    public TeacherDAO() {
        try {
            conn = DBConnection.connect();
        } catch (Exception e) {
            System.out.println("Connection fail: " + e);
        }
    }
    
    public Teacher teacherLogin(String email) {
        String query = "SELECT * FROM Teachers WHERE email = ?";
        try ( PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Teacher teacher = new Teacher();
                teacher.setTeacherID(resultSet.getInt("TeacherID"));
                teacher.setName(resultSet.getString("Name"));
                teacher.setEmail(resultSet.getString("Email"));
                teacher.setPhone(resultSet.getString("Phone"));
                teacher.setStatus(resultSet.getInt("Status"));
                return teacher;
            }
        } catch (SQLException e) {
            System.out.println("Get teacher: " + e);
        }
        return null;
    }

    public void addTeacher(Teacher teacher) {
        String query = "INSERT INTO Teachers (Name, Email, Phone, Status) VALUES (?, ?, ?, ?)";
        try ( PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, teacher.getName());
            statement.setString(2, teacher.getEmail());
            statement.setString(3, teacher.getPhone());
            statement.setInt(4, teacher.getStatus());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Add teacher: " + e);
        }
    }

    public Teacher getTeacher(int teacherID) {
        String query = "SELECT * FROM Teachers WHERE TeacherID = ?";
        try ( PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, teacherID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Teacher teacher = new Teacher();
                teacher.setTeacherID(resultSet.getInt("TeacherID"));
                teacher.setName(resultSet.getString("Name"));
                teacher.setEmail(resultSet.getString("Email"));
                teacher.setPhone(resultSet.getString("Phone"));
                teacher.setStatus(resultSet.getInt("Status"));
                return teacher;
            }
        } catch (SQLException e) {
            System.out.println("Get teacher: " + e);
        }
        return null;
    }

    public List<Teacher> getAllTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        String query = "SELECT * FROM Teachers";
        try ( PreparedStatement statement = conn.prepareStatement(query);  ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Teacher teacher = new Teacher();
                teacher.setTeacherID(resultSet.getInt("TeacherID"));
                teacher.setName(resultSet.getString("Name"));
                teacher.setEmail(resultSet.getString("Email"));
                teacher.setPhone(resultSet.getString("Phone"));
                teacher.setStatus(resultSet.getInt("Status"));
                teachers.add(teacher);
            }
        } catch (SQLException e) {
            System.out.println("Get all teachers: " + e);
        }
        return teachers;
    }

    public void updateTeacher(Teacher teacher) {
        String query = "UPDATE Teachers SET Name = ?, Email = ?, Phone = ?, Status = ? WHERE TeacherID = ?";
        try ( PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, teacher.getName());
            statement.setString(2, teacher.getEmail());
            statement.setString(3, teacher.getPhone());
            statement.setInt(4, teacher.getStatus());
            statement.setInt(5, teacher.getTeacherID());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Update teacher: " + e);
        }
    }
    
    public boolean isEmailExists(String email, int id) {
        try ( Connection conn = DBConnection.connect();  PreparedStatement statement = conn.prepareStatement("SELECT * FROM Teachers WHERE Email = ? and teacherID != ?")) {
            statement.setString(1, email);
            statement.setInt(2, id);
            try ( ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); 
            }
        } catch (SQLException e) {
            System.out.println("Check email exists: " + e);
        }
        return false;
    }

    public boolean isPhoneExists(String phone, int id) {
        try ( Connection conn = DBConnection.connect();  PreparedStatement statement = conn.prepareStatement("SELECT * FROM Teachers WHERE Phone = ? and teacherID != ?")) {
            statement.setString(1, phone);
            statement.setInt(2, id);
            try ( ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            System.out.println("Check phone exists: " + e);
        }
        return false;
    }

    public int deleteTeacher(int teacherID) {
        String query = "DELETE FROM Teachers WHERE TeacherID = ?";
        try ( PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, teacherID);
            return statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Delete teacher: " + e);
        }
        return 0;
    }
}
