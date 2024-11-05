/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DBConnection.DBConnection;
import Model.ClassInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ClassDAO {

    private Connection conn;

    public ClassDAO() {
        try {
            conn = DBConnection.connect();
        } catch (Exception e) {
            System.out.println("Connection fail: " + e);
        }
    }

    public int addClass(ClassInfo classInfo) {
        String query = "INSERT INTO Classes (ClassName, Status, TeacherID, CourseID, semesterId) VALUES (?, ?, ?, ?, ?)";
        try ( PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, classInfo.getClassName());
            statement.setInt(2, classInfo.getStatus());
            statement.setInt(3, classInfo.getTeacherID());
            statement.setInt(4, classInfo.getCourseID());
            statement.setInt(5, classInfo.getSemesterID());
            return statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Add class error: " + e);
        }
        return 0;
    }
//  teacher

    public List<ClassInfo> getClasseActiveByTeacher(int id) {
        String query = "SELECT C.*, s.semesterName FROM Classes as C JOIN Semesters as S on c.semesterID = s.semesterID JOIN "
                + "Courses as Co on Co.courseId = c.CourseId where co.status =1 and s.status =1 and s.status=1 and c.teacherId=?";
        List<ClassInfo> classes = new ArrayList<>();
        try ( PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ClassInfo classInfo = new ClassInfo();
                classInfo.setClassID(resultSet.getInt("ClassID"));
                classInfo.setClassName(resultSet.getString("ClassName"));
                classInfo.setSemesterName(resultSet.getString("semesterName"));
                classInfo.setStatus(resultSet.getInt("Status"));
                classInfo.setTeacherID(resultSet.getInt("TeacherID"));
                classInfo.setSemesterID(resultSet.getInt("semesterID"));
                classInfo.setCourseID(resultSet.getInt("CourseID"));
                classes.add(classInfo);
            }
        } catch (SQLException e) {
            System.out.println("Get class error active: " + e);
        }
        return classes;
    }

    public ClassInfo getClasseActiveByTeacherById(int id) {
        String query = "SELECT C.*, s.semesterName FROM Classes as C JOIN Semesters as S on c.semesterID = s.semesterID JOIN "
                + "Courses as Co on Co.courseId = c.CourseId where co.status =1 and s.status =1 and s.status=1 and c.classID=?";
        try ( PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ClassInfo classInfo = new ClassInfo();
                classInfo.setClassID(resultSet.getInt("ClassID"));
                classInfo.setClassName(resultSet.getString("ClassName"));
                classInfo.setSemesterName(resultSet.getString("semesterName"));
                classInfo.setStatus(resultSet.getInt("Status"));
                classInfo.setTeacherID(resultSet.getInt("TeacherID"));
                classInfo.setSemesterID(resultSet.getInt("semesterID"));
                classInfo.setCourseID(resultSet.getInt("CourseID"));
                return classInfo;
            }
        } catch (SQLException e) {
            System.out.println("Get class by id error active: " + e);
        }
        return null;
    }

