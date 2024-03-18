<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/head.jsp" />
    <title>Error</title>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="/navbar.jsp" />
    <h1>Error</h1>
    <p><%= (String) request.getAttribute("errorMessage") %></p>
</div>
</body>
</html>