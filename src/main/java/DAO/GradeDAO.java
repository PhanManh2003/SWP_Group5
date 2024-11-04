/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author HP
 */
import DBConnection.DBConnection;
import Model.Grade;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GradeDAO {

    private Connection conn;

    public GradeDAO() {
        try {
            conn = DBConnection.connect();
        } catch (Exception e) {
            System.out.println("Connection fail: " + e);
        }
    }

    public void addGrade(Grade grade) {
        String query = "INSERT INTO Grade (grade, studentId, answerId, date) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, grade.getGrade());
            statement.setInt(2, grade.getStudentId());
            statement.setInt(3, grade.getAnswerId());
            statement.setTimestamp(4, grade.getDate());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Add grade: " + e);
        }
    }

    public void updateGrade(Grade grade) {
        String query = "UPDATE Grade SET grade = ?, studentId = ?, answerId = ?, date = ? WHERE id = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, grade.getGrade());
            statement.setInt(2, grade.getStudentId());
            statement.setInt(3, grade.getAnswerId());
            statement.setTimestamp(4, grade.getDate());
            statement.setInt(5, grade.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Update grade: " + e);
        }
    }

    public Grade getGrade(int id) {
        String query = "SELECT * FROM Grade WHERE id = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Grade grade = new Grade();
                grade.setId(resultSet.getInt("id"));
                grade.setGrade(resultSet.getInt("grade"));
                grade.setStudentId(resultSet.getInt("studentId"));
                grade.setAnswerId(resultSet.getInt("answerId"));
                grade.setDate(resultSet.getTimestamp("date"));
                return grade;
            }
        } catch (SQLException e) {
            System.out.println("Get grade: " + e);
        }
        return null;
    }

    public List<Grade> getAllGrades() {
        List<Grade> grades = new ArrayList<>();
        String query = "SELECT * FROM Grade";
        try (PreparedStatement statement = conn.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Grade grade = new Grade();
                grade.setId(resultSet.getInt("id"));
                grade.setGrade(resultSet.getInt("grade"));
                grade.setStudentId(resultSet.getInt("studentId"));
                grade.setAnswerId(resultSet.getInt("answerId"));
                grade.setDate(resultSet.getTimestamp("date"));
                grades.add(grade);
            }
        } catch (SQLException e) {
            System.out.println("Get all grades: " + e);
        }
        return grades;
    }

    public List<Grade> getAllGradesByAnswerId(int answerId) {
        List<Grade> grades = new ArrayList<>();
        String query = "SELECT * FROM Grade where answerId =?";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, answerId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Grade grade = new Grade();
                grade.setId(resultSet.getInt("id"));
                grade.setGrade(resultSet.getInt("grade"));
                grade.setStudentId(resultSet.getInt("studentId"));
                grade.setAnswerId(resultSet.getInt("answerId"));
                grade.setDate(resultSet.getTimestamp("date"));
                grades.add(grade);
            }
        } catch (SQLException e) {
            System.out.println("Get all grades: " + e);
        }
        return grades;
    }

    public int deleteGrade(int id) {
        String query = "DELETE FROM Grade WHERE id = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            return statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Delete grade: " + e);
        }
        return 0;
    }
}
