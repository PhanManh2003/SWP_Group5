<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html class="no-js" lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>SkillGro - Online Courses & Education Template</title>
        <meta name="description" content="SkillGro - Online Courses & Education Template">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/img/favicon.png">
        <!-- Place favicon.ico in the root directory -->

        <!-- CSS here -->
        <jsp:include page="../common/css-file.jsp"></jsp:include>
        </head>

        <body>

            <!-- Scroll-top -->
            <button class="scroll__top scroll-to-target" data-target="html">
                <i class="tg-flaticon-arrowhead-up"></i>
            </button>
            <!-- Scroll-top-end-->

            <!-- header-area -->
        <jsp:include page="../common/home/header-home.jsp"></jsp:include>
            <!-- header-area-end -->



            <!-- main-area -->
            <main class="main-area">

                <!-- dashboard-area -->
                <section class="dashboard__area section-pb-120">
                    <div class="container">
                    <jsp:include page="../common/dashboard/avatar.jsp"></jsp:include>
                        <div class="dashboard__inner-wrap">
                            <div class="row">
                                <!--Side bar-->
                            <jsp:include page="../common/dashboard/sideBar.jsp"></jsp:include>

                                <!--Main Content-->
                                <div class="col-lg-9">
                                    <div class="dashboard__content-wrap">
                                        <div class="dashboard__content-title">
                                            <h4 class="title">My Profile</h4>
                                        </div>
                                        <div class="row">
                                            <div class="col-lg-12">
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
                                            <div class="profile__content-wrap">
                                                <form action="AdminController?action=edit" method="post">
                                                    <input type="hidden" name="type" value="profile" required>
                                                    <div class="form-group">
                                                        <label for="name">Name:</label>
                                                        <input type="text" class="form-control" id="name" name="name" value="${currentAdmin.name}" required>
                                                        <input type="hidden" name="idAdmin" value="${currentAdmin.id}" required>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="email">Email:</label>
                                                        <input type="email" class="form-control" id="email" name="email" value="${currentAdmin.email}" required>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="phone">Phone:</label>
                                                        <input type="text" class="form-control" id="phone" name="phone" value="${currentAdmin.phone}" required>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="password">Password:</label>
                                                        <input type="password" class="form-control" id="password" name="password">
                                                        <input type="hidden" value="${currentAdmin.password}" name="oldPassword"/>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="status">Status:</label>
                                                        <select class="form-control" id="status" name="status">
                                                            <option value="1" ${currentAdmin.status == 1 ? "selected" : ""}>Active</option>
                                                            <option value="0" ${currentAdmin.status == 0 ? "selected" : ""}>Inactive</option>
                                                        </select>
                                                    </div>
                                                    <button type="submit" class="btn btn-primary">Update Admin</button>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <!-- dashboard-area-end -->

        </main>
        <!-- main-area-end -->



        <!-- footer-area -->
        <jsp:include page="../common/home/footer-home.jsp"></jsp:include>
            <!-- footer-area-end -->



            <!-- JS here -->
        <jsp:include page="../common/js-file.jsp"></jsp:include>
            <!--        <script>
                        SVGInject(document.querySelectorAll("img.injectable"));
                    </script>-->
            <script>
                document.addEventListener('DOMContentLoaded', function () {
                    var toastMessage = "${toastMessage}";
                    var toastType = "${toastType}";

                    if (toastMessage) {
                        iziToast.show({
                            title: toastType === 'success' ? 'Success' : 'Error',
                            message: toastMessage,
                            position: 'topRight',
                            color: toastType === 'success' ? 'green' : 'red',
                            timeout: 5000
                        });
                    }
                });
        </script>
    </body>

</html>