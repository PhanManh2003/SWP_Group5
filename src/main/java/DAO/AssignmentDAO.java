/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DBConnection.DBConnection;
import Model.Assignment;
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
public class AssignmentDAO {

    private Connection conn;

    public AssignmentDAO() {
        try {
            conn = DBConnection.connect();
        } catch (Exception e) {
            System.out.println("Connection fail: " + e);
        }
    }

//  teacher
    public List<Assignment> getAllAssignmentsByClassId(int idClass) {
        List<Assignment> assignments = new ArrayList<>();
        String query = "SELECT * FROM Assignments where classID=?";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, idClass);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Assignment assignment = new Assignment();
                assignment.setAssignmentID(resultSet.getInt("AssignmentID"));
                assignment.setTitle(resultSet.getString("Title"));
                assignment.setDescription(resultSet.getString("Description"));
                assignment.setDueDate(resultSet.getTimestamp("DueDate"));
                assignment.setClassID(resultSet.getInt("ClassID"));
                assignment.setType(resultSet.getInt("Type"));
                assignment.setStatus(resultSet.getInt("Status"));
                assignments.add(assignment);
            }
        } catch (SQLException e) {
            System.out.println("Get all assignments: " + e);
        }
        return assignments;
    }

    public List<Assignment> getAllAssignmentsByteacherId(int teacherId) {
        List<Assignment> assignments = new ArrayList<>();
        String query = "SELECT A.* FROM Assignments as A join Classes as C on "
                + "C.ClassID = A.ClassID join Teachers as T on T.teacherID = C.TeacherId where C.TeacherId=?";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, teacherId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Assignment assignment = new Assignment();
                assignment.setAssignmentID(resultSet.getInt("AssignmentID"));
                assignment.setTitle(resultSet.getString("Title"));
                assignment.setDescription(resultSet.getString("Description"));
                assignment.setDueDate(resultSet.getTimestamp("DueDate"));
                assignment.setClassID(resultSet.getInt("ClassID"));
                assignment.setType(resultSet.getInt("Type"));
                assignment.setStatus(resultSet.getInt("Status"));
                assignments.add(assignment);
            }
        } catch (SQLException e) {
            System.out.println("Get all assignments by teacher  id: " + e);
        }
        return assignments;
    }
    
    public Assignment getAssignmentByTeacher(int assignmentID, int teacherId) {
        String query = "SELECT A.* FROM Assignments as A join Classes as C on "
                + "C.ClassID = A.ClassID join Teachers as T on T.teacherID = C.TeacherId where C.TeacherId=? and A.AssignmentID = ?";
        try ( PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, teacherId);
            statement.setInt(2, assignmentID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Assignment assignment = new Assignment();
                assignment.setAssignmentID(resultSet.getInt("AssignmentID"));
                assignment.setTitle(resultSet.getString("Title"));
                assignment.setDescription(resultSet.getString("Description"));
                assignment.setDueDate(resultSet.getTimestamp("DueDate"));
                assignment.setClassID(resultSet.getInt("ClassID"));
                assignment.setType(resultSet.getInt("Type"));
                assignment.setStatus(resultSet.getInt("Status"));
                return assignment;
            }
        } catch (SQLException e) {
            System.out.println("Get assignment: " + e);
        }
        return null;
    }
