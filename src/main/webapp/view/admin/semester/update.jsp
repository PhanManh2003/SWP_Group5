


<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../commonAdmin/head.jsp"></jsp:include>
        <div class="container">
            <h2>Edit Semester</h2>
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
            <form action="EditSemesterController" method="post" class="mb-4">
                <input type="hidden" name="semesterID" value="${semester.semesterID}">
                <div class="form-group">
                    <label for="semesterName">Semester Name:</label>
                    <input type="text" class="form-control" id="semesterName" name="semesterName" value="${semester.semesterName}" required>
                </div>
                <div class="form-group">
                    <label for="year">Year:</label>
                    <input type="number" class="form-control" id="year" name="year" value="${semester.year}" required>
                </div>
                <div class="form-group">
                    <label for="status">Status:</label>
                    <select class="form-control" id="status" name="status">
                        <option value="1" ${semester.status == 1 ? "selected" : ""}>Active</option>
                        <option value="0" ${semester.status == 0 ? "selected" : ""}>Inactive</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Update Semester</button>
            </form>
            <a href="ListSemestersServlet" class="btn btn-secondary">Back to List</a>
        </div>
   <%@include file="../../commonAdmin/footer.jsp" %>