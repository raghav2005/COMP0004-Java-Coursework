<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String errorMessage = request.getAttribute("errorMessage") == null ? "" : (String) request.getAttribute("errorMessage");
%>
<html>
<head>
    <jsp:include page="/head.jsp" />
    <title>Error</title>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="/navbar.jsp" />

    <div class="row">
        <div class="col-md-12" style="text-align: center">
            <h1 style="color: red; text-align: center">Error</h1>
            <p style="color: red; text-align: center"><%= errorMessage %></p>
        </div>
    </div>
</div>
<jsp:include page="/foot.jsp" />
</body>
</html>