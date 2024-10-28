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
import Model.Answer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnswerDAO {

    private Connection conn;

    public AnswerDAO() {
        try {
            conn = DBConnection.connect();
        } catch (Exception e) {
            System.out.println("Connection fail: " + e);
        }
    }

    public void addAnswer(Answer answer) {
        String query = "INSERT INTO Answer (answer, questionId, studentId, date) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, answer.getAnswer());
            statement.setInt(2, answer.getQuestionId());
            statement.setInt(3, answer.getStudentId());
            statement.setTimestamp(4, answer.getDate());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Add answer: " + e);
        }
    }

    public void updateAnswer(Answer answer) {
        String query = "UPDATE Answer SET answer = ?, questionId = ?, studentId = ?, date = ? WHERE id = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, answer.getAnswer());
            statement.setInt(2, answer.getQuestionId());
            statement.setInt(3, answer.getStudentId());
            statement.setTimestamp(4, answer.getDate());
            statement.setInt(5, answer.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Update answer: " + e);
        }
    }

    public Answer getAnswer(int id) {
        String query = "SELECT * FROM Answer WHERE id = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Answer answer = new Answer();
                answer.setId(resultSet.getInt("id"));
                answer.setAnswer(resultSet.getString("answer"));
                answer.setQuestionId(resultSet.getInt("questionId"));
                answer.setStudentId(resultSet.getInt("studentId"));
                answer.setDate(resultSet.getTimestamp("date"));
                GradeDAO gradeDao = new GradeDAO();
                StudentDAO studentDao = new StudentDAO();
                answer.setStudent(studentDao.getStudent(resultSet.getInt("studentId")));
                answer.setGrade(gradeDao.getAllGradesByAnswerId(resultSet.getInt("id")));
                return answer;
            }
        } catch (SQLException e) {
            System.out.println("Get answer: " + e);
        }
        return null;
    }

    public List<Answer> getAllAnswers() {
        List<Answer> answers = new ArrayList<>();
        String query = "SELECT * FROM Answer";
        try (PreparedStatement statement = conn.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Answer answer = new Answer();
                answer.setId(resultSet.getInt("id"));
                answer.setAnswer(resultSet.getString("answer"));
                answer.setQuestionId(resultSet.getInt("questionId"));
                answer.setStudentId(resultSet.getInt("studentId"));
                answer.setDate(resultSet.getTimestamp("date"));
                GradeDAO gradeDao = new GradeDAO();
                StudentDAO studentDao = new StudentDAO();
                answer.setStudent(studentDao.getStudent(resultSet.getInt("studentId")));
                answer.setGrade(gradeDao.getAllGradesByAnswerId(resultSet.getInt("id")));
                answers.add(answer);
            }
        } catch (SQLException e) {
            System.out.println("Get all answers: " + e);
        }
        return answers;
    }

    public List<Answer> getAllAnswersByQuestionId(int questionId) {
        List<Answer> answers = new ArrayList<>();
        String query = "SELECT * FROM Answer where questionId = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, questionId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Answer answer = new Answer();
                answer.setId(resultSet.getInt("id"));
                answer.setAnswer(resultSet.getString("answer"));
                answer.setQuestionId(resultSet.getInt("questionId"));
                answer.setStudentId(resultSet.getInt("studentId"));
                answer.setDate(resultSet.getTimestamp("date"));
                GradeDAO gradeDao = new GradeDAO();
                StudentDAO studentDao = new StudentDAO();
                answer.setStudent(studentDao.getStudent(resultSet.getInt("studentId")));
                answer.setGrade(gradeDao.getAllGradesByAnswerId(resultSet.getInt("id")));
                answers.add(answer);
            }
        } catch (SQLException e) {
            System.out.println("Get all answers: " + e);
        }
        return answers;
    }

    public int deleteAnswer(int id) {
        String query = "DELETE FROM Answer WHERE id = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            return statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Delete answer: " + e);
        }
        return 0;
    }
}
