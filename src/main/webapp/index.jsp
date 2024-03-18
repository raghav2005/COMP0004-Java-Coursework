<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <jsp:include page="/head.jsp" />
    <title>Home</title>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="/navbar.jsp" />

    <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4">
            <h1 style="text-align: center;">Welcome to the Patient Data App!</h1>
        </div>
        <div class="col-md-4"></div>
    </div>

    <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4" style="text-align: center;">
            <form action="<%= request.getContextPath() %>/patientList.html" method="get">
                <label for="filename">Enter Filename:</label>
                <input type="text" id="filename" name="filename" required>
                <button type="submit">Submit</button>
            </form>
        </div>
        <div class="col-md-4"></div>
    </div>

</div>
<jsp:include page="/foot.jsp" />
</body>
</html>
