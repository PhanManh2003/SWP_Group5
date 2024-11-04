/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Model;

import java.util.List;

/**
 *
 * @author HP
 */
public class Course {
    private int courseID;
    private String courseName;
    private String courseCode;
    private int status;
    private List<StudentLession> studentLession;
    private List<Lession> lession;

    public Course() {
    }

    public Course(int courseID, String courseName, String courseCode,  int status) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.status = status;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<StudentLession> getStudentLession() {
        return studentLession;
    }

    public void setStudentLession(List<StudentLession> studentLession) {
        this.studentLession = studentLession;
    }

    public List<Lession> getLession() {
        return lession;
    }

    public void setLession(List<Lession> lession) {
        this.lession = lession;
    }
}
