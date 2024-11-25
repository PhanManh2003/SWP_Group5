/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Model;

public class Lesson {
    private int lessionId;
    private int courseId;
    private String title;
    private String videoUrl;
    private String docsLink;
    private String description;
    private String status;
    private StudentLession studentLession;
    private int adminID;
    private Admin admin;
    
    public Lesson() {
    }

    public Lesson(int lessionId, int courseId, String title, String videoUrl, String docsLink, String description, String status) {
        this.lessionId = lessionId;
        this.courseId = courseId;
        this.title = title;
        this.videoUrl = videoUrl;
        this.docsLink = docsLink;
        this.description = description;
        this.status = status;
    }
    

    public int getLessionId() {
        return lessionId;
    }

    public void setLessionId(int lessionId) {
        this.lessionId = lessionId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getDocsLink() {
        return docsLink;
    }

    public void setDocsLink(String docsLink) {
        this.docsLink = docsLink;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public StudentLession getStudentLession() {
        return studentLession;
    }

    public void setStudentLession(StudentLession studentLession) {
        this.studentLession = studentLession;
    }

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
    
}

