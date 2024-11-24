<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../../commonTeacher/head.jsp"></jsp:include>

<div class="container mt-4">
    <h1 class="mb-4">Edit Assignment</h1>

    <!-- Hiển thị thông báo lỗi nếu có -->
    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger" role="alert">
            <strong>Error:</strong> ${errorMessage}
        </div>
    </c:if>

    <!-- Form để chỉnh sửa bài tập -->
    <form action="TeacherAssignmentController?action=edit" method="post" class="mb-4">
        <input type="hidden" name="assignmentID" value="${assignment.assignmentID}">
        
        <!-- Class ID -->
        <div class="form-group">
            <label for="classID">Class ID:</label>
            <input type="text" class="form-control" id="classID" name="classID" value="${assignment.classID}" readonly>
        </div>

        <!-- Title -->
        <div class="form-group">
            <label for="title">Title:</label>
            <input type="text" class="form-control" id="title" name="title" value="${assignment.title}" required>
        </div>

        <!-- Description -->
        <div class="form-group">
            <label for="desc">Description:</label>
            <textarea class="form-control" id="desc" name="desc" rows="5" required>${assignment.description}</textarea>
        </div>

        <!-- Due Date -->
        <div class="form-group">
            <label for="duedate">Due Date:</label>
            <input type="datetime-local" class="form-control" id="duedate" name="duedate" value="${assignment.dueDate}" required>
        </div>

        <!-- Type -->
        <div class="form-group">
            <label for="type">Type:</label>
            <select class="form-control" id="type" name="type" required>
                <option value="3" ${3 == assignment.type ? 'selected' : ''}>Caption</option>
                <option value="2" ${2 == assignment.type ? 'selected' : ''}>Assignment</option>
                <option value="1" ${1 == assignment.type ? 'selected' : ''}>Lab</option>
            </select>
        </div>

        <!-- Status -->
        <div class="form-group">
            <label for="status">Status:</label>
            <select class="form-control" id="status" name="status" required>
                <option value="1" ${1 == assignment.status ? 'selected' : ''}>Active</option>
                <option value="0" ${0 == assignment.status ? 'selected' : ''}>Inactive</option>
            </select>
        </div>

        <!-- Nút Update -->
        <div class="text-right">
            <button type="submit" class="btn btn-primary">
                <i class="fas fa-save"></i> Update Assignment
            </button>
        </div>
    </form>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
<script>
    var now = moment().utcOffset(7);
    var nowString = now.format('YYYY-MM-DDTHH:mm');
    document.getElementById("duedate").setAttribute("min", nowString);
</script>

<%@include file="../../commonTeacher/footer.jsp" %>
