<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Lesson View</title>
    <!-- Bootstrap và FontAwesome để tạo giao diện hiện đại -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        body {
            background-color: #f4f6f9;
        }
        .card {
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .card-header {
            background-color: #007bff;
            color: white;
        }
        .list-group-item.active {
            background-color: #007bff;
            border-color: #007bff;
        }
        .list-group-item a {
            text-decoration: none;
        }
        .btn-success {
            background-color: #28a745;
            border-color: #28a745;
        }
        .btn-success:hover {
            background-color: #218838;
        }
        .btn-completed {
            background-color: #6c757d;
            color: white;
            cursor: default;
        }
        .progress {
            height: 10px;
        }
        .badge-primary {
            background-color: #007bff;
        }
    </style>
    <script src="https://www.youtube.com/iframe_api"></script>
    <script>
        var player;

        function onYouTubeIframeAPIReady() {
            player = new YT.Player('lessonVideo', {
                events: {
                    'onStateChange': onPlayerStateChange
                }
            });
        }

        function onPlayerStateChange(event) {
            if (event.data === YT.PlayerState.ENDED) {
                markAsCompleted();
            }
        }

        function markAsCompleted() {
            document.getElementById("completeForm").submit();
        }
    </script>
</head>
<body>
    <%@ include file="../../commonStudent/head.jsp" %>

    <div class="container-fluid mt-4">
        <c:if test="${param.error != null}">
            <div class="alert alert-danger" role="alert">
                ${param.error}
            </div>
        </c:if>

        <div class="row">
            <div class="col-md-3">
                <div class="card mb-4">
                    <div class="card-header">
                        <h4>Course Lessons</h4>
                    </div>
                    <ul class="list-group list-group-flush">
                        <c:forEach var="lession" items="${lessions}">
                            <li class="list-group-item ${lession.lessionId == currentLession.lessionId ? 'active' : ''}">
                                <a style="color: ${lession.lessionId == currentLession.lessionId ? '#fff' : '#333'}" href="LessionStudentController?action=learn&lessionId=${lession.lessionId}">
                                    ${lession.title}
                                    <c:if test="${lession.studentLession != null && lession.studentLession.isCompleted == true}">
                                        <span class="badge badge-success ml-2">Completed</span>
                                    </c:if>
                                </a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>

            <div class="col-md-9">
                <div class="card mb-4">
                    <div class="card-header">
                        <h2>${currentLession.title}</h2>
                    </div>
                    <div class="card-body">
                        <c:choose>
                            <c:when test="${currentLession.videoUrl != null}">
                                <c:choose>
                                    <c:when test="${currentLession.videoUrl.contains('youtube') || currentLession.videoUrl.contains('youtu.be')}">
                                        <iframe id="lessonVideo" width="100%" height="480" src="${currentLession.videoUrl}" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
                                    </c:when>
                                    <c:otherwise>
                                        <video id="lessonVideo" width="100%" controls onended="markAsCompleted()">
                                            <source src="${currentLession.videoUrl}" type="video/mp4">
                                            Your browser does not support the video tag.
                                        </video>
                                    </c:otherwise>
                                </c:choose>
                            </c:when>

                            <c:when test="${currentLession.videoUrl != null && currentLession.docsLink != null}">
                                <a target="_blank" href="${currentLession.docsLink}" class="badge badge-primary mb-3">Open Docs in new tab</a>
                                <iframe src="${currentLession.docsLink}" width="100%" height="600px" class="mb-3"></iframe>
                            </c:when>

                            <c:otherwise>
                                <a target="_blank" href="${currentLession.docsLink}" class="badge badge-primary mb-3">Open Docs in new tab</a>
                                <iframe src="${currentLession.docsLink}" width="100%" height="600px" class="mb-3"></iframe>
                            </c:otherwise>
                        </c:choose>
                       
                        <form id="completeForm" action="LessionStudentController?action=markCompleted" method="post">
                            <input type="hidden" name="lessionId" value="${currentLession.lessionId}">
                            <c:choose>
                                <c:when test="${currentLession.studentLession != null && currentLession.studentLession.isCompleted}">
                                    <button type="button" class="btn btn-info mt-3" disabled>
                                        <i class="fas fa-check-circle"></i> Completed
                                    </button>
                                </c:when>
                                <c:otherwise>
                                    <button type="button" class="btn btn-success mt-3" onclick="markAsCompleted()">
                                        <i class="fas fa-check-circle"></i> Mark as complete
                                    </button>
                                </c:otherwise>
                            </c:choose>
                        </form>
                    </div>
                </div>

                <div class="card mb-4">
                    <div class="card-body">
                        <h5>Description</h5>
                        <p>${currentLession.description}</p>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <%@ include file="../../commonStudent/footer.jsp" %>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
