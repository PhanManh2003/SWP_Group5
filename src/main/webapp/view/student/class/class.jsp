<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="getTeacher" class="DAO.TeacherDAO" />
<jsp:useBean id="getCourse" class="DAO.CourseDAO" />
<jsp:useBean id="getStudentClasses" class="DAO.StudentClassDAO" />
<jsp:include page="../../commonStudent/head.jsp"/>
<div class="container">
    <h2 class="mb-4">List of Classes</h2>
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
    <form action="StudentClassController" method="get" class="mb-4">
        <div class="input-group">
            <input type="text" class="form-control" name="searchQuery" value="${searchQuery}" placeholder="Search for classes..." />
            <div class="input-group-append">
                <button class="btn btn-primary" type="submit">Search</button>
            </div>
        </div>
    </form>
    <div class="row">
        <c:forEach var="cla" items="${classInfors}">
            <c:set value="${getCourse.getCourse(cla.courseID)}" var="course" />
            <c:set value="${getTeacher.getTeacher(cla.teacherID)}" var="teacher" />
            <c:set value="${getStudentClasses.getStudentsByClassId(sessionScope.studentID, cla.classID)}" var="classesJoined" />
            <div class="col-lg-4 col-md-6 mb-4">
                <div class="courses__item shine__animate-item">
                    <div class="courses__item-thumb">
                        <a href="StudentClassController?action=view&classID=${cla.classID}" class="shine__animate-link">
                            <img src="https://via.placeholder.com/200x200.png?text=${cla.className}" alt="Class Image">
                        </a>
                    </div>
                    <div class="courses__item-content">
                        <ul class="courses__item-meta list-wrap">
                            <li class="courses__item-tag">
                                <a href="StudentClassController?action=view&classID=${cla.classID}">Class: ${cla.className}</a>
                            </li>
                            <li class="avg-rating"><i class="fas fa-user"></i> Teacher: ${teacher.name}</li>
                        </ul>

                        <h5 class="title">
                            <a href="StudentClassController?action=view&classID=${cla.classID}">Course: ${course.courseCode}</a>
                        </h5>

                        <p class="author">
                            <strong>Semester:</strong> ${cla.semesterName} <br>
                            <strong>Status:</strong> ${cla.status == 1 ? 'Active' : 'Inactive'}
                        </p>

                        <div class="courses__item-bottom">
                            <div class="button">
                                <a href="StudentClassController?action=view&classID=${cla.classID}">
                                    <span class="text">View Class</span>
                                    <i class="flaticon-arrow-right"></i>
                                </a>
                            </div>
                            <c:if test="${classesJoined == null}">
                                <div class="button">
                                    <a onclick="return confirm('Are you sure to join this class?')" href="StudentClassController?action=ask-join&classID=${cla.classID}" class="btn btn-warning">Ask to join</a>
                                </div>
                            </c:if>

                            <c:if test="${classesJoined != null && classesJoined.status == 0}">
                                <div class="button">
                                    <a class="btn btn-primary">Waiting</a>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<%@include file="../../commonStudent/footer.jsp" %>
