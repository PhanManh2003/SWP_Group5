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
            // Hàm kiểm tra tính hợp lệ của tệp được chọn
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

            <div class="alert alert-warning mt-3">
                <strong>Important:</strong> Please ensure to keep a copy of your submission on your device. ZIP files submitted will be stored and cannot be accessed or deleted until the end of the course.
            </div>

            <form action="StudentAssignmentController?action=submit" method="post" onsubmit="return validateForm()" enctype="multipart/form-data">
                <input type="hidden" name="classID" value="${currentClass.classID}">
                <input type="hidden" name="studentID" value="${studentLogin.studentID}">
                <input type="hidden" name="assignmentID" value="${assignment.assignmentID}">
                <!-- Lưu thông tin cùng tệp tải lên -->

                <div class="form-group">
                    <label for="file">Choose a file to submit:</label>
                    <input class="form-control" type="file" id="file" name="file" accept=".zip" required>
                    <!-- required đảm bảo phải chọn một tệp -->
                </div>

                <button class="btn btn-primary" type="submit">Submit</button>
                <a class="btn btn-secondary" href="StudentClassController?action=assignment&classID=${currentClass.classID}">Cancel</a>
            </form>
        </div>

        <%@ include file="../../commonStudent/footer.jsp" %>
    </body>
</html>