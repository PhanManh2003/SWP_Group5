<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="getTeacher" class="DAO.TeacherDAO" />
<jsp:useBean id="getCourse" class="DAO.CourseDAO" />
<%@ include file="../../commonStudent/head.jsp" %>
<div class="container mt-4">
    <h2 class="mb-4">View Class</h2>
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

    <c:choose>
        <c:when test="${studentJoin != null && studentJoin.status == 1}">
            <a href="StudentClassController?action=assignment&classID=${currentClass.classID}" class="btn btn-info mb-3">View Assignment</a>
            <a href="LessonStudentController?classID=${currentClass.classID}" class="btn btn-warning mb-3">Go to course</a>
        </c:when>
        <c:when test="${studentJoin != null && studentJoin.status == 0}">
            <a class="btn btn-primary mb-3">Waiting for Approval</a>
        </c:when>
        <c:otherwise>
            <a onclick="return confirm('Are you sure to join this class?')" href="StudentClassController?action=ask-join&classID=${currentClass.classID}" class="btn btn-warning mb-3">Ask to Join</a>
        </c:otherwise>
    </c:choose>
    <form action="AddClassController" method="post" class="mb-4">
        <div class="form-group">
            <label for="className">Class Name:</label>
            <input type="text" class="form-control" value="${currentClass.className}" readonly>
        </div>
        <div class="form-group">
            <label for="status">Status:</label>
            <input type="text" class="form-control" value="${currentClass.status == 1 ? 'Active' : 'Inactive'}" readonly>
        </div>
        <div class="form-group">
            <label for="teacherID">Teacher:</label>
            <c:set value="${getTeacher.getTeacher(currentClass.teacherID)}" var="teacher" />
            <input type="text" class="form-control" value="${teacher.name}" readonly>
        </div>
        <div class="form-group">
            <label for="courseID">Course Code:</label>
            <c:set value="${getCourse.getCourse(currentClass.courseID)}" var="course" />
            <input type="text" class="form-control" value="${course.courseCode}" readonly>
        </div>
        <div class="form-group">
            <label for="courseID">Course Name: </label>
            <input type="text" class="form-control" value="${course.courseName}" readonly>
        </div>
        <div class="form-group">
            <label for="courseID">Semester: </label>
            <input type="text" class="form-control" value="${currentClass.semesterName}" readonly>
        </div>
    </form>
</div>

<%@ include file="../../commonStudent/footer.jsp" %>