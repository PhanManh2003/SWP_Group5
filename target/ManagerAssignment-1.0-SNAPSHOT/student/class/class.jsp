

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="getTeacher" class="DAO.TeacherDAO" />
<jsp:useBean id="getCourse" class="DAO.CourseDAO" />
<jsp:useBean id="getStudentClasses" class="DAO.StudentClassDAO" />
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>List of Classes</title>
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
            <h2>List of Classes</h2>
            <div class="mt-3">
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
            </div>
            <div class="row">
                <c:forEach var="cla" items="${classInfors}">
                    <c:set value="${getCourse.getCourse(cla.courseID)}" var="course" />
                    <c:set value="${getTeacher.getTeacher(cla.teacherID)}" var="teacher" />
                    <c:set value="${getStudentClasses.getStudentsByClassId(sessionScope.studentID, cla.classID)}" var="classesJoined" />
                    <div class="col-md-3 mb-4">
                        <div class="card h-100">
                            <div class="card-body">
                                <h5 class="card-title">${cla.className}</h5>
                                <h6 class="card-subtitle mb-2 text-muted">${teacher.name}</h6>
                                <p class="card-text">
                                    <strong>Class ID:</strong> ${cla.classID}<br>
                                    <strong>Status:</strong> ${cla.status == 1 ? 'Active' : 'Inactive'}<br>
                                    <strong>Course:</strong> ${course.courseCode}<br>
                                    <strong>Semester:</strong> ${cla.semesterName}
                                </p>
                                <a href="StudentClassController?action=view&classID=${cla.classID}" class="btn btn-info">View</a>
                                <c:if test="${classesJoined == null}">
                                    <c:if test="${joined == null}">
                                        <a onclick="return confirm('Are you sure to join this class?')" href="StudentClassController?action=ask-join&classID=${cla.classID}" class="btn btn-warning">Ask to join</a>
                                    </c:if>
                                </c:if>
                                <c:if test="${classesJoined != null && classesJoined.status == 0}">
                                    <a class="btn btn-primary">Waiting</a>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </body>
</html>
