/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DBConnection.DBConnection;
import Model.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;


public class StudentDAO {

    private Connection conn;

    public StudentDAO() {
        try {
            conn = DBConnection.connect();
        } catch (Exception e) {
            System.out.println("Connection fail: " + e);
        }
    }
//  teacher
    
    public List<Student> getAllStudentsByClass(int classId) {
        List<Student> students = new ArrayList<>();
        String query = "SELECT S.*, SC.status as joinStatus, sc.joinAt FROM Students as S join studentClass as SC on SC.studentID = s.studentId where sc.classId=?";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, classId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Student student = new Student();
                student.setStudentID(resultSet.getInt("StudentID"));
                student.setName(resultSet.getString("Name"));
                student.setEmail(resultSet.getString("Email"));
                student.setPhone(resultSet.getString("Phone"));
                student.setStatus(resultSet.getInt("Status"));
                student.setJoinStatus(resultSet.getInt("joinStatus"));
                student.setJoinAt(resultSet.getTimestamp("joinAt"));
                students.add(student);
            }
        } catch (Exception e) {
            System.out.println("Get all student by classId: " + e);
        }
        return students;
    }
//  end teacher

    public void addStudent(Student student) {
        String query = "INSERT INTO Students (Name, Email, Phone, Status) VALUES (?, ?, ?, ?)";
        try ( PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, student.getName());
            statement.setString(2, student.getEmail());
            statement.setString(3, student.getPhone());
            statement.setInt(4, student.getStatus());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Add student: " + e);
        }
    }
    
    public Student getStudentByEmail(String email) {
        String query = "SELECT * FROM Students WHERE email = ?";
        try ( PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Student student = new Student();
                student.setStudentID(resultSet.getInt("StudentID"));
                student.setName(resultSet.getString("Name"));
                student.setEmail(resultSet.getString("Email"));
                student.setPhone(resultSet.getString("Phone"));
                student.setStatus(resultSet.getInt("Status"));
                return student;
            }
        } catch (SQLException e) {
            System.out.println("Get student: " + e);
        }
        return null;
    }

    public Student getStudent(int studentID) {
        String query = "SELECT * FROM Students WHERE StudentID = ?";
        try ( PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, studentID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Student student = new Student();
                student.setStudentID(resultSet.getInt("StudentID"));
                student.setName(resultSet.getString("Name"));
                student.setEmail(resultSet.getString("Email"));
                student.setPhone(resultSet.getString("Phone"));
                student.setStatus(resultSet.getInt("Status"));
                return student;
            }
        } catch (SQLException e) {
            System.out.println("Get student: " + e);
        }
        return null;
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM Students";
        try ( PreparedStatement statement = conn.prepareStatement(query);  ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Student student = new Student();
                student.setStudentID(resultSet.getInt("StudentID"));
                student.setName(resultSet.getString("Name"));
                student.setEmail(resultSet.getString("Email"));
                student.setPhone(resultSet.getString("Phone"));
                student.setStatus(resultSet.getInt("Status"));
                students.add(student);
            }
        } catch (SQLException e) {
            System.out.println("Get all student: " + e);
        }
        return students;
    }

    public void updateStudent(Student student) {
        String query = "UPDATE Students SET Name = ?, Email = ?, Phone = ?, Status = ? WHERE StudentID = ?";
        try ( PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, student.getName());
            statement.setString(2, student.getEmail());
            statement.setString(3, student.getPhone());
            statement.setInt(4, student.getStatus());
            statement.setInt(5, student.getStudentID());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Update student: " + e);
        }
    }

    public boolean isEmailExists(String email, int id) {
        try ( Connection conn = DBConnection.connect();  PreparedStatement statement = conn.prepareStatement("SELECT * FROM Students WHERE Email = ? and studentId != ?")) {
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
        try ( Connection conn = DBConnection.connect();  PreparedStatement statement = conn.prepareStatement("SELECT * FROM Students WHERE Phone = ? and studentId != ?")) {
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

    public int deleteStudent(int studentID) {
        String query = "DELETE FROM Students WHERE StudentID = ?";
        try ( PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, studentID);
            return statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Delete student: " + e);
        }
        return 0;
    }

}
