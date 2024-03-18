<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Patient Data App</title>
</head>
<body>
<div class="main">
  <h2>Patients:</h2>
  <ul>
    <%
      ArrayList<String> columnNames = (ArrayList<String>) request.getAttribute("columnNames");
      for (String columnName : columnNames) {
    %>
      <li><%=columnName%></li>
    <% } %>
  </ul>
</div>
</body>
</html>
