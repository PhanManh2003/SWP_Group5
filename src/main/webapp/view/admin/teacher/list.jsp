

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../commonAdmin/head.jsp"></jsp:include>
    <div class="container">
        <h2>List Teachers</h2>
        <a href="AddTeacherController" class="btn btn-primary mb-4">Add new</a>
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
    <table class="table" id="lessionTable">
        <thead class="thead-dark">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Status</th>
                <th>Can delete?</th>
                <th>Created by</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="teacher" items="${teachers}">
                <tr>
                    <td>${teacher.teacherID}</td>
                    <td>${teacher.name}</td>
                    <td>${teacher.email}</td>
                    <td>${teacher.phone}</td>
                    <td>${teacher.status == 1 ? 'Active' : 'Inactive'}</td>
                    <td>
                        <span class="${teacher.canDelete ? 'badge badge-success' : 'badge badge-danger'}">
                            ${teacher.canDelete ? "Yes" : "No"}
                        </span>
                    </td>
                    <td>${teacher.admin != null ? teacher.admin.name : "N/A"}</td>
                    <td>
                        <a href="EditTeacherController?teacherID=${teacher.teacherID}" class="btn btn-info">Edit</a>
                        <a onClick="return confirm('Are your sure to delete it?')" href="DeleteTeacherController?teacherID=${teacher.teacherID}" class="btn btn-danger">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<%@include file="../../commonAdmin/footer.jsp" %>