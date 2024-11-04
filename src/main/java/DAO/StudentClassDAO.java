/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DBConnection.DBConnection;
import Model.StudentClass;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class StudentClassDAO {

    private Connection connection;

    public StudentClassDAO() {
        try {
            connection = DBConnection.connect();
        } catch (Exception e) {
            System.out.println("Connection fail: " + e);
        }
    }

//   student
    public int addToJoinClass(int studentId, int classId) throws SQLException {
        String query = "INSERT INTO studentClass (studentId, classId, joinAt, status) VALUES (?, ?, CURRENT_TIMESTAMP, 0)";

        try ( PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(2, classId);
            return preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Add to class fail: " + e);
        }
        return 0;
    }
    
    public StudentClass getStudentsByClassId(int studentID, int classId) throws SQLException {
        String query = "SELECT * FROM studentClass WHERE classId = ? and studentId =?";

        try ( PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, classId);
            preparedStatement.setInt(2, studentID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int studentId = resultSet.getInt("studentId");
                Timestamp joinAt = resultSet.getTimestamp("joinAt");
                int status = resultSet.getInt("status");
                StudentClass studentClass = new StudentClass(studentId, classId, joinAt, status);
                return studentClass;
            }
        }
        return null;
    }
//   end student

    public int addStudentToClass(int studentId, int classId) throws SQLException {
        String query = "INSERT INTO studentClass (studentId, classId, joinAt, status) VALUES (?, ?, CURRENT_TIMESTAMP, 1)";

        try ( PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(2, classId);
            return preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Add to class fail: " + e);
        }
        return 0;
    }

    public List<StudentClass> getStudentsByClassId(int classId) throws SQLException {
        List<StudentClass> studentList = new ArrayList<>();
        String query = "SELECT * FROM studentClass WHERE classId = ?";

        try ( PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, classId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int studentId = resultSet.getInt("studentId");
                Timestamp joinAt = resultSet.getTimestamp("joinAt");
                int status = resultSet.getInt("status");
                StudentClass studentClass = new StudentClass(studentId, classId, joinAt, status);
                studentList.add(studentClass);
            }
        }
        return studentList;
    }

    public StudentClass getStudentsByClassIdAndStudent(int classId, int studentId) throws SQLException {
        String query = "SELECT * FROM studentClass WHERE classId = ? and StudentId=?";

        try ( PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, classId);
            preparedStatement.setInt(2, studentId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Timestamp joinAt = resultSet.getTimestamp("joinAt");
                int status = resultSet.getInt("status");
                StudentClass studentClass = new StudentClass(studentId, classId, joinAt, status);
                return studentClass;
            }
        }
        return null;
    }

    public int updateStudentStatus(StudentClass stClass) {
        String query = "UPDATE studentClass SET status = ? WHERE studentId = ? AND classId = ?";
        try ( PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, stClass.getStatus());
            preparedStatement.setInt(2, stClass.getStudentId());
            preparedStatement.setInt(3, stClass.getClassId());
            return preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Update status class: " + e);
        }
        return 0;
    }

    public int removeStudentFromClass(int studentId, int classId) {
        String query = "DELETE FROM studentClass WHERE studentId = ? AND classId = ?";
        try ( PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(2, classId);
            return preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Remove: " + e);
        }
        return 0;
    }
}
