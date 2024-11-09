<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="getTeacher" class="DAO.TeacherDAO" />
<jsp:useBean id="getActiveAssignment" class="Utils.GetActiveAssignment" />
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Submission</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <style>
            .container {
                margin-top: 50px;
            }
        </style>
    </head>
    <body>
        <%@ include file="../../commonStudent/head.jsp" %>

        <div class="container">
            <h2 class="mb-4">Your Submission for: ${assignment.title}</h2>

            <!-- Hiển thị thông báo lỗi hoặc thành công nếu có -->
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

            <!-- Bảng hiển thị thông tin bài nộp -->
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>File</th>
                        <th>Submission Date</th>
                        <th>Grade</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:if test="${submit != null}">
                        <tr>
                            <td>1</td>
                            <td>
                                <a href="FileDownloadController?file=${submit.submissionContent}" class="badge badge-info">${submit.submissionContent}</a>
                            </td>
                            <td>
                                ${submit.submissionDate}
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${submit.grade == -1}">
                                        <span class="badge badge-secondary">Not graded</span>
                                    </c:when>
                                    <c:when test="${submit.grade < 5 && submit.grade != -1}">
                                        <span class="badge badge-danger">Graded: ${submit.grade}</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="badge badge-success">Graded: ${submit.grade}</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <a href="FileDownloadController?file=${submit.submissionContent}" class="btn btn-info btn-sm">Download</a>
                            </td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>

        <%@ include file="../../commonStudent/footer.jsp" %>
    </body>
</html>
