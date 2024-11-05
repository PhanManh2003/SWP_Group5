/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DBConnection.DBConnection;
import Model.Question;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionDAO {

    private Connection conn;

    public QuestionDAO() {
        try {
            conn = DBConnection.connect();
        } catch (Exception e) {
            System.out.println("Connection fail: " + e);
        }
    }

    public void addQuestion(Question question) {
        String query = "INSERT INTO Question (Question, Status, Slot, ClassId) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, question.getQuestion());
            statement.setInt(2, question.getStatus());
            statement.setInt(3, question.getSlot());
            statement.setInt(4, question.getClassId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Add question: " + e);
        }
    }

    public void updateQuestion(Question question) {
        String query = "UPDATE Question SET Question = ?, Status = ?, Slot = ?, ClassId = ? WHERE id = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, question.getQuestion());
            statement.setInt(2, question.getStatus());
            statement.setInt(3, question.getSlot());
            statement.setInt(4, question.getClassId());
            statement.setInt(5, question.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Update question: " + e);
        }
    }

    public Question getQuestion(int id) {
        String query = "SELECT * FROM Question WHERE id = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Question question = new Question();
                question.setId(resultSet.getInt("id"));
                question.setQuestion(resultSet.getString("Question"));
                question.setStatus(resultSet.getInt("Status"));
                question.setSlot(resultSet.getInt("Slot"));
                question.setClassId(resultSet.getInt("ClassId"));
                ClassDAO classDao = new ClassDAO();
                question.setClassInfo(classDao.getClass(resultSet.getInt("ClassId")));
                return question;
            }
        } catch (SQLException e) {
            System.out.println("Get question: " + e);
        }
        return null;
    }

    public List<Question> getAllQuestions() {
        List<Question> questions = new ArrayList<>();
        String query = "SELECT * FROM Question";
        try (PreparedStatement statement = conn.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Question question = new Question();
                question.setId(resultSet.getInt("id"));
                question.setQuestion(resultSet.getString("Question"));
                question.setStatus(resultSet.getInt("Status"));
                question.setSlot(resultSet.getInt("Slot"));
                question.setClassId(resultSet.getInt("ClassId"));
                ClassDAO classDao = new ClassDAO();
                question.setClassInfo(classDao.getClass(resultSet.getInt("ClassId")));
                questions.add(question);
            }
        } catch (SQLException e) {
            System.out.println("Get all questions: " + e);
        }
        return questions;
    }

    public List<Question> getAllQuestionsByClassId(int classId) {
        List<Question> questions = new ArrayList<>();
        String query = "SELECT * FROM Question where ClassId=?";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, classId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Question question = new Question();
                question.setId(resultSet.getInt("id"));
                question.setQuestion(resultSet.getString("Question"));
                question.setStatus(resultSet.getInt("Status"));
                question.setSlot(resultSet.getInt("Slot"));
                question.setClassId(resultSet.getInt("ClassId"));
                ClassDAO classDao = new ClassDAO();
                question.setClassInfo(classDao.getClass(resultSet.getInt("ClassId")));
                questions.add(question);
            }
        } catch (SQLException e) {
            System.out.println("Get all questions: " + e);
        }
        return questions;
    }
    
    public List<Question> getAllQuestionsByClassIdByStudent(int classId) {
        List<Question> questions = new ArrayList<>();
        String query = "SELECT * FROM Question where ClassId=? and status = 1";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, classId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Question question = new Question();
                question.setId(resultSet.getInt("id"));
                question.setQuestion(resultSet.getString("Question"));
                question.setStatus(resultSet.getInt("Status"));
                question.setSlot(resultSet.getInt("Slot"));
                question.setClassId(resultSet.getInt("ClassId"));
                ClassDAO classDao = new ClassDAO();
                question.setClassInfo(classDao.getClass(resultSet.getInt("ClassId")));
                questions.add(question);
            }
        } catch (SQLException e) {
            System.out.println("Get all questions: " + e);
        }
        return questions;
    }

    public int deleteQuestion(int id) {
        String query = "DELETE FROM Question WHERE id = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            return statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Delete question: " + e);
        }
        return 0;
    }
}
