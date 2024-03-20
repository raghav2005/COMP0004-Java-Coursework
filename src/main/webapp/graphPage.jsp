<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <jsp:include page="/head.jsp" />
  <title>View Graph</title>
</head>
<body>
<div class="container-fluid">
  <jsp:include page="/navbar.jsp" />

    <div class="row">
      <div class="col-md-1">
        <form action="<%= request.getContextPath() %>/search" method="get" id="search_form">
          <input type="text" value="<%= (String) request.getAttribute("filename") %>" hidden="hidden" name="filename" />
          <input type="text" name="search_field" value="<%= request.getAttribute("search_field") == null ? "" : request.getAttribute("search_field") %>" hidden="hidden" />
          <button class="button" form="search_form" type="submit" style="width: 100%; margin-top: 0.25em;">Go Back</button>
        </form>
      </div>

      <div class="col-md-11"></div>
    </div>

  <div class="row" style="padding-top: -2.5em;">
    <div class="col-md-12">
      <canvas id="graphToShow"></canvas>
    </div>
  </div>

  <jsp:include page="graphChart.jsp" />


</div>
<jsp:include page="/foot.jsp" />
</body>
</html>
