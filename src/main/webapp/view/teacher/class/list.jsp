<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="getTeacher" class="DAO.TeacherDAO" />
<jsp:useBean id="getCourse" class="DAO.CourseDAO" />
<jsp:include page="../../commonTeacher/head.jsp"></jsp:include>

<div class="container">
    <h2>List of Classes</h2>

    <!-- Display error or success message if available -->
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

    <!-- Class cards layout -->
    <div class="row">
        <c:forEach var="cla" items="${classes}">
            <c:set value="${getCourse.getCourse(cla.courseID)}" var="course" />
            <c:set value="${getTeacher.getTeacher(cla.teacherID)}" var="teacher" />

            <!-- Card for each class -->
            <div class="col-md-4 mb-4">
                <div class="card h-100">
                    <div class="card-header bg-primary text-white">
                        <h5 class="card-title">${cla.className}</h5>
                    </div>
                    <div class="card-body">
                        <p><strong>Class ID:</strong> ${cla.classID}</p>
                        <p><strong>Course:</strong> ${course.courseCode}</p>
                        <p><strong>Status:</strong> ${cla.status == 1 ? 'Active' : 'Inactive'}</p>
                        <p><strong>Semester:</strong> ${cla.semesterName}</p>
                    </div>
                    <div class="card-footer text-center">
                        <!-- Action buttons -->
                        <a href="TeacherClassController?action=view&classID=${cla.classID}" class="btn btn-info mr-2">View</a>
                        <a href="TeacherClassController?action=add-student&classID=${cla.classID}" class="btn btn-danger">Add Student</a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<%@include file="../../commonTeacher/footer.jsp" %>
