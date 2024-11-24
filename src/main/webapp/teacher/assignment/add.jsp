<%-- 
    Document   : add
    Created on : Jun 11, 2024, 1:38:31 AM
    Author     : HP
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../../commonTeacher/head.jsp"></jsp:include>
    <div class="container">
        <h2>Add assignment</h2>
        <c:if test="${errorMessage != null}">
        <div class="alert alert-danger" role="alert">
            ${errorMessage}
        </div>
    </c:if>
    <span class="badge badge-danger"></span>
    <form action="TeacherAssignmentController?action=add" method="post" class="mb-4">
        <input type="hidden" class="form-control" id="classID" name="classID" required value="${classInfo.classID}">
        <div class="form-group">
            <label for="title">Title:</label>
            <input type="text" class="form-control" id="title" name="title" required>
        </div>
        <div class="form-group">
            <label for="desc">Description</label>
            <textarea id="desc" class="form-control" name="desc" rows="5" cols="10"></textarea>
        </div>
        <div class="form-group">
            <label for="duedate">Due date</label>
            <input type="datetime-local" class="form-control" id="duedate" name="duedate" required>
        </div>
        <div class="form-group">
            <label for="type">Type:</label>
            <select class="form-control" id="type" name="type" required>
                <option value="3">Caption</option>
                <option value="2">Assignment</option>
                <option value="1">Lab</option>
            </select>
        </div>
        <div class="form-group">
            <label for="status">Status:</label>
            <select class="form-control" id="status" name="status" required>
                <option value="1">Active</option>
                <option value="0">Inactive</option>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Add assignment</button>
    </form>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
<script>
    var now = moment().utcOffset(7);
    var nowString = now.format('YYYY-MM-DDTHH:mm');
    document.getElementById("duedate").value = nowString;
    document.getElementById("duedate").setAttribute("min", nowString);
</script>
 <%@include file="../../commonTeacher/footer.jsp" %>

