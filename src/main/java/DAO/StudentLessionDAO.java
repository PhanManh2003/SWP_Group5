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
import Model.StudentLession;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentLessionDAO {

    private Connection connection;

    public StudentLessionDAO() {
        try {
            connection = DBConnection.connect();
        } catch (Exception e) {
            System.out.println("Connection fail: " + e);
        }
    }

    // Thêm hoặc cập nhật thông tin hoàn thành bài học
    public void markLessionComplete(int studentId, int lessionId) throws SQLException {
        String query = "INSERT INTO StudentLession (student_id, lession_id, completion_date, is_completed) VALUES (?, ?, ?, ?)"
                + "ON DUPLICATE KEY UPDATE completion_date = ?, is_completed = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, studentId);
        ps.setInt(2, lessionId);
        ps.setDate(3, new java.sql.Date(System.currentTimeMillis()));
        ps.setBoolean(4, true);
        ps.setDate(5, new java.sql.Date(System.currentTimeMillis()));
        ps.setBoolean(6, true);
        ps.executeUpdate();
        ps.close();
    }

    // Lấy danh sách các bài học đã hoàn thành của sinh viên trong một khóa học
    public List<StudentLession> getCompletedLessionsByCourse(int studentId, int courseId) throws SQLException {
        List<StudentLession> completedLessions = new ArrayList<>();
        String query = "SELECT sl.student_id, sl.lession_id, sl.completion_date, sl.is_completed FROM StudentLession sl "
                + "JOIN Lession l ON sl.lession_id = l.lession_id WHERE sl.student_id = ? AND l.course_id = ? AND sl.is_completed = 1";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, studentId);
        ps.setInt(2, courseId);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            StudentLession studentLession = new StudentLession();
            studentLession.setStudentId(rs.getInt("student_id"));
            studentLession.setLessionId(rs.getInt("lession_id"));
            studentLession.setCompletionDate(rs.getDate("completion_date"));
            studentLession.setIsCompleted(rs.getBoolean("is_completed"));
            completedLessions.add(studentLession);
        }
        rs.close();
        ps.close();
        return completedLessions;
    }

    public StudentLession getStudentLessionByIds(int studentId, int lessionId, int courseId) throws SQLException {
        StudentLession studentLession = null;
        String query = "SELECT sl.student_id, sl.lession_id, sl.completion_date, sl.is_completed FROM StudentLession sl "
                + "JOIN Lession l ON sl.lession_id = l.lession_id "
                + "WHERE sl.student_id = ? AND sl.lession_id = ? AND l.course_id = ?";

        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, studentId);
        ps.setInt(2, lessionId);
        ps.setInt(3, courseId);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            studentLession = new StudentLession();
            studentLession.setStudentId(rs.getInt("student_id"));
            studentLession.setLessionId(rs.getInt("lession_id"));
            studentLession.setCompletionDate(rs.getDate("completion_date"));
            studentLession.setIsCompleted(rs.getBoolean("is_completed"));
        }

        rs.close();
        ps.close();
        return studentLession;
    }

    // Kiểm tra tiến độ học của sinh viên theo từng khóa học
    public int getProgressByCourse(int studentId, int courseId) throws SQLException {
        String totalQuery = "SELECT COUNT(*) AS total FROM Lession WHERE course_id = ?";
        String completedQuery = "SELECT COUNT(*) AS completed FROM StudentLession sl "
                + "JOIN Lession l ON sl.lession_id = l.lession_id WHERE sl.student_id = ? AND l.course_id = ? AND sl.is_completed = 1";

        PreparedStatement psTotal = connection.prepareStatement(totalQuery);
        psTotal.setInt(1, courseId);
        ResultSet rsTotal = psTotal.executeQuery();
        rsTotal.next();
        int totalLessions = rsTotal.getInt("total");

        PreparedStatement psCompleted = connection.prepareStatement(completedQuery);
        psCompleted.setInt(1, studentId);
        psCompleted.setInt(2, courseId);
        ResultSet rsCompleted = psCompleted.executeQuery();
        rsCompleted.next();
        int completedLessions = rsCompleted.getInt("completed");

        rsTotal.close();
        rsCompleted.close();
        psTotal.close();
        psCompleted.close();

        // Trả về tiến độ dưới dạng phần trăm
        return (int) ((double) completedLessions / totalLessions * 100);
    }

    public boolean markLessionCompleted(int studentId, int lessionId) {
        String checkQuery = "SELECT COUNT(*) FROM StudentLession WHERE student_id = ? AND lession_id = ?";
        String insertQuery = "INSERT INTO StudentLession (student_id, lession_id, is_completed, completion_date) VALUES (?, ?, 1, GETDATE())";
        String updateQuery = "UPDATE StudentLession SET is_completed = 1, completion_date = GETDATE() WHERE student_id = ? AND lession_id = ?";

        try (PreparedStatement checkPs = connection.prepareStatement(checkQuery)) {
            // Kiểm tra xem bản ghi đã tồn tại chưa
            checkPs.setInt(1, studentId);
            checkPs.setInt(2, lessionId);
            ResultSet rs = checkPs.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0) {
                    return true;
                } else {
                    try (PreparedStatement insertPs = connection.prepareStatement(insertQuery)) {
                        insertPs.setInt(1, studentId);
                        insertPs.setInt(2, lessionId);
                        int rowsInserted = insertPs.executeUpdate();
                        System.out.println("Inserted: " + rowsInserted);
                        return rowsInserted > 0;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error while marking lesson completed: " + e.getMessage());
            return false;
        }
        return false;
    }

}
