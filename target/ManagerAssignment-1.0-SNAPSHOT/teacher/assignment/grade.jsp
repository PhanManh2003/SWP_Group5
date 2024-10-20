<%-- 
    Document   : grade
    Created on : Jun 11, 2024, 5:29:15 PM
    Author     : HP
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="getStudent" class="DAO.StudentDAO" />
<jsp:useBean id="getClass" class="DAO.ClassDAO" />
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Submit assignment</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <%@ include file="../component/header.jsp" %>
        <div class="container">
            <h2>Grade for submission</h2>
            <c:if test="${errorMessage != null}">
                <div class="alert alert-danger" role="alert">
                    ${errorMessage}
                </div>
            </c:if>
            <c:set var="studentSubmit" value="${getStudent.getStudent(submission.studentID)}" />
            <c:set var="classSubmit" value="${getClass.getClass(assignment.classID)}" />
            <form action="TeacherAssignmentController?action=grade" method="post">
                <input type="hidden" name="submissionID" value="${submission.submissionID}">
                <input type="hidden" name="classID" value="${assignment.classID}">
                <input type="hidden" name="assignmentID" value="${submission.assignmentID}">
                <div class="form-group">
                    <label for="file">Submission:</label>
                    <a class="btn btn-info form-control" href="FileDownloadController?file=${submission.submissionContent}">
                        ${submission.submissionContent}
                    </a>
                </div>
                <div class="form-group">
                    <label for="file">Class name: </label>
                    <input class="form-control" type="text" value="${classSubmit.className}" readonly/>
                </div>
                <div class="form-group">
                    <label for="file">Student name: </label>
                    <input class="form-control" type="text" value="${studentSubmit.name}" readonly/>
                </div>
                <div class="form-group">
                    <label for="file">Student email: </label>
                    <input class="form-control" type="text" value="${studentSubmit.email}" readonly/>
                </div>
                <div class="form-group">
                    <label for="file">Grade for submission</label>
                    <input class="form-control" min="0" max="10" type="text" value="${submission.grade == -1 ? 0 : submission.grade}" required name="grade"/>
                </div>
                <button type="submit" class="btn btn-primary">Grade</button>
            </form>
        </div>
    </body>
</html>
