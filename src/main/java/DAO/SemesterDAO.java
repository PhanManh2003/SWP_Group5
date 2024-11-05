/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DBConnection.DBConnection;
import Model.Semester;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class SemesterDAO {

    private Connection conn;

    public SemesterDAO() {
        try {
            conn = DBConnection.connect();
        } catch (Exception e) {
            System.out.println("Connection fail: " + e);
        }
    }

    public void addSemester(Semester semester) {
        String query = "INSERT INTO Semesters (SemesterName, Year, Status) VALUES (?, ?, ?)";
        try ( PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, semester.getSemesterName());
            statement.setInt(2, semester.getYear());
            statement.setInt(3, semester.getStatus());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Add semester: " + e);
        }
    }

    public void updateSemester(Semester semester) {
        String query = "UPDATE Semesters SET SemesterName = ?, Year = ?, Status = ? WHERE SemesterID = ?";
        try ( PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, semester.getSemesterName());
            statement.setInt(2, semester.getYear());
            statement.setInt(3, semester.getStatus());
            statement.setInt(4, semester.getSemesterID());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Update semester: " + e);
        }
    }

    public int deleteSemester(int semesterID) {
        String query = "DELETE FROM Semesters WHERE SemesterID = ?";
        try ( PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, semesterID);
            return statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Delete semester: " + e);
        }
        return 0;
    }

    public Semester getSemester(int semesterID) {
        Semester semester = null;
        String query = "SELECT * FROM Semesters WHERE SemesterID = ?";
        try ( PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, semesterID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                semester = new Semester();
                semester.setSemesterID(resultSet.getInt("SemesterID"));
                semester.setSemesterName(resultSet.getString("SemesterName"));
                semester.setYear(resultSet.getInt("Year"));
                semester.setStatus(resultSet.getInt("Status"));
            }
        } catch (SQLException e) {
            System.out.println("Get semester: " + e);
        }
        return semester;
    }
    
    public boolean getSemesterName(String name, int id) {
        Semester semester = null;
        String query = "SELECT * FROM Semesters WHERE SemesterID != ? and SemesterName=?";
        try ( PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.setString(2, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0; 
            }
        } catch (SQLException e) {
            System.out.println("Get semester: " + e);
        }
        return false;
    }

    public List<Semester> getAllSemesters() {
        List<Semester> semesters = new ArrayList<>();
        String query = "SELECT * FROM Semesters";
        try ( PreparedStatement statement = conn.prepareStatement(query);  ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Semester semester = new Semester();
                semester.setSemesterID(resultSet.getInt("SemesterID"));
                semester.setSemesterName(resultSet.getString("SemesterName"));
                semester.setYear(resultSet.getInt("Year"));
                semester.setStatus(resultSet.getInt("Status"));
                semesters.add(semester);
            }
        } catch (SQLException e) {
            System.out.println("Get all semesters: " + e);
        }
        return semesters;
    }
}
