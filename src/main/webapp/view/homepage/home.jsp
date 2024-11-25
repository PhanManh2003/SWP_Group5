<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="getTeacher" class="DAO.TeacherDAO" />
<jsp:useBean id="getCourse" class="DAO.CourseDAO" />


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html class="no-js" lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>FPT - Courses</title>
        <meta name="description" content="SkillGro - Manage Accounts">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="shortcut icon" type="image/x-icon" href="https://cdn.haitrieu.com/wp-content/uploads/2021/10/Logo-Dai-hoc-FPT.png">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/animate.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/magnific-popup.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/fontawesome-all.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/flaticon-skillgro.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/flaticon-skillgro-new.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/swiper-bundle.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/default-icons.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/select2.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/odometer.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/aos.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/plyr.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/spacing.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/tg-cursor.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/izitoast/1.4.0/css/iziToast.min.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.datatables.net/1.11.5/css/dataTables.bootstrap4.min.css">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.11.5/js/dataTables.bootstrap4.min.js"></script>
        <style>
            .iziToast-wrapper {
                z-index: 99999 !important;
            }
            .iziToast {
                min-width: 300px;
            }
        </style>

    </head>

    <body>

        <button class="scroll__top scroll-to-target" data-target="html">
            <i class="tg-flaticon-arrowhead-up"></i>
        </button>
        <link rel="stylesheet" href="https://cdn.datatables.net/2.0.8/css/dataTables.bootstrap4.css"/>
        <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.datatables.net/2.0.8/js/dataTables.js"></script>
        <script src="https://cdn.datatables.net/2.0.8/js/dataTables.bootstrap5.js"></script>
        <script>
            $(document).ready(function () {
                $("#data-table").DataTable();
            });
        </script>
        <header>
            <div class="tg-header__top">
                <div class="container custom-container">
                    <div class="row">
                        <div class="col-lg-6">
                            <ul class="tg-header__top-info list-wrap">
                                <li><img src="${pageContext.request.contextPath}/assets/img/icons/map_marker.svg" alt="Icon"> <span>FPT University</span></li>
                                <li><img src="${pageContext.request.contextPath}/assets/img/icons/envelope.svg" alt="Icon"> <a>www.fpt.course@gmail.com</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div id="header-fixed-height"></div>
            <div id="sticky-header" class="tg-header__area">
                <div class="container custom-container">
                    <div class="row">
                        <div class="col-12">
                            <div class="tgmenu__wrap">
                                <nav class="tgmenu__nav">
                                    <div class="logo">
                                        <a href="home"><img style="width: 150px" src="https://cdn.haitrieu.com/wp-content/uploads/2021/10/Logo-Dai-hoc-FPT.png" alt="Logo"></a>
                                    </div>
                                    <div class="tgmenu__navbar-wrap tgmenu__main-menu d-none d-xl-flex">
                                        <ul class="navigation">
                                            <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
                                                <c:if test="${sessionScope.studentID != null}">
                                                <li><a href="StudentClassController">Courses</a></li>
                                                <li><a href="progress">Progress</a></li>
                                                </c:if>
                                                 <c:if test="${sessionScope.teacherID != null}">
                                                <li><a href="TeacherAssignmentController">Teacher class</a></li>
                                                </c:if>

                                            <c:if  test="${sessionScope.adminLogin != null}">
                                                <li><a href="AdminController?action=update-profile">Dashboard</a></li>
                                                </c:if>
                                        </ul>
                                    </div>

                                    <div class="tgmenu__action">
                                        <ul class="list-wrap">
                                            <li class="header-btn login-btn">
                                                <c:choose>
                                                    <c:when test="${sessionScope.adminLogin != null}">
                                                        <a href="${pageContext.request.contextPath}/AdminLogoutController">Log out</a>
                                                    </c:when>
                                                    <c:when test="${sessionScope.studentID != null && sessionScope.studentID > 0}">
                                                        <a href="${pageContext.request.contextPath}/StudentLogoutController">Log out</a>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <a href="${pageContext.request.contextPath}/LoginController">Log in</a>
                                                    </c:otherwise>
                                                </c:choose>
                                            </li>
                                        </ul>
                                    </div>
                                    <div class="mobile-login-btn">
                                        <a href="login.html"><img src="${pageContext.request.contextPath}/assets/img/icons/user.svg" alt="" class="injectable"></a>
                                    </div>
                                    <div class="mobile-nav-toggler"><i class="tg-flaticon-menu-1"></i></div>
                                </nav>
                            </div>
                            <div class="tgmobile__menu">
                                <nav class="tgmobile__menu-box">
                                    <div class="close-btn"><i class="tg-flaticon-close-1"></i></div>
                                    <div class="nav-logo">
                                        <a href="home"><img style="width: 150px" src="https://cdn.haitrieu.com/wp-content/uploads/2021/10/Logo-Dai-hoc-FPT.png" alt="Logo"></a>
                                    </div>
                                    <div class="tgmobile__search">
                                        <form action="#">
                                            <input type="text" placeholder="Search here...">
                                            <button><i class="fas fa-search"></i></button>
                                        </form>
                                    </div>
                                    <div class="tgmobile__menu-outer">

                                    </div>
                                    <div class="social-links">
                                        <ul class="list-wrap">
                                            <li><a href="#"><i class="fab fa-facebook-f"></i></a></li>
                                            <li><a href="#"><i class="fab fa-twitter"></i></a></li>
                                            <li><a href="#"><i class="fab fa-instagram"></i></a></li>
                                            <li><a href="#"><i class="fab fa-linkedin-in"></i></a></li>
                                            <li><a href="#"><i class="fab fa-youtube"></i></a></li>
                                        </ul>
                                    </div>
                                </nav>
                            </div>
                            <div class="tgmobile__menu-backdrop"></div>
                        </div>
                    </div>
                </div>
            </div>
        </header>
        <!-- header-area-end -->

        <!-- main-area -->
        <main class="main-area">
            <section class="dashboard__area section-pb-120">
                <div class="container">
                    <div class="dashboard__inner-wrap">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="container">
                                    <h2 class="mb-4">List of Classes</h2>
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

                                    <div class="row">
                                        <c:choose>
                                       
                                            <c:when test="${classInfors == null || classInfors.size() == 0}">
                                                <div class="col-12">
                                                    <div class="alert alert-warning text-center" role="alert">
                                                        <strong>Note:</strong> There are no classes available at the moment. Please check back later.
                                                    </div>
                                                </div>
                                            </c:when>

                                            <c:otherwise>
                                                <c:forEach var="cla" items="${classInfors}">
                                                    <c:set value="${getCourse.getCourse(cla.courseID)}" var="course" />
                                                    <c:set value="${getTeacher.getTeacher(cla.teacherID)}" var="teacher" />
                                                    <div class="col-lg-4 col-md-6 mb-4">
                                                        <div class="courses__item shine__animate-item">
                                                            <div class="courses__item-thumb">
                                                                <a href="StudentClassController?action=view&classID=${cla.classID}" class="shine__animate-link">
                                                                    <img src="https://via.placeholder.com/200x200.png?text=${cla.className}" alt="Class Image">
                                                                </a>
                                                            </div>
                                                            <div class="courses__item-content">
                                                                <ul class="courses__item-meta list-wrap">
                                                                    <li class="courses__item-tag">
                                                                        <a href="StudentClassController?action=view&classID=${cla.classID}">Class: ${cla.className}</a>
                                                                    </li>
                                                                    <li class="avg-rating"><i class="fas fa-user"></i> Teacher: ${teacher.name}</li>
                                                                </ul>

                                                                <h5 class="title">
                                                                    <a href="StudentClassController?action=view&classID=${cla.classID}">Course: ${course.courseCode}</a>
                                                                </h5>

                                                                <p class="author">
                                                                    <strong>Semester:</strong> ${cla.semesterName} <br>
                                                                    <strong>Status:</strong> ${cla.status == 1 ? 'Active' : 'Inactive'}
                                                                </p>
                                                                <c:if test="${sessionScope.studentID != null}">
                                                                    <div class="courses__item-bottom">
                                                                        <div class="button">
                                                                            <a href="StudentClassController?action=view&classID=${cla.classID}">
                                                                                <span class="text">View Class</span>
                                                                                <i class="flaticon-arrow-right"></i>
                                                                            </a>
                                                                        </div>
                                                                        <div class="button">
                                                                            <a onclick="return confirm('Are you sure to join this class?')" href="StudentClassController?action=ask-join&classID=${cla.classID}" class="btn btn-warning">Ask to join</a>
                                                                        </div>
                                                                    </div>
                                                                </c:if>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </c:forEach>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </main>
        <footer class="footer__area">
            <div class="footer__bottom">
                <div class="container">
                    <div class="row align-items-center">
                        <div class="col-md-7">
                            <div class="copy-right-text">
                                <p>© 2024 FPT University - Leading the Future of Education</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </footer>
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
