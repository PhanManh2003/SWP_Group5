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
import Model.Lesson;
import Model.StudentLession;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LessonDAO {

    private Connection connection;

    public LessonDAO() {
        try {
            connection = DBConnection.connect();
        } catch (Exception e) {
            System.out.println("Connection fail: " + e);
        }
    }

    // Thêm mới bài học
    public boolean addLession(Lesson lession) {
        String query = "INSERT INTO Lession (course_id, title, video_url, docs_link, description, status, adminID) VALUES (?,?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, lession.getCourseId());
            ps.setString(2, lession.getTitle());
            ps.setString(3, lession.getVideoUrl());
            ps.setString(4, lession.getDocsLink());
            ps.setString(5, lession.getDescription());
            ps.setString(6, lession.getStatus());
            ps.setInt(7, lession.getAdminID());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Lấy danh sách tất cả các bài học
    public List<Lesson> getAllLessions() {
        List<Lesson> lessions = new ArrayList<>();
        String query = "SELECT * FROM Lession";
        try (PreparedStatement ps = connection.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Lesson lession = new Lesson();
                lession.setLessionId(rs.getInt("lession_id"));
                lession.setCourseId(rs.getInt("course_id"));
                lession.setTitle(rs.getString("title"));
                lession.setVideoUrl(rs.getString("video_url"));
                lession.setDocsLink(rs.getString("docs_link"));
                lession.setDescription(rs.getString("description"));
                lession.setStatus(rs.getString("status"));
                lessions.add(lession);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lessions;
    }

    public List<Lesson> getLessionsByCourseId(int courseId, int studentId) {
        List<Lesson> lessions = new ArrayList<>();
        String query = "SELECT * FROM Lession WHERE course_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, courseId);
            StudentLessionDAO studentLessionDao = new StudentLessionDAO();
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Lesson lession = new Lesson();
                    lession.setLessionId(rs.getInt("lession_id"));
                    lession.setCourseId(rs.getInt("course_id"));
                    lession.setTitle(rs.getString("title"));
                    lession.setVideoUrl(rs.getString("video_url"));
                    lession.setDocsLink(rs.getString("docs_link"));
                    lession.setDescription(rs.getString("description"));
                    lession.setStatus(rs.getString("status"));
                    lession.setStudentLession(studentLessionDao.getStudentLessionByIds(studentId,rs.getInt("lession_id"), courseId));
                    lessions.add(lession);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lessions;
    }
    
    public Lesson getLessionByIdAndStudentId(int lessionId, int studentId) {
    Lesson lession = null;
    String query = "SELECT * FROM Lession WHERE lession_id = ?";
    
    try (PreparedStatement ps = connection.prepareStatement(query)) {
        ps.setInt(1, lessionId);
        StudentLessionDAO studentLessionDao = new StudentLessionDAO();
        
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                lession = new Lesson();
                lession.setLessionId(rs.getInt("lession_id"));
                lession.setCourseId(rs.getInt("course_id"));
                lession.setTitle(rs.getString("title"));
                lession.setVideoUrl(rs.getString("video_url"));
                lession.setDocsLink(rs.getString("docs_link"));
                lession.setDescription(rs.getString("description"));
                lession.setStatus(rs.getString("status"));
                StudentLession studentLession = studentLessionDao.getStudentLessionByIds(studentId, lessionId, rs.getInt("course_id"));
                lession.setStudentLession(studentLession);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return lession;
}


    public List<Lesson> getLessionsByCourse(int courseId) {
        List<Lesson> lessons = new ArrayList<>();
        String query = "SELECT * FROM Lession WHERE course_id = ?";
        AdminDAO adminDao = new AdminDAO();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, courseId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Lesson lession = new Lesson();
                lession.setLessionId(rs.getInt("lession_id"));
                lession.setCourseId(rs.getInt("course_id"));
                lession.setTitle(rs.getString("title"));
                lession.setDescription(rs.getString("description"));
                lession.setVideoUrl(rs.getString("video_url"));
                lession.setDocsLink(rs.getString("docs_link"));
                lession.setStatus(rs.getString("status"));
                lession.setAdmin(adminDao.getAdmin(rs.getInt("AdminID")));
                lessons.add(lession);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lessons;
    }

    // Cập nhật bài học
    public boolean updateLession(Lesson lession) {
        String query = "UPDATE Lession SET course_id=?, title=?, video_url=?, docs_link=?, description=?, status=? WHERE lession_id=?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, lession.getCourseId());
            ps.setString(2, lession.getTitle());
            ps.setString(3, lession.getVideoUrl());
            ps.setString(4, lession.getDocsLink());
            ps.setString(5, lession.getDescription());
            ps.setString(6, lession.getStatus());
            ps.setInt(7, lession.getLessionId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Xóa bài học
    public boolean deleteLession(int lessionId) {
        String query = "DELETE FROM Lession WHERE lession_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, lessionId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Lấy bài học theo ID
    public Lesson getLessionById(int lessionId) {
        Lesson lession = null;
        String query = "SELECT * FROM Lession WHERE lession_id=?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, lessionId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    lession = new Lesson();
                    lession.setLessionId(rs.getInt("lession_id"));
                    lession.setCourseId(rs.getInt("course_id"));
                    lession.setTitle(rs.getString("title"));
                    lession.setVideoUrl(rs.getString("video_url"));
                    lession.setDocsLink(rs.getString("docs_link"));
                    lession.setDescription(rs.getString("description"));
                    lession.setStatus(rs.getString("status"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lession;
    }
}
