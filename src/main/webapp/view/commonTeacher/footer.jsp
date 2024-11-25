<%-- 
    Document   : footer
    Created on : Oct 18, 2024, 1:58:53 AM
    Author     : HP
--%>

</div>
</div>
</div>
</div>
</section>
</main>
<!-- main-area-end -->

<!-- footer-area -->
<jsp:include page="../../commonTeacher/home/footer-home.jsp"></jsp:include>
    <!-- footer-area-end -->

    <!-- JS here -->
<jsp:include page="../../commonTeacher/js-file.jsp"></jsp:include>

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