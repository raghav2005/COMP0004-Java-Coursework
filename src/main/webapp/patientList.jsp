<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <jsp:include page="/head.jsp" />
  <title>Patient List</title>
</head>
<body>
<jsp:include page="/navbar.jsp" />
<div class="container">

  <!-- NAVBAR - CHANGE FILENAME, ETC. -->

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
<jsp:include page="/foot.jsp" />
</body>
</html>
