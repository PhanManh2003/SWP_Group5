<%-- 
    Document   : question-list
    Created on : Jul 17, 2024, 7:26:42 PM
    Author     : HP
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Questions List</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <%@ include file="../component/header.jsp" %>
        <div class="container">
            <h2>Questions List</h2>
            <a href="teacher-question?action=new&classID=${classId}" class="btn btn-primary">Add New Question</a>
            <table class="table table-striped mt-3">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Question</th>
                        <th>Status</th>
                        <th>Slot</th>
                        <th>Class ID</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="question" items="${listQuestions}">
                        <tr>
                            <td>${question.id}</td>
                            <td>${question.question}</td>
                            <td>${question.status == 1 ? 'Active' : 'Inactive'}</td>
                            <td>${question.slot}</td>
                            <td>${question.classInfo.className}</td>
                            <td>
                                <a href="teacher-question?action=edit&id=${question.id}&classID=${classId}" class="btn btn-warning">Edit</a>
                                <a href="teacher-question?action=answer&id=${question.id}&classID=${classId}" class="btn btn-success">Answer</a>
                                <a href="teacher-question?action=delete&id=${question.id}&classID=${classId}" class="btn btn-danger">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
