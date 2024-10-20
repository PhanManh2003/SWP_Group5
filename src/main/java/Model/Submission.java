/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Timestamp;

/**
 *
 * @author HP
 */
public class Submission {

    private int submissionID;
    private String submissionContent;
    private int studentID;
    private int assignmentID;
    private Timestamp submissionDate;
    private float grade;

    public Submission() {
    }

    public Submission(int submissionID, String submissionContent, int studentID, int assignmentID, Timestamp submissionDate, float grade) {
        this.submissionID = submissionID;
        this.submissionContent = submissionContent;
        this.studentID = studentID;
        this.assignmentID = assignmentID;
        this.submissionDate = submissionDate;
        this.grade = grade;
    }
    

    public int getSubmissionID() {
        return submissionID;
    }

    public void setSubmissionID(int submissionID) {
        this.submissionID = submissionID;
    }

    public String getSubmissionContent() {
        return submissionContent;
    }

    public void setSubmissionContent(String submissionContent) {
        this.submissionContent = submissionContent;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getAssignmentID() {
        return assignmentID;
    }

    public void setAssignmentID(int assignmentID) {
        this.assignmentID = assignmentID;
    }

    public Timestamp getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Timestamp submissionDate) {
        this.submissionDate = submissionDate;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }
}
