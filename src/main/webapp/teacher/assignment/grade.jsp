<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="getStudent" class="DAO.StudentDAO" />
<jsp:useBean id="getClass" class="DAO.ClassDAO" />
<jsp:include page="../../commonTeacher/head.jsp"></jsp:include>

<div class="container mt-4">
    <h2>Grade Submission</h2>
    <c:if test="${errorMessage != null}">
        <div class="alert alert-danger" role="alert">
            ${errorMessage}
        </div>
    </c:if>

    <c:set var="studentSubmit" value="${getStudent.getStudent(submission.studentID)}" />
    <c:set var="classSubmit" value="${getClass.getClass(assignment.classID)}" />

    <form action="TeacherAssignmentController?action=grade" method="post">
        <!-- Hidden inputs for necessary IDs -->
        <input type="hidden" name="submissionID" value="${submission.submissionID}">
        <input type="hidden" name="classID" value="${assignment.classID}">
        <input type="hidden" name="assignmentID" value="${submission.assignmentID}">

        <!-- Thông tin file submission -->
        <div class="form-group">
            <label for="submissionFile">Submission File:</label>
            <a href="FileDownloadController?file=${submission.submissionContent}" class="btn btn-info form-control">
                <i class="fas fa-download"></i> ${submission.submissionContent}
            </a>
        </div>

        <!-- Thông tin lớp học -->
        <div class="form-group">
            <label for="className">Class Name:</label>
            <input class="form-control" type="text" value="${classSubmit.className}" readonly/>
        </div>

        <!-- Thông tin sinh viên -->
        <div class="row">
            <div class="col-md-6">
                <div class="form-group">
                    <label for="studentName">Student Name:</label>
                    <input class="form-control" type="text" value="${studentSubmit.name}" readonly/>
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-group">
                    <label for="studentEmail">Student Email:</label>
                    <input class="form-control" type="text" value="${studentSubmit.email}" readonly/>
                </div>
            </div>
        </div>

        <!-- Đánh giá điểm -->
        <div class="form-group">
            <label for="grade">Grade for Submission:</label>
            <input class="form-control" id="grade" min="0" max="10" type="number" value="${submission.grade == -1 ? 0 : submission.grade}" required name="grade"/>
        </div>

        <!-- Nút xác nhận -->
        <div class="text-right">
            <button type="submit" class="btn btn-primary">
                <i class="fas fa-check"></i> Grade Submission
            </button>
        </div>
    </form>
</div>

<%@include file="../../commonTeacher/footer.jsp" %>
