<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <jsp:include page="head.jsp" />
  <title>Patient Data App</title>
</head>
<body>
<div class="container">

  <!-- NAVBAR - CHANGE FILENAME, ETC. -->
  <nav class="navbar navbar-default navbar-expand-sm navbar-dark">
    <div class="container-fluid navbar_wrapper">
      <div class="navbar-header">
        <a class="navbar-brand" href="#">Patient Data App</a>
      </div>
      <ul class="nav navbar-nav">
        <li class="active"><a href="/">Home</a></li>
        <li><a href="#">Page 1</a></li>
        <li><a href="#">Page 2</a></li>
      </ul>
    </div>
  </nav>

  <div class="row">
    <div class="col-md-12">
      <h2>Patients:</h2>
    </div>
  </div>

  <div class="row">
    <div class="col-md-12">
      <table>

        <tr>
          <%
            ArrayList<String> columnNames = (ArrayList<String>) request.getAttribute("columnNames");
            for (String columnName : columnNames) {
          %>
          <th><%= columnName %></th>
          <% } %>
        </tr>

        <%
          ArrayList<ArrayList<String>> allRows = (ArrayList<ArrayList<String>>) request.getAttribute("allRows");
          for (ArrayList<String> row : allRows) {
        %>
        <tr>
          <%
            for (String field : row) {
          %>
          <td><%= field %></td>
          <% } %>
        </tr>
        <% } %>

      </table>
    </div>
  </div>

</div>
</body>

<jsp:include page="foot.jsp" />

</html>
