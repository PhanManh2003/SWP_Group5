<%-- 
    Document   : add
    Created on : Jun 11, 2024, 1:45:51 PM
    Author     : HP
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Submit assignment</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <script>
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
        <%@ include file="../component/header.jsp" %>
        <div class="container">
            <h2>Submit assignment</h2>
            <c:if test="${errorMessage != null}">
                <div class="alert alert-danger" role="alert">
                    ${errorMessage}
                </div>
            </c:if>
            <span class="badge badge-danger"></span>
            <form action="StudentAssignmentController?action=submit" method="post" onsubmit="return validateForm()" enctype="multipart/form-data">
                <input type="hidden" name="classID" value="${currentClass.classID}">
                <input type="hidden" name="studentID" value="${studentLogin.studentID}">
                <input type="hidden" name="assignmentID" value="${assignment.assignmentID}">
                <div class="form-group">
                    <label for="file">Choose a file to submit:</label>
                    <input class="form-control" onChange="validateForm()" type="file" id="file" name="file" accept=".zip" required>
                </div>
                <button class="btn btn-primary" type="submit">Submit</button>
                <a class="btn btn-default" href="StudentAssignmentController?action=assignment&classID=${currentClass.classID}">Cancel</a>
            </form>
        </div>
    </body>
</html>