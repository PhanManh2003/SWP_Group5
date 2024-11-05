

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="getTeacher" class="DAO.TeacherDAO" />
<jsp:useBean id="getCourse" class="DAO.CourseDAO" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../commonAdmin/head.jsp"></jsp:include>
        <div class="container">
            <h2>List of Classes</h2>
            <a href="AddClassController" class="btn btn-primary mb-4">Add Class</a>
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
            <table class="table table-bordered" id="data-table">
                <thead class="thead-dark">
                    <tr>
                        <th>Class ID</th>
                        <th>Class Name</th>
                        <th>Status</th>
                        <th>Teacher</th>
                        <th>Course </th>
                        <th>Semester Name</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="cla" items="${classes}">
                        <c:set value="${getTeacher.getTeacher(cla.teacherID)}" var="teacher" />
                        <c:set value="${getCourse.getCourse(cla.courseID)}" var="course" />
                        <tr>
                            <td>${cla.classID}</td>
                            <td>${cla.className}</td>
                            <td>${cla.status == 1 ? 'Active' : 'Inactive'}</td>
                            <td>${teacher.name}</td>
                            <td>${course.courseCode}</td>
                            <td>${cla.semesterName}</td>
                            <td>
                                <a href="EditClassController?classID=${cla.classID}" class="btn btn-warning">Edit</a>
                                <a onClick="return confirm('Are your sure to delete it?')" href="DeleteClassController?classID=${cla.classID}" class="btn btn-danger">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
<%@include file="../../commonAdmin/footer.jsp" %>
