
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../commonAdmin/head.jsp"></jsp:include>
        <div class="container">
            <h2>Add Course</h2>
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
            <form action="AddCourseController" method="post">
                <div class="form-group">
                    <label for="courseName">Course Name:</label>
                    <input type="text" class="form-control" id="courseName" name="courseName" required>
                </div>
                <div class="form-group">
                    <label for="courseCode">Course Code:</label>
                    <input type="text" class="form-control" id="courseCode" name="courseCode" required>
                </div>
                <div class="form-group">
                    <label for="status">Status:</label>
                    <select class="form-control" id="status" name="status" required>
                        <option value="1">Active</option>
                        <option value="0">Inactive</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Add Course</button>
            </form>
        </div>

 <%@include file="../../commonAdmin/footer.jsp" %>