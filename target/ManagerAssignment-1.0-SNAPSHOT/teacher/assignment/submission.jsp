

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="getTeacher" class="DAO.TeacherDAO" />
<jsp:useBean id="getActiveAssignment" class="Utils.GetActiveAssignment" />
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Submition</title>
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
            <h2>Your submission for ${assignment.title}</h2>
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
                            <td colspan="10">
                                <span class="badge badge-default">No have submission for this assignment</span>
                            </td>
                        </tr>
                    </c:if>
                    <c:forEach items="${submissions}" var="submit" varStatus="status">
                        <tr>
                            <td>
                                ${status.index + 1}
                            </td>
                            <td>
                                <span class="badge badge-default">
                                    <a href="FileDownloadController?file=${submit.submissionContent}">${submit.submissionContent}</a>
                                </span>
                            </td>
                            <td >
                                ${submit.submissionDate}
                            </td>
                            <td >
                                <c:if test="${submit.grade == -1}">
                                    <span class="badge badge-default">
                                        Not grade
                                    </span>
                                </c:if>
                                <c:if test="${submit.grade < 5 && submit.grade != -1}">
                                    <span class="badge badge-danger">
                                        Graded: ${submit.grade}
                                    </span>
                                </c:if>
                                <c:if test="${submit.grade >= 5}">
                                    <span class="badge badge-success">
                                        Graded: ${submit.grade}
                                    </span>
                                </c:if>
                            </td>
                            <td >
                                <span class="badge badge-default">
                                    <a href="FileDownloadController?file=${submit.submissionContent}">Download</a>
                                </span>
                                <a href="TeacherAssignmentController?action=grade&submitID=${submit.submissionID}" class="btn btn-primary">Grade</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>

