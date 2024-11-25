<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                                    <li><a href="ListCourseController">Courses</a></li>
                                    <c:if test="${sessionScope.adminLogin != null}">
                                        <li>
                                            <a href="${pageContext.request.contextPath}/AdminController?action=update-profile">Dashboard</a>
                                        </li>
                                    </c:if>
                                </ul>
                            </div>
                            
                            <div class="tgmenu__action">
                                <ul class="list-wrap">
                                    <li class="header-btn login-btn">
                                    <c:choose>
                                        <c:when test="${sessionScope.adminLogin != null}">
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