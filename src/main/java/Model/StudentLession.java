/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Model;

import java.sql.Date;

/**
 *
 * @author HP
 */
public class StudentLession {
    private int studentId;
    private int lessionId;
    private boolean isCompleted;
    private Date completionDate;

    public StudentLession() {}

    public StudentLession(int studentId, int lessionId, boolean isCompleted, Date completionDate) {
        this.studentId = studentId;
        this.lessionId = lessionId;
        this.isCompleted = isCompleted;
        this.completionDate = completionDate;
    }

    // Getters và Setters
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getLessionId() {
        return lessionId;
    }

    public void setLessionId(int lessionId) {
        this.lessionId = lessionId;
    }

    public boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }
}

