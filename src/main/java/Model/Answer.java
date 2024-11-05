/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Timestamp;
import java.util.List;

public class Answer {

    private int id;
    private String answer;
    private int questionId;
    private int studentId;
    private Timestamp date;
    private List<Grade> grade;
    private Student student;

    public Answer() {
    }

    public Answer(int id, String answer, int questionId, int studentId, Timestamp date) {
        this.id = id;
        this.answer = answer;
        this.questionId = questionId;
        this.studentId = studentId;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public void setGrade(List<Grade> grade) {
        this.grade = grade;
    }

    public List<Grade> getGrade() {
        return grade;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
