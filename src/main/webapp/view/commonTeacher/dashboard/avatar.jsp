<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="getTeacherHeader" class="DAO.TeacherDAO" />
<c:set value="${getTeacherHeader.getTeacher(sessionScope.teacherID)}" var="teacherLoginHeader"/>
<c:if test="${teacherLoginHeader == null || teacherLoginHeader.status == 0}">
    <c:redirect url="LoginController" />
</c:if>
<div class="dashboard__top-wrap">
    <div class="dashboard__top-bg" data-background="https://daihoc.fpt.edu.vn/wp-content/uploads/2024/07/186495053_4677120922317472_6915415257506550014_n-650x488.webp"></div>
    <div class="dashboard__instructor-info">
        <div class="dashboard__instructor-info-left">
            <div class="thumb" id="profileImageContainer">
                <c:choose>
                    <c:when test="${empty teacherLoginHeader.avatar}">
                        <img src="${pageContext.request.contextPath}/assets/img/courses/details_instructors02.jpg" alt="Profile Image" id="profileImage">
                    </c:when>
                    <c:otherwise>
                        <img src="${pageContext.request.contextPath}/${teacherLoginHeader.avatar}" alt="Profile Image" id="profileImage">
                    </c:otherwise>
                </c:choose>
                <input type="file" id="imageUpload" accept="image/*" style="display: none;">
                <div class="image-upload-overlay">
                    <span>Change<br>Image</span>
                </div>
            </div>
            <div class="content">
                <h4 class="title">${teacherLoginHeader.name}</h4>
            </div>
        </div>
    </div>
</div>
<style>
    #profileImageContainer {
        position: relative;
        cursor: pointer;
        width: 150px; /* Adjust this to match your avatar size */
        height: 150px; /* Adjust this to match your avatar size */
        border-radius: 50%;
        overflow: hidden;
    }
    #profileImage {
        width: 100%;
        height: 100%;
        object-fit: cover;
    }
    .image-upload-overlay {
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background: rgba(0, 0, 0, 0.5);
        display: flex;
        justify-content: center;
        align-items: center;
        opacity: 0;
        transition: opacity 0.3s;
        border-radius: 50%;
    }
    #profileImageContainer:hover .image-upload-overlay {
        opacity: 1;
    }
    .image-upload-overlay span {
        color: white;
        font-size: 14px;
        text-align: center;
        line-height: 1.2;
    }
</style>

<script>
    document.addEventListener('DOMContentLoaded', function () {
        const profileImageContainer = document.getElementById('profileImageContainer');
        const imageUpload = document.getElementById('imageUpload');
        const profileImage = document.getElementById('profileImage');

        profileImageContainer.addEventListener('click', function () {
            imageUpload.click();
        });

        imageUpload.addEventListener('change', function (event) {
            const file = event.target.files[0];
            if (file) {
                const reader = new FileReader();
                reader.onload = function (e) {
                    profileImage.src = e.target.result;
                }
                reader.readAsDataURL(file);

                const formData = new FormData();
                formData.append('profileImage', file);
                formData.append('action', 'uploadProfileImage');
                fetch('${pageContext.request.contextPath}/dashboard-profile-teacher', {
                    method: 'POST',
                    body: formData
                })
                        .then(response => {
                            if (!response.ok) {
                                throw new Error('Network response was not ok');
                            }
                            return response.text();
                        })
                        .then(data => {
                            window.location.href = '${pageContext.request.contextPath}/TeacherClassController';
                        })
                        .catch(error => {
                            console.error('Error:', error);
                            alert('Failed to upload image. Please try again.');
                        });
            }
        });
    });
</script>