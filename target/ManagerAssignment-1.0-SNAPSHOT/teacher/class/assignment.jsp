

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="getTeacher" class="DAO.TeacherDAO" />
<jsp:useBean id="getCourse" class="DAO.CourseDAO" />
<jsp:useBean id="getActiveAssignment" class="Utils.GetActiveAssignment" />
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>List of assignment</title>
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
            <h2>List of assignments for class ${currentClass.className} </h2>
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
                        <th>Title</th>
                        <th>Due Date</th>
                        <th>Type </th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="assign" items="${assignments}" varStatus="status">
                        <tr>
                            <td>${status.index + 1}</td>
                            <td>${assign.title}</td>
                            <td>
                                 ${getActiveAssignment.getAssignmentBadge(assign.dueDate)}
                            </td>
                            <td>
                                <span class="badge badge-success">
                                    <c:choose>
                                        <c:when test="${assign.type == 3}">
                                            Caption
                                        </c:when>
                                        <c:when test="${assign.type == 3}">
                                            Assignment
                                        </c:when>
                                        <c:otherwise>
                                            Lab
                                        </c:otherwise>
                                    </c:choose>
                                </span>
                            </td>
                            <td>${assign.status == 1 ? 'Active' : 'Hidden'}</td>
                            <td>
                                <a href="TeacherAssignmentController?action=submission&classID=${assign.classID}&assignmentID=${assign.assignmentID}" class="btn btn-info">Submition</a>
                                <a href="TeacherAssignmentController?action=edit&assignmentID=${assign.assignmentID}" class="btn btn-warning">Edit</a>
                                <a onclick="deleteAssignment(${assign.assignmentID}, ${assign.classID})" class="btn btn-danger">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <script>
            const deleteAssignment = (assignmentId, classID) => {
                if (confirm('Are you sure to remove this assignment, if you delete all submit will be deleted?') == false) {
                    return false;
                }
                var xhr = new XMLHttpRequest();
                var url = "http://localhost:8080/ManagerAssignment/TeacherClassController?action=delete-assignment";
                xhr.open("POST", url, true);
                xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === 4) {
                        if (xhr.status == 200) {
                            var response = xhr.responseText;
                            if (response == "success") {
                                alert("Delete successfully.");
                                setTimeout(function () {
                                    location.reload();
                                }, 200);
                            } else {
                                showError("Delete fail. Try again.");
                            }
                        } else {
                            showError("Delete fail. Try again.");
                        }
                    }
                };
                var params = "assignmentId=" + assignmentId + "&classId=" + classID;
                xhr.send(params);
            };
        </script>
    </body>
</html>

