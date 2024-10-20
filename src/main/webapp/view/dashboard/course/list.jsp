<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html class="no-js" lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>SkillGro - Manage Accounts</title>
        <meta name="description" content="SkillGro - Manage Accounts">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/img/favicon.png">
        <!-- Place favicon.ico in the root directory -->

        <!-- CSS here -->
        <jsp:include page="../../common/css-file.jsp"></jsp:include>
        </head>

        <body>

            <!-- Scroll-top -->
            <button class="scroll__top scroll-to-target" data-target="html">
                <i class="tg-flaticon-arrowhead-up"></i>
            </button>
            <!-- Scroll-top-end-->

            <!-- header-area -->
        <jsp:include page="../../common/home/header-home.jsp"></jsp:include>
            <!-- header-area-end -->

            <!-- main-area -->
            <main class="main-area">
                <section class="dashboard__area section-pb-120">
                    <div class="container">
                    <jsp:include page="../../common/dashboard/avatar.jsp"></jsp:include>

                        <div class="dashboard__inner-wrap">
                            <div class="row">
                            <jsp:include page="../../common/dashboard/sideBar.jsp"></jsp:include>
                                <div class="col-lg-9">
                                    <div class="container">
                                        <h2>List Courses</h2>
                                        <a href="AddCourseController" class="btn btn-primary mb-4">Add Course</a>
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
                                    <table class="table table-striped" id="data-table">
                                        <thead class="thead-dark">
                                            <tr>
                                                <th>ID</th>
                                                <th>Course Name</th>
                                                <th>Course Code</th>
                                                <th>Status</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="course" items="${courses}">
                                                <tr>
                                                    <td>${course.courseID}</td>
                                                    <td>${course.courseName}</td>
                                                    <td>${course.courseCode}</td>
                                                    <td>${course.status == 1 ? 'Active' : 'Inactive'}</td>
                                                    <td>
                                                        <a href="EditCourseController?courseID=${course.courseID}" class="btn btn-primary">Edit</a>
                                                        <a onClick="return confirm('Are your sure to delete it?')" href="DeleteCourseController?courseID=${course.courseID}" class="btn btn-danger">Delete</a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                </div>
            </section>
        </main>
        <!-- main-area-end -->

        <!-- footer-area -->
        <jsp:include page="../../common/home/footer-home.jsp"></jsp:include>
            <!-- footer-area-end -->

            <!-- JS here -->
        <jsp:include page="../../common/js-file.jsp"></jsp:include>

            <script>
                function confirmDeactivate(accountId) {
                    if (confirm('Are you sure you want to deactivate this account?')) {
                        window.location.href = '${pageContext.request.contextPath}/manage-account?action=deactivate&id=' + accountId;
                    }
                }
        </script>

        <script>
            // Toast message display
            var toastMessage = "${sessionScope.toastMessage}";
            var toastType = "${sessionScope.toastType}";
            if (toastMessage) {
                iziToast.show({
                    title: toastType === 'success' ? 'Success' : 'Error',
                    message: toastMessage,
                    position: 'topRight',
                    color: toastType === 'success' ? 'green' : 'red',
                    timeout: 5000,
                    onClosing: function () {
                        // Remove toast attributes from the session after displaying
                        fetch('${pageContext.request.contextPath}/remove-toast', {
                            method: 'POST',
                            headers: {
                                'Content-Type': 'application/x-www-form-urlencoded',
                            },
                        }).then(response => {
                            if (!response.ok) {
                                console.error('Failed to remove toast attributes');
                            }
                        }).catch(error => {
                            console.error('Error:', error);
                        });
                    }
                });
            }
        </script>
    </body>

</html>