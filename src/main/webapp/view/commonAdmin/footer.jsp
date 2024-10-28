

</div>
</div>
</div>
</div>
</section>
</main>
<!-- main-area-end -->

<!-- footer-area -->
<jsp:include page="../../commonAdmin/home/footer-home.jsp"></jsp:include>
    <!-- footer-area-end -->

    <!-- JS here -->
<jsp:include page="../../commonAdmin/js-file.jsp"></jsp:include>

    <script>
        function confirmDeactivate(accountId) {
            if (confirm('Are you sure you want to deactivate this account?')) {
                window.location.href = '${pageContext.request.contextPath}/manage-account?action=deactivate&id=' + accountId;
            }
        }
        
        $(document).ready(function () {
                                $('#lessionTable').DataTable();

                                $('#courseFilter').on('change', function () {
                                    var selectedCourseId = $(this).val();
                                    window.location.href = 'lessions?action=list&courseId=' + selectedCourseId;
                                });
                            });
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