//  end teacher
//  student

    public List<Assignment> getAllAssignmentsByClassIdActive(int idClass) {
        List<Assignment> assignments = new ArrayList<>();
        String query = "SELECT * FROM Assignments where classID=? and status = 1";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, idClass);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Assignment assignment = new Assignment();
                assignment.setAssignmentID(resultSet.getInt("AssignmentID"));
                assignment.setTitle(resultSet.getString("Title"));
                assignment.setDescription(resultSet.getString("Description"));
                assignment.setDueDate(resultSet.getTimestamp("DueDate"));
                assignment.setClassID(resultSet.getInt("ClassID"));
                assignment.setType(resultSet.getInt("Type"));
                assignment.setStatus(resultSet.getInt("Status"));
                assignments.add(assignment);
            }
        } catch (SQLException e) {
            System.out.println("Get all assignments by student: " + e);
        }
        return assignments;
    }
    
    public Assignment getAssignmentSubmit(int assignmentID, int classId) {
        String query = "SELECT * FROM Assignments WHERE AssignmentID = ? and  classId=? and status=1";
        try ( PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, assignmentID);
            statement.setInt(2, classId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Assignment assignment = new Assignment();
                assignment.setAssignmentID(resultSet.getInt("AssignmentID"));
                assignment.setTitle(resultSet.getString("Title"));
                assignment.setDescription(resultSet.getString("Description"));
                assignment.setDueDate(resultSet.getTimestamp("DueDate"));
                assignment.setClassID(resultSet.getInt("ClassID"));
                assignment.setType(resultSet.getInt("Type"));
                assignment.setStatus(resultSet.getInt("Status"));
                return assignment;
            }
        } catch (SQLException e) {
            System.out.println("Get assignment: " + e);
        }
        return null;
    }
//  end student

    public int addAssignment(Assignment assignment) {
        String query = "INSERT INTO Assignments (Title, Description, DueDate, ClassID, Type, Status) VALUES (?, ?, ?, ?, ?, ?)";
        try ( PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, assignment.getTitle());
            statement.setString(2, assignment.getDescription());
            statement.setTimestamp(3, new Timestamp(assignment.getDueDate().getTime()));
            statement.setInt(4, assignment.getClassID());
            statement.setInt(5, assignment.getType());
            statement.setInt(6, assignment.getStatus());
            return statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Add assignment: " + e);
        }
        return 0;
    }

    public Assignment getAssignment(int assignmentID) {
        String query = "SELECT * FROM Assignments WHERE AssignmentID = ?";
        try ( PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, assignmentID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Assignment assignment = new Assignment();
                assignment.setAssignmentID(resultSet.getInt("AssignmentID"));
                assignment.setTitle(resultSet.getString("Title"));
                assignment.setDescription(resultSet.getString("Description"));
                assignment.setDueDate(resultSet.getTimestamp("DueDate"));
                assignment.setClassID(resultSet.getInt("ClassID"));
                assignment.setType(resultSet.getInt("Type"));
                assignment.setStatus(resultSet.getInt("Status"));
                return assignment;
            }
        } catch (SQLException e) {
            System.out.println("Get assignment: " + e);
        }
        return null;
    }

    public List<Assignment> getAllAssignments() {
        List<Assignment> assignments = new ArrayList<>();
        String query = "SELECT * FROM Assignments";
        try ( PreparedStatement statement = conn.prepareStatement(query);  ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Assignment assignment = new Assignment();
                assignment.setAssignmentID(resultSet.getInt("AssignmentID"));
                assignment.setTitle(resultSet.getString("Title"));
                assignment.setDescription(resultSet.getString("Description"));
                assignment.setDueDate(resultSet.getTimestamp("DueDate"));
                assignment.setClassID(resultSet.getInt("ClassID"));
                assignment.setType(resultSet.getInt("Type"));
                assignment.setStatus(resultSet.getInt("Status"));
                assignments.add(assignment);
            }
        } catch (SQLException e) {
            System.out.println("Get all assignments: " + e);
        }
        return assignments;
    }

    public int updateAssignment(Assignment assignment) {
        String query = "UPDATE Assignments SET Title = ?, Description = ?, DueDate = ?, ClassID = ?, Type = ?, Status = ? WHERE AssignmentID = ?";
        try ( PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, assignment.getTitle());
            statement.setString(2, assignment.getDescription());
            statement.setTimestamp(3, new Timestamp(assignment.getDueDate().getTime()));
            statement.setInt(4, assignment.getClassID());
            statement.setInt(5, assignment.getType());
            statement.setInt(6, assignment.getStatus());
            statement.setInt(7, assignment.getAssignmentID());
            return statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Update assignment: " + e);
        }
        return 0;
    }

    public int deleteAssignment(int assignmentID, int classId) {
        String query = "DELETE FROM Assignments WHERE AssignmentID = ? and classID=?";
        try ( PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, assignmentID);
            statement.setInt(2, classId);
            return statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Delete assignment: " + e);
        }
        return 0;
    }

}
