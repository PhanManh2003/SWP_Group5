<%-- 
    Document   : edit
    Created on : Jun 7, 2024, 4:04:13 PM
    Author     : HP
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Edit Class</title>
        <!-- Bootstrap CSS -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
         <%@ include file="../component/header.jsp" %>
        <div class="container">
            <h2>Edit Class</h2>
            <c:if test="${param.error != null}">
                <div class="alert alert-danger" role="alert">
                    ${param.error}
                </div>
            </c:if>
            <form action="EditClassController" method="post" class="mb-4">
                <div class="form-group">
                    <input type="hidden" name="classID" value="${classInfo.classID}">
                    <label for="className">Class Name:</label>
                    <input type="text" class="form-control" id="className" name="className" value="${classInfo.className}" required>
                </div>
                <div class="form-group">
                    <label for="status">Status:</label>
                    <select class="form-control" id="status" name="status" required>
                        <option value="1" ${classInfo.status == 1 ? 'selected' : ''}>Active</option>
                        <option value="0" ${classInfo.status == 0 ? 'selected' : ''}>Inactive</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="teacherID">Teacher:</label>
                    <select class="form-control" id="teacherID" name="teacherID" required>
                        <c:forEach var="teacher" items="${teachers}">
                            <option value="${teacher.teacherID}" ${teacher.teacherID == classInfo.teacherID ? 'selected' : ''}>${teacher.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="courseID">Course:</label>
                    <select class="form-control" id="courseID" name="courseID" required>
                        <c:forEach var="course" items="${courses}">
                            <option value="${course.courseID}" ${course.courseID == classInfo.courseID ? 'selected' : ''}>${course.courseName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="semesterID">Semester: </label>
                    <select class="form-control" id="semesterID" name="semesterID" required>
                        <c:forEach var="semester" items="${semesters}">
                            <option value="${semester.semesterID}" ${semester.semesterID == classInfo.semesterID ? 'selected' : ''}>${semester.semesterName}</option>
                        </c:forEach>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Update Class</button>
            </form>
        </div>
    </body>
</html>
