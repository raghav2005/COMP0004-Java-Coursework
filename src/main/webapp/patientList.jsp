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

          <form action="<%= request.getContextPath() %>/add" method="get" id="add_form">
            <th colspan="2">
              <button type="submit" class="button" style="height: 2em; width: 2em;">
                <i class="fa fa-sharp fa-solid fa-plus"></i>
              </button>
            </th>
          </form>

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
                <% if (!field.isEmpty()) { %>
                  <td><%= field %></td>
                <% } else { %>
                  <td><b>-</b></td>
                <% } %>
              <% } %>

              <form action="<%= request.getContextPath() %>/edit" method="get" id="edit_form_<%= row.getFirst() %>">
                <td>
                  <input id="editIDValue" name="editIDValue" type="text" value="<%= row.getFirst() %>" hidden="hidden">
                  <button form="edit_form_<%= row.getFirst() %>" class="button" type="button" style="height: 2em; width: 2em;" onclick="editRecord('<%= row.getFirst() %>')">
                    <i class="fa fa-sharp fa-solid fa-pencil"></i>
                  </button>
                </td>
              </form>

              <td>
                <button class="button" style="height: 2em; width: 2em;" onclick="deleteRecord('<%= row.getFirst() %>')"><i class="fa fa-sharp fa-solid fa-minus"></i></button>
              </td>

            </tr>
          <% } %>
        <% } %>

      </table>
      <jsp:include page="sendIDData.jsp" />
    </div>
  </div>

  <div class="row">
    <div class="col-md-12">
      <form action="<%= request.getContextPath() %>/delete" method="post" id="delete_form">
        <input id="delete_field" type="text" name="delete" value="" hidden="hidden"/>
        <button type="submit" hidden="hidden"></button>
      </form>
    </div>
  </div>

</div>
<jsp:include page="/foot.jsp" />
</body>
</html>
