/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DBConnection.DBConnection;
import Model.ClassInfo;
import Model.Course;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class CourseDAO {

    private Connection conn;

    public CourseDAO() {
        try {
            conn = DBConnection.connect();
        } catch (Exception e) {
            System.out.println("Connection fail: " + e);
        }
    }

    public void addCourse(Course course) {
        String query = "INSERT INTO Courses (CourseName, CourseCode, Status) VALUES (?, ?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, course.getCourseName());
            statement.setString(2, course.getCourseCode());
            statement.setInt(3, course.getStatus());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Add course: " + e);
        }
    }

    public void updateCourse(Course course) {
        String query = "UPDATE Courses SET CourseName = ?, CourseCode = ?, Status = ? WHERE CourseID = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, course.getCourseName());
            statement.setString(2, course.getCourseCode());
            statement.setInt(3, course.getStatus());
            statement.setInt(4, course.getCourseID());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Update course: " + e);
        }
    }

    public boolean isCourseCodeExists(String courseCode, int id) {
        String query = "SELECT COUNT(*) FROM Courses WHERE CourseCode = ? and courseID !=?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, courseCode);
            statement.setInt(2, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            System.out.println("Check course code existence: " + e);
        }
        return false;
    }

    public Course getCourse(int courseID) {
        String query = "SELECT * FROM Courses WHERE CourseID = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, courseID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Course course = new Course();
                course.setCourseID(resultSet.getInt("CourseID"));
                course.setCourseName(resultSet.getString("CourseName"));
                course.setCourseCode(resultSet.getString("CourseCode"));
                course.setStatus(resultSet.getInt("Status"));
                return course;
            }
        } catch (SQLException e) {
            System.out.println("Get course: " + e);
        }
        return null;
    }

    public List<Course> getCourseByStudent(int studentID) {
        String query = "SELECT * FROM Courses WHERE CourseID = ?";
        List<Course> courses = new ArrayList<>();
        ClassDAO classDao = new ClassDAO();
        List<ClassInfo> classInfors = classDao.getAllClassesActiveJoin(studentID);
        LessionDAO lessionDao = new LessionDAO();
        StudentLessionDAO studentLessionDao = new StudentLessionDAO();
        for (int i = 0; i < classInfors.size() ;i++) {
            boolean isHave = false;
            for (Course course : courses) {
                if(course.getCourseID()  == classInfors.get(i).getCourseID())  {
                    isHave = true;
                }
            }
            if(isHave) {
                continue;
            }
            try (PreparedStatement statement = conn.prepareStatement(query)) {
                statement.setInt(1, classInfors.get(i).getCourseID());
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    Course course = new Course();
                    course.setCourseID(resultSet.getInt("CourseID"));
                    course.setCourseName(resultSet.getString("CourseName"));
                    course.setCourseCode(resultSet.getString("CourseCode"));
                    course.setStatus(resultSet.getInt("Status"));
                    course.setLession(lessionDao.getLessionsByCourseId(resultSet.getInt("CourseID"), studentID));
                    course.setStudentLession(studentLessionDao.getCompletedLessionsByCourse(studentID, resultSet.getInt("CourseID")));
                    courses.add(course);
                }
            } catch (SQLException e) {
                System.out.println("Get course: " + e);
            }
        }

        return courses;
    }

    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT * FROM Courses";
        try (PreparedStatement statement = conn.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Course course = new Course();
                course.setCourseID(resultSet.getInt("CourseID"));
                course.setCourseName(resultSet.getString("CourseName"));
                course.setCourseCode(resultSet.getString("CourseCode"));
                course.setStatus(resultSet.getInt("Status"));
                courses.add(course);
            }
        } catch (SQLException e) {
            System.out.println("Get all courses: " + e);
        }
        return courses;
    }

    public int deleteCourse(int courseID) {
        String query = "DELETE FROM Courses WHERE CourseID = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, courseID);
            return statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Delete course: " + e);
        }
        return 0;
    }
}