//  end teacher
//  student
    public ClassInfo getClasseActiveByStudentById(int id) {
        String query = "SELECT C.*, s.semesterName FROM Classes as C JOIN Semesters as S on c.semesterID = s.semesterID JOIN "
                + "Courses as Co on Co.courseId = c.CourseId where co.status =1 and s.status =1 and s.status=1 and c.classID=?";
        try ( PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ClassInfo classInfo = new ClassInfo();
                classInfo.setClassID(resultSet.getInt("ClassID"));
                classInfo.setClassName(resultSet.getString("ClassName"));
                classInfo.setSemesterName(resultSet.getString("semesterName"));
                classInfo.setStatus(resultSet.getInt("Status"));
                classInfo.setTeacherID(resultSet.getInt("TeacherID"));
                classInfo.setSemesterID(resultSet.getInt("semesterID"));
                classInfo.setCourseID(resultSet.getInt("CourseID"));
                return classInfo;
            }
        } catch (SQLException e) {
            System.out.println("Get class by id error active: " + e);
        }
        return null;
    }

    public List<ClassInfo> getAllClassesActive() {
        String query = "SELECT C.*, s.semesterName FROM Classes as C JOIN Semesters as S on c.semesterID = s.semesterID JOIN "
                + "Courses as Co on Co.courseId = c.CourseId where co.status =1 and s.status =1 and s.status=1";
        List<ClassInfo> classes = new ArrayList<>();
        try ( PreparedStatement statement = conn.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ClassInfo classInfo = new ClassInfo();
                classInfo.setClassID(resultSet.getInt("ClassID"));
                classInfo.setClassName(resultSet.getString("ClassName"));
                classInfo.setSemesterName(resultSet.getString("semesterName"));
                classInfo.setStatus(resultSet.getInt("Status"));
                classInfo.setTeacherID(resultSet.getInt("TeacherID"));
                classInfo.setSemesterID(resultSet.getInt("semesterID"));
                classInfo.setCourseID(resultSet.getInt("CourseID"));
                classes.add(classInfo);
            }
        } catch (SQLException e) {
            System.out.println("Get class active error: " + e);
        }
        return classes;
    }

    public List<ClassInfo> getAllClassesActiveJoin(int studentId) {
        String query = "SELECT C.*, s.semesterName FROM Classes as C "
                + "JOIN Semesters as S on c.semesterID = s.semesterID JOIN "
                + "Courses as Co on Co.courseId = c.CourseId "
                + "JOIN studentClass as SC on SC.classId = C.classID "
                + "where co.status =1 and s.status =1 and s.status=1 and Sc.studentId =?";
        List<ClassInfo> classes = new ArrayList<>();
        try ( PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, studentId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ClassInfo classInfo = new ClassInfo();
                classInfo.setClassID(resultSet.getInt("ClassID"));
                classInfo.setClassName(resultSet.getString("ClassName"));
                classInfo.setSemesterName(resultSet.getString("semesterName"));
                classInfo.setStatus(resultSet.getInt("Status"));
                classInfo.setTeacherID(resultSet.getInt("TeacherID"));
                classInfo.setSemesterID(resultSet.getInt("semesterID"));
                classInfo.setCourseID(resultSet.getInt("CourseID"));
                classes.add(classInfo);
            }
        } catch (SQLException e) {
            System.out.println("Get class active error: " + e);
        }
        return classes;
    }
//  end student

    public ClassInfo getClass(int classID) {
        String query = "SELECT * FROM Classes WHERE ClassID = ?";
        try ( PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, classID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                ClassInfo classInfo = new ClassInfo();
                classInfo.setClassID(resultSet.getInt("ClassID"));
                classInfo.setClassName(resultSet.getString("ClassName"));
                classInfo.setStatus(resultSet.getInt("Status"));
                classInfo.setTeacherID(resultSet.getInt("TeacherID"));
                classInfo.setSemesterID(resultSet.getInt("semesterID"));
                classInfo.setCourseID(resultSet.getInt("CourseID"));
                return classInfo;
            }
        } catch (SQLException e) {
            System.out.println("Get class error: " + e);
        }
        return null;
    }

    public boolean getExistClass(int classID, String className) {
        String query = "SELECT * FROM Classes WHERE ClassID != ? and className=?";
        try ( PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, classID);
            statement.setString(2, className);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("ClassID") > 0;
            }
        } catch (SQLException e) {
            System.out.println("Get class error: " + e);
        }
        return false;
    }

    public List<ClassInfo> getClasses() {
        String query = "SELECT C.*, s.semesterName FROM Classes as C JOIN Semesters as S on c.semesterID = s.semesterID";
        List<ClassInfo> classes = new ArrayList<>();
        try ( PreparedStatement statement = conn.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ClassInfo classInfo = new ClassInfo();
                classInfo.setClassID(resultSet.getInt("ClassID"));
                classInfo.setClassName(resultSet.getString("ClassName"));
                classInfo.setSemesterName(resultSet.getString("semesterName"));
                classInfo.setStatus(resultSet.getInt("Status"));
                classInfo.setTeacherID(resultSet.getInt("TeacherID"));
                classInfo.setSemesterID(resultSet.getInt("semesterID"));
                classInfo.setCourseID(resultSet.getInt("CourseID"));
                classes.add(classInfo);
            }
        } catch (SQLException e) {
            System.out.println("Get class error: " + e);
        }
        return classes;
    }

    public int updateClass(ClassInfo classInfo) {
        String query = "UPDATE Classes SET ClassName = ?, Status = ?, TeacherID = ?, CourseID = ?, semesterID=? WHERE ClassID = ?";
        try ( PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, classInfo.getClassName());
            statement.setInt(2, classInfo.getStatus());
            statement.setInt(3, classInfo.getTeacherID());
            statement.setInt(4, classInfo.getCourseID());
            statement.setInt(5, classInfo.getSemesterID());
            statement.setInt(6, classInfo.getClassID());
            return statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Update class error: " + e);
        }
        return 0;
    }

    public int deleteClass(int classID) {
        String query = "DELETE FROM Classes WHERE ClassID = ?";
        try ( PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, classID);
            return statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Delete class error: " + e);
        }
        return 0;
    }
}
