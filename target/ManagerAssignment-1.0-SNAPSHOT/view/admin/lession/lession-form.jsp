<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../commonAdmin/head.jsp"></jsp:include>
        <div class="container">
            <h1 class="mt-5">${lession != null ? 'Edit' : 'New'} Lession</h1>
            <c:if test="${not empty error}">
                <div class="alert alert-danger">${error}</div>
            </c:if>
            <form action="lessions" method="post" enctype="multipart/form-data" onsubmit="return validateForm()">
                <input type="hidden" name="action" value="${lession != null ? 'update' : 'insert'}">
                <input type="hidden" name="id" value="${lession.lessionId}">

                <div class="form-group">
                    <label for="title">Title</label>
                    <input type="text" class="form-control" id="title" name="title" value="${lession.title}">
                </div>

                <div class="form-group">
                    <label for="description">Description</label>
                    <textarea class="form-control" id="description" name="description">${lession.description}</textarea>
                </div>

                <!-- Chọn Video URL hoặc tải lên file video -->
                <div class="form-group">
                    <label for="videoUrl">Video URL</label>
                    <input type="text" class="form-control" id="videoUrl" name="videoUrl" value="${lession.videoUrl}">
                </div>
                <div class="form-group">
                    <label for="videoFile">Upload Video</label>
                    <input type="file" class="form-control-file" id="videoFile" name="videoFile" accept="video/*">
                </div>

                <!-- Chọn Docs Link hoặc tải lên file docs -->
                <div class="form-group">
                    <label for="docsLink">Docs Link</label>
                    <input type="text" class="form-control" id="docsLink" name="docsLink" value="${lession.docsLink}">
                </div>
                <div class="form-group">
                    <label for="docsFile">Upload Document</label>
                    <input type="file" class="form-control-file" id="docsFile" name="docsFile" accept=".pdf,.doc,.docx">
                </div>

                <div class="form-group">
                    <label for="courseId">Course</label>
                    <select class="form-control" id="courseId" name="courseId">
                        <c:forEach var="course" items="${courses}">
                            <option value="${course.courseID}" ${lession != null && course.courseID == lession.courseId ? 'selected' : ''}>
                                ${course.courseName}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label for="status">Status</label>
                    <select class="form-control" id="status" name="status">
                        <option value="active" ${lession.status == 'active' ? 'selected' : ''}>Active</option>
                        <option value="inactive" ${lession.status == 'inactive' ? 'selected' : ''}>Inactive</option>
                    </select>
                </div>

                <button type="submit" class="btn btn-primary">${lession != null ? 'Update' : 'Save'}</button>
            </form>
        </div>
<%@include file="../../commonAdmin/footer.jsp" %>