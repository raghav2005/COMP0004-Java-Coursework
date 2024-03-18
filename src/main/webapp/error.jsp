<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/head.jsp" />
    <title>Error</title>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="/navbar.jsp" />
    <h1 style="color: red; text-align: center">Error</h1>
    <p style="color: red; text-align: center"><%= (String) request.getAttribute("errorMessage") %></p>
</div>
</body>
</html>