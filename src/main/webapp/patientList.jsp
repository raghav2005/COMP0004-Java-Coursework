<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Patient Data App</title>
</head>
<body>
<div class="main">

  <!-- NAVBAR - CHANGE FILENAME, ETC. -->

  <h2>Patients:</h2>

  <table style="width: 100%">

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
</body>
</html>
