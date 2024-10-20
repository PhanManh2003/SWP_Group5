<%-- 
    Document   : edit
    Created on : Jun 11, 2024, 2:16:10 AM
    Author     : HP
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Edit Assignment</title>
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
            <h1>Edit Assignment</h1>
            <c:if test="${not empty errorMessage}">
                <div class="alert alert-danger" role="alert">
                    <strong>Error:</strong> ${errorMessage}
                </div>
            </c:if>
            <form action="TeacherAssignmentController?action=edit" method="post" class="mb-4">
                <input type="hidden" name="assignmentID" value="${assignment.assignmentID}">
                <div class="form-group">
                    <label for="classID">Class ID:</label>
                    <input type="text" class="form-control" id="classID" name="classID" value="${assignment.classID}" readonly>
                </div>
                <div class="form-group">
                    <label for="title">Title:</label>
                    <input type="text" class="form-control" id="title" name="title" value="${assignment.title}" required>
                </div>
                <div class="form-group">
                    <label for="desc">Description:</label>
                    <textarea class="form-control" id="desc" name="desc" rows="5" required>${assignment.description}</textarea>
                </div>
                <div class="form-group">
                    <label for="duedate">Due date:</label>
                    <input type="datetime-local" class="form-control" id="duedate" name="duedate" value="${assignment.dueDate}" required>
                </div>
                <div class="form-group">
                    <label for="type">Type:</label>
                    <select class="form-control" id="type" name="type" required>
                        <option value="3" ${3 == assignment.type ? 'selected' : ''}>Caption</option>
                        <option value="2" ${2 == assignment.type ? 'selected' : ''}>Assignment</option>
                        <option value="1" ${1 == assignment.type ? 'selected' : ''}>Lab</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="status">Status:</label>
                    <select class="form-control" id="status" name="status" required>
                        <option value="1" ${1 == assignment.status ? 'selected' : ''}>Active</option>
                        <option value="0" ${0 == assignment.status ? 'selected' : ''}>Inactive</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Update Assignment</button>
            </form>
        </div>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
        <script>
            var now = moment().utcOffset(7);
            var nowString = now.format('YYYY-MM-DDTHH:mm');
            document.getElementById("duedate").value = nowString;
            document.getElementById("duedate").setAttribute("min", nowString);
        </script>
    </body>
</html>
