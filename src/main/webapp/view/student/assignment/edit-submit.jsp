<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Submit Assignment</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <script>
            // Hàm kiểm tra tính hợp lệ của tệp tin được chọn
            function validateForm() {
                var fileInput = document.getElementById("file");
                var file = fileInput.files[0];
                if (!file) {
                    alert("Please select a file to upload.");
                    return false;
                }
                var maxSize = 10 * 1024 * 1024; // 10MB
                if (file.size > maxSize) {
                    alert("File size exceeds the maximum limit of 10MB.");
                    return false;
                }
                var allowedExtension = ".zip";
                if (!file.name.endsWith(allowedExtension)) {
                    alert("Only ZIP files are allowed.");
                    return false;
                }
                return true;
            }
        </script>
    </head>
    <body>
        <%@ include file="../../commonStudent/head.jsp" %>
        
        <div class="container mt-4">
            <h2 class="mb-4">Submit Assignment</h2>
            
            <!-- Hiển thị thông báo lỗi nếu có -->
            <c:if test="${errorMessage != null}">
                <div class="alert alert-danger" role="alert">
                    ${errorMessage}
                </div>
            </c:if>
            
            <!-- Form để sinh viên nộp bài -->
            <form action="StudentAssignmentController?action=edit-submit" method="post" class="mb-4" onsubmit="return validateForm()" enctype="multipart/form-data">
                <input type="hidden" class="form-control" id="classID" name="classID" required value="${currentClass.classID}">
                <input type="hidden" class="form-control" id="studentId" name="studentID" required value="${currentSubmit.studentID}">
                <input type="hidden" class="form-control" id="submitID" name="submitID" required value="${currentSubmit.submissionID}">
                <input type="hidden" class="form-control" id="assignmentId" name="assignmentID" required value="${currentSubmit.assignmentID}">
                
                <div class="form-group">
                    <label for="file">Choose a file to submit: </label>
                    <input type="file" class="form-control" id="file" name="file" accept=".zip" required>
                    <input type="hidden" class="form-control" name="oldSubmission" required value="${currentSubmit.submissionContent}">
                </div>
                
                <!-- Hiển thị thông tin bài đã nộp trước đó nếu có -->
                <div class="form-group">
                    <label for="file">Submitted File: </label>
                    <a class="btn btn-info form-control" href="FileDownloadController?file=${currentSubmit.submissionContent}">
                        ${currentSubmit.submissionContent}
                    </a>
                </div>
                
                <button type="submit" class="btn btn-primary">Submit</button>
                <a href="StudentClassController?action=assignment&classID=${currentClass.classID}" class="btn btn-secondary">Back to Assignment List</a>
            </form>
        </div>

        <%@ include file="../../commonStudent/footer.jsp" %>
    </body>
</html>
