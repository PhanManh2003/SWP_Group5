<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<jsp:useBean id="getTeacher" class="DAO.TeacherDAO" />
<jsp:useBean id="getActiveAssignment" class="Utils.GetActiveAssignment" />
<jsp:include page="../../commonTeacher/head.jsp"></jsp:include>

<div class="container mt-4">
    <!-- Card hiển thị thông tin Assignment -->
    <div class="card">
        <div class="card-header bg-primary text-white">
            <h3>Assignment: ${assignment.title}</h3>
        </div>
        <div class="card-body">
            <!-- Title -->
            <h5 class="card-title">${assignment.title}</h5>

            <!-- Description -->
            <p class="card-text">
                <strong>Description:</strong> ${assignment.description}
            </p>

            <!-- Due Date -->
            <p class="card-text">
                <strong>Due Date:</strong>
                <span class="badge badge-warning">${assignment.dueDate}</span>
            </p>

            <!-- Type -->
            <p class="card-text">
                <strong>Type:</strong>
                <span class="badge badge-success">
                    <c:choose>
                        <c:when test="${assignment.type == 3}">Caption</c:when>
                        <c:when test="${assignment.type == 2}">Assignment</c:when>
                        <c:otherwise>Lab</c:otherwise>
                    </c:choose>
                </span>
            </p>

            <!-- Status -->
            <p class="card-text">
                <strong>Status:</strong>
                <span class="badge ${assignment.status == 1 ? 'badge-success' : 'badge-secondary'}">
                    ${assignment.status == 1 ? 'Active' : 'Hidden'}
                </span>
            </p>
        </div>
    </div>

    <!-- Hiển thị danh sách submission -->
    <h2 class="mt-4">Your submission for ${assignment.title}</h2>
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
        <thead>
            <tr>
                <th>No.</th>
                <th>File</th>
                <th>Submission date</th>
                <th>Grade</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:if test="${submissions.size() == 0}">
                <tr>
                    <td colspan="5" class="text-center">
                        <span class="badge badge-secondary">No submissions for this assignment</span>
                    </td>
                </tr>
            </c:if>
            <c:forEach items="${submissions}" var="submit" varStatus="status">
                <tr>
                    <td>${status.index + 1}</td>
                    <td>
                        <span class="badge badge-info">
                            <a href="FileDownloadController?file=${submit.submissionContent}" class="text-white">${submit.submissionContent}</a>
                        </span>
                    </td>
                    <td>
                        ${submit.submissionDate}
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${submit.grade == -1}">
                                <span class="badge badge-secondary">Not graded</span>
                            </c:when>
                            <c:when test="${submit.grade < 5}">
                                <span class="badge badge-danger">Graded: ${submit.grade}</span>
                            </c:when>
                            <c:otherwise>
                                <span class="badge badge-success">Graded: ${submit.grade}</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <div class="btn-group">
                            <a href="FileDownloadController?file=${submit.submissionContent}" class="btn btn-info btn-sm">
                                <i class="fas fa-download"></i> Download
                            </a>
                            <a href="TeacherAssignmentController?action=grade&submitID=${submit.submissionID}" class="btn btn-primary btn-sm">
                                <i class="fas fa-pen"></i> Grade
                            </a>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<%@include file="../../commonTeacher/footer.jsp" %>
