<%-- 
    Document   : add
    Created on : Jun 7, 2024, 4:19:36 PM
    Author     : HP
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Add Semester</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <%@ include file="../component/header.jsp" %>
        <div class="container">
            <h2>Add Semester</h2>
            <span class="badge badge-danger">${errorMessage}</span>
            <c:if test="${errorMessage != null}">
                <div class="alert alert-danger" role="alert">
                    ${errorMessage}
                </div>
            </c:if>
            <form action="AddSemesterController" method="post" class="mb-4">
                <div class="form-group">
                    <label for="semesterName">Semester Name:</label>
                    <input type="text" class="form-control" id="semesterName" name="semesterName" required>
                </div>
                <div class="form-group">
                    <label for="year">Year:</label>
                    <input type="number" class="form-control" id="year" name="year" required>
                </div>
                <div class="form-group">
                    <label for="status">Status:</label>
                    <select class="form-control" id="status" name="status">
                        <option value="1">Active</option>
                        <option value="0">Inactive</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Add Semester</button>
            </form>
            <a href="ListSemestersController" class="btn btn-secondary">Back to List</a>
        </div>
    </body>
</html>

