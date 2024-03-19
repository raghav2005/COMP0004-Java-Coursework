<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <jsp:include page="/head.jsp" />
  <title>Patient List</title>
</head>
<body>
<div class="container-fluid">
  <jsp:include page="/navbar.jsp" />

  <div class="row">
    <div class="col-md-12">
      <table>
        <tr id="header_row">
          <%
            ArrayList<String> columnNames = (ArrayList<String>) request.getAttribute("columnNames");
            for (String columnName : columnNames) {
          %>
          <th><%= columnName %></th>
          <% } %>

          <th colspan="2"><button style="height: 2em; width: 2em;"><i class="fa fa-sharp fa-solid fa-plus"></i></button></th>

        </tr>
        <%
          ArrayList<ArrayList<String>> allRows = (ArrayList<ArrayList<String>>) request.getAttribute("allRows");
          if (!(allRows == null || allRows.isEmpty())) {
            for (ArrayList<String> row : allRows) {
          %>
            <tr>

              <%
                for (String field : row) {
              %>
                <% if (field.length() > 0) { %>
                  <td><%= field %></td>
                <% } else { %>
                  <td><b>-</b></td>
                <% } %>
              <% } %>

              <td><button style="height: 2em; width: 2em;"><i class="fa fa-sharp fa-solid fa-pencil"></i></button></td>

              <td>
                <button style="height: 2em; width: 2em;" onclick="deleteRecord('<%= row.getFirst() %>')"><i class="fa fa-sharp fa-solid fa-minus"></i></button>
              </td>

              <script type="text/javascript">
                function deleteRecord(delete_id) {
                  document.getElementById("delete_field").value = delete_id;
                  document.getElementById("delete_form").submit();
                }
              </script>

            </tr>
          <% } %>
        <% } %>
      </table>
    </div>
  </div>

  <div class="row">

    <div class="col-md-4">
      <form action="<%= request.getContextPath() %>/delete" method="post" id="delete_form">
        <input id="delete_field" type="text" name="delete" value="" hidden="hidden"/>
        <button type="submit" hidden="hidden"></button>
      </form>
    </div>

    <div class="col-md-4"></div>

    <div class="col-md-4"></div>

  </div>

</div>
<jsp:include page="/foot.jsp" />
</body>
</html>
