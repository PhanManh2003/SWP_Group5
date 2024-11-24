<%-- 
    Document   : add-student
    Created on : Jun 11, 2024, 2:39:25 AM
    Author     : HP
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../../commonTeacher/head.jsp"></jsp:include>
    <div class="container">
        <h2>Add student</h2>
    <c:if test="${errorMessage != null}">
        <div class="alert alert-danger" role="alert">
            ${errorMessage}
        </div>
    </c:if>
    <span class="badge badge-danger"></span>
    <form action="TeacherClassController?action=add-student" method="post" class="mb-4">
        <input type="hidden" class="form-control" id="classID" name="classID" required value="${classId}">
        <div class="form-group">
            <label for="email">Email</label>
            <input type="email" class="form-control" id="email" name="email" required placeholder="Email student">
        </div>
        <button type="submit" class="btn btn-primary">Add student</button>
    </form>
</div>
<%@include file="../../commonTeacher/footer.jsp" %>

