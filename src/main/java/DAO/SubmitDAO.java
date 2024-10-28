/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DBConnection.DBConnection;
import Model.Submission;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class SubmitDAO {

    private Connection conn;

    public SubmitDAO() {
        try {
            conn = DBConnection.connect();
        } catch (Exception e) {
            System.out.println("Connection fail: " + e);
        }
    }

//  teacher
     public boolean updateGrade(float grade, int submitId) {
        String sql = "UPDATE submissions SET grade = ?";
        sql += " WHERE submissionID = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setFloat(1, grade);
            statement.setInt(2, submitId);
            boolean rowUpdated = statement.executeUpdate() > 0;
            return rowUpdated;
        } catch (Exception e) {
            System.out.println("Update grade submission: " + e);
        }
        return false;
    }
     
    public List<Submission> listAllSubmissionsByTeacher(int assigmentId) {
        List<Submission> listSubmission = new ArrayList<>();

        String sql = "SELECT * FROM submissions where assignmentID = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, assigmentId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int submissionID = resultSet.getInt("submissionID");
                String submissionContent = resultSet.getString("submissionContent");
                int studentID = resultSet.getInt("studentID");
                int assignmentID = resultSet.getInt("assignmentID");
                Timestamp submissionDate = resultSet.getTimestamp("submissionDate");
                float grade = resultSet.getFloat("grade");
                Submission submission = new Submission(submissionID, submissionContent, studentID, assignmentID, submissionDate, grade);
                listSubmission.add(submission);
            }
        } catch (Exception e) {
            System.out.println("Get submisstion by teacher" + e);
        }
        return listSubmission;
    }
    
    public Submission getSubmissionsBySubmitId(int submitID) {
        String sql = "SELECT * FROM submissions where submissionID = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, submitID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int submissionID = resultSet.getInt("submissionID");
                String submissionContent = resultSet.getString("submissionContent");
                int studentID = resultSet.getInt("studentID");
                int assignmentID = resultSet.getInt("assignmentID");
                Timestamp submissionDate = resultSet.getTimestamp("submissionDate");
                float grade = resultSet.getFloat("grade");
                Submission submission = new Submission(submissionID, submissionContent, studentID, assignmentID, submissionDate, grade);
                return submission;
            }
        } catch (Exception e) {
            System.out.println("Get submisstion by teacher" + e);
        }
        return null;
    }
//  end teacher

//  student
    public Submission getSubmissionByassignment(int assignmentID, int studentID) {
        Submission submission = null;
        String sql = "SELECT * FROM submissions WHERE AssignmentID = ? and studentID=?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, assignmentID);
            statement.setInt(2, studentID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int submissionID = resultSet.getInt("submissionID");
                String submissionContent = resultSet.getString("submissionContent");
                Timestamp submissionDate = resultSet.getTimestamp("submissionDate");
                float grade = resultSet.getFloat("grade");
                submission = new Submission(submissionID, submissionContent, studentID, assignmentID, submissionDate, grade);
            }
        } catch (Exception e) {
            System.out.println("Get submition by assigment: " + e);
        }
        return submission;
    }

    public boolean addSubmission(Submission submission) {
        String sql = "INSERT INTO submissions (submissionContent, studentID, assignmentID, submissionDate) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, submission.getSubmissionContent());
            statement.setInt(2, submission.getStudentID());
            statement.setInt(3, submission.getAssignmentID());
            statement.setTimestamp(4, submission.getSubmissionDate());
            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Fail to submit: " + e);
        }
        return false;
    }

    public boolean updateSubmissionStudent(Submission submission) {
        String sql = "UPDATE submissions SET submissionContent = ?, studentID = ?, assignmentID = ?, submissionDate = ?";
        sql += " WHERE submissionID = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, submission.getSubmissionContent());
            statement.setInt(2, submission.getStudentID());
            statement.setInt(3, submission.getAssignmentID());
            statement.setTimestamp(4, submission.getSubmissionDate());
            statement.setInt(5, submission.getSubmissionID());
            boolean rowUpdated = statement.executeUpdate() > 0;
            return rowUpdated;
        } catch (Exception e) {
            System.out.println("Update submission: " + e);
        }
        return false;
    }
//  end student

    public boolean insertSubmission(Submission submission) throws SQLException {
        String sql = "INSERT INTO submissions (submissionContent, studentID, assignmentID, submissionDate, grade) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, submission.getSubmissionContent());
        statement.setInt(2, submission.getStudentID());
        statement.setInt(3, submission.getAssignmentID());
        statement.setTimestamp(4, submission.getSubmissionDate());
        statement.setFloat(5, submission.getGrade());
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        return rowInserted;
    }

    public List<Submission> listAllSubmissions() throws SQLException {
        List<Submission> listSubmission = new ArrayList<>();

        String sql = "SELECT * FROM submissions";
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int submissionID = resultSet.getInt("submissionID");
            String submissionContent = resultSet.getString("submissionContent");
            int studentID = resultSet.getInt("studentID");
            int assignmentID = resultSet.getInt("assignmentID");
            Timestamp submissionDate = resultSet.getTimestamp("submissionDate");
            float grade = resultSet.getFloat("grade");
            Submission submission = new Submission(submissionID, submissionContent, studentID, assignmentID, submissionDate, grade);
            listSubmission.add(submission);
        }
        resultSet.close();
        statement.close();
        return listSubmission;
    }

    public boolean deleteSubmission(Submission submission) throws SQLException {
        String sql = "DELETE FROM submissions where submissionID = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, submission.getSubmissionID());
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        return rowDeleted;
    }

    public boolean updateSubmission(Submission submission) throws SQLException {
        String sql = "UPDATE submissions SET submissionContent = ?, studentID = ?, assignmentID = ?, submissionDate = ?, grade = ?";
        sql += " WHERE submissionID = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, submission.getSubmissionContent());
        statement.setInt(2, submission.getStudentID());
        statement.setInt(3, submission.getAssignmentID());
        statement.setTimestamp(4, submission.getSubmissionDate());
        statement.setFloat(5, submission.getGrade());
        statement.setInt(6, submission.getSubmissionID());
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        return rowUpdated;
    }

    public Submission getSubmission(int id) throws SQLException {
        Submission submission = null;
        String sql = "SELECT * FROM submissions WHERE submissionID = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            String submissionContent = resultSet.getString("submissionContent");
            int studentID = resultSet.getInt("studentID");
            int assignmentID = resultSet.getInt("assignmentID");
            Timestamp submissionDate = resultSet.getTimestamp("submissionDate");
            float grade = resultSet.getFloat("grade");
            submission = new Submission(id, submissionContent, studentID, assignmentID, submissionDate, grade);
        }
        resultSet.close();
        statement.close();
        return submission;
    }
}
