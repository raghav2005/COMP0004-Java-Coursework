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

            <form action="<%= request.getContextPath() %>/add" method="post" id="add_form" style="margin-top: 0.5em;">

                <input type="text" name="search_field" value="<%= request.getAttribute("search_field") == null ? "" : request.getAttribute("search_field") %>" hidden="hidden" />

                <!-- AUTO-GEN ID FIELD disabled -->

                <%
                    ArrayList<String> columnNames = (ArrayList<String>) request.getAttribute("columnNames");
                    for (int i = 1; i < columnNames.size(); i++) {
                %>

                    <div class="row" style="margin-bottom: 0.5em;">
                        <div class="col-md-1" style="text-align: left">
                            <label for="<%= columnNames.get(i) %>_field"><%= columnNames.get(i).toUpperCase() %>:</label>
                        </div>
                        <div class="col-md-11">
                            <input class="columnInputs" type="text" name="<%= columnNames.get(i) %>_field" id="<%= columnNames.get(i) %>_field" placeholder="<%= columnNames.get(i) %>" value="" style="width: 100%" />
                        </div>
                    </div>

                <% } %>

                <div class="row" style="margin-top: 0.5em;">
                    <div class="col-md-10"></div>
                    <div class="col-md-1">
                        <button class="button" type="reset" style="width: 100%; height: 200%;" onclick="clearInputs()">Clear</button>
                    </div>
                    <div class="col-md-1">
                        <button class="button" type="submit" style="width: 100%; height: 200%;">Submit</button>
                    </div>
                </div>

                <!-- GO BACK BUTTON -->

            </form>

            <script>
                function clearInputs() {
                    Array.from(document.getElementsByClassName("columnInputs")).forEach(element => element.value = "");
                }
            </script>

<%--            <p>--%>
<%--                columnNames: <%= request.getAttribute("columnNames") == null ? "" : request.getAttribute("columnNames") %> <br />--%>
<%--                allRows: <%= request.getAttribute("allRows") == null ? "" : request.getAttribute("allRows") %> <br />--%>
<%--                activeNavTab: <%= request.getAttribute("activeNavTab") == null ? "" : request.getAttribute("activeNavTab") %> <br />--%>
<%--                filename: <%= request.getAttribute("filename") == null ? "" : request.getAttribute("filename") %> <br />--%>
<%--                search_field: <%= request.getAttribute("search_field") == null ? "" : request.getAttribute("search_field") %> <br />--%>
<%--            </p>--%>
        </div>
    </div>

</div>
<jsp:include page="/foot.jsp" />
</body>
</html>
