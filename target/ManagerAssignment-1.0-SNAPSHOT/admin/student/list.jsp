<%-- 
    Document   : list
    Created on : Jun 7, 2024, 12:16:22 PM
    Author     : HP
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>List Students</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <style>
            .container {
                margin-top: 50px;
            }
        </style>
    </head>
    <body>
        <%@ include file="../component/header.jsp" %>
        <div class="container">
            <h2>List Students</h2>
            <a href="AddStudentController" class="btn btn-primary mb-4">Add Student</a>
            <c:if test="${param.error != null}">
                <div class="alert alert-danger" role="alert">
                    ${param.error}
                </div>
            </c:if>
            <c:if test="${param.success != null}">
                <div class="alert alert-success" role="alert">
                    ${param.success}
                </div>
            </c:if>
            <table class="table" id="data-table">
                <thead class="thead-dark">
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Phone</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="student" items="${students}">
                        <tr>
                            <td>${student.studentID}</td>
                            <td>${student.name}</td>
                            <td>${student.email}</td>
                            <td>${student.phone}</td>
                            <td>${student.status == 1 ? "Active" : "Inactive"}</td>
                            <td>
                                <a href="EditStudentController?studentID=${student.studentID}" class="btn btn-primary">Edit</a>
                                <a onClick="return confirm('Are your sure to delete it?')" href="DeleteStudentController?studentID=${student.studentID}" class="btn btn-danger">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
