<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String routePath = request.getAttribute("routePath") == null ? "/error.html" : (String) request.getAttribute("routePath");
%>

<html>
<head>
    <jsp:include page="/head.jsp" />
    <title><%= routePath.substring(0, 1).toUpperCase() + routePath.substring(1) %> Patient</title>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="/navbar.jsp" />

    <div class="row">
        <div class="col-md-12">

            <form action="<%= request.getContextPath() + "/" + routePath %>" method="post" id="<%= routePath %>_form" style="margin-top: 0.5em;">

                <input type="text" name="search_field" value="<%= request.getAttribute("search_field") == null ? "" : request.getAttribute("search_field") %>" hidden="hidden" />

                <div class="row" style="margin-bottom: 0.5em;">
                    <div class="col-md-1" style="text-align: left">
                        <label for="ID_field">ID:</label>
                    </div>
                    <div class="col-md-11">
                        <input class="columnInputs" type="text" name="ID_field" id="ID_field" value="<%= request.getAttribute("ID_value") %>" style="width: 100%" readonly />
                    </div>
                </div>

                <%
                    String value;
                    ArrayList<String> columnNames = (ArrayList<String>) request.getAttribute("columnNames");
                    for (int i = 1; i < columnNames.size(); i++) {
                        value = columnNames.get(i).toUpperCase();
                %>

                    <div class="row" style="margin-bottom: 0.5em;">
                        <div class="col-md-1" style="text-align: left">
                            <label for="<%= value %>_field"><%= value %>:</label>
                        </div>
                        <div class="col-md-11">
                            <input class="columnInputs" type="text" name="<%= value %>_field" id="<%= value %>_field" placeholder="<%= value %>" value="<%= request.getAttribute(value + "_value") == null ? "" : request.getAttribute(value + "_value") %>" style="width: 100%" />
                        </div>
                    </div>

                <% } %>

                <div class="row" style="margin-top: 0.5em;">
                    <div class="col-md-1">
                        <form action="<%= request.getContextPath() %>/search" method="get" id="search_form">
                            <input type="text" value="<%= (String) request.getAttribute("filename") %>" hidden="hidden" name="filename" />
                            <button class="button" form="search_form" type="submit" style="width: 100%; height: 200%;">Go Back</button>
                        </form>
                    </div>
                    <div class="col-md-9"></div>
                    <div class="col-md-1">
                        <button class="button" form="<%= routePath %>_form" type="reset" style="width: 100%; height: 200%;" onclick="clearInputs()">Clear</button>
                    </div>
                    <div class="col-md-1">
                        <button class="button" form="<%= routePath %>_form" type="submit" style="width: 100%; height: 200%;">Submit</button>
                    </div>
                </div>

            </form>

            <jsp:include page="resetFeatures.jsp" />
        </div>
    </div>

</div>
<jsp:include page="/foot.jsp" />
</body>
</html>
