<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    boolean displayPatientStuff = ((!((String) request.getAttribute("activeNavTab") == "home") &&
            !((String) request.getAttribute("activeNavTab") == "error")) ||
            ((String) request.getAttribute("activeNavTab") == "home" &&
                    !((String) request.getAttribute("filename") == null)));
    ArrayList<String> columnNames = (ArrayList<String>) request.getAttribute("columnNames");
%>

<div class="row">
    <div class="col-md-12">
        <nav class="navbar navbar-dark bg-dark fixed-top navbar-expand-sm">
            <div class="container-fluid navbar_wrapper">

                <a class="navbar-brand" href="#">Patient Data App</a>
                <button class="navbar-toggler button" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="nav navbar-nav me-auto">

                        <li class="nav-item">
                            <a class="nav-link" href="<%= request.getContextPath() %>/" id="home">Home</a>
                        </li>

                        <% if (displayPatientStuff) { %>
                            <li class="nav-item">
                                <a class="nav-link" href="<%= request.getContextPath() %>/patientList.html" id="patientList">Patient List</a>
                            </li>
                        <% } %>

                    </ul>

                    <script type="text/javascript">
                        document.getElementById("<%= (String) request.getAttribute("activeNavTab") %>").classList.add("active");
                    </script>
                </div>

                <% if (displayPatientStuff) { %>

                    <div class="JSONDownload" style="margin-right: 3em;">
                        <form action="<%= request.getContextPath() %>/JSONDownload" method="post" id="JSONDownload_form">
                            <button type="submit" class="button">
                                Download JSON <i class="fa fa-sharp fa-solid fa-file"></i>
                            </button>
                        </form>
                    </div>

                    <div class="graph" style="margin-right: 3em;">
                        <form action="<%= request.getContextPath() %>/graph" method="get" id="graph_form">

                            <select name="graph" id="graph" required>
                                <%
                                    for (String columnName : columnNames) {
                                %>
                                <option value="<%= columnName %>"><%= columnName %></option>
                                <% } %>
                                <option value="AGE">AGE</option>
                            </select>

                            <input type="text" name="search" value="<%= (String) request.getAttribute("search_field") %>" hidden="hidden"/>

                            <button type="submit" class="button">
                                <i class="fa fa-sharp fa-solid fa-bar-chart"></i>
                            </button>
                        </form>
                    </div>

                    <div class="sort" style="margin-right: 3em;">
                        <form action="<%= request.getContextPath() %>/sort" method="get" id="sort_form">

                            <select name="sort" id="sort" required>
                                <%
                                    for (String columnName : columnNames) {
                                %>
                                <option value="<%= columnName %>"><%= columnName %></option>
                                <% } %>
                                <option value="AGE">AGE</option>
                            </select>

                            <select name="order" id="order" required>
                                <option value="ascending">ascending</option>
                                <option value="descending">descending</option>
                            </select>

                            <input type="text" name="search" value="<%= (String) request.getAttribute("search_field") %>" hidden="hidden"/>

                            <button type="submit" class="button">
                                <i class="fa fa-sharp fa-solid fa-sort"></i>
                            </button>
                            <button type="button" onclick="resetSort()" class="button">Reset</button>
                        </form>
                    </div>

                    <div class="search">
                        <form action="<%= request.getContextPath() %>/search" method="get" id="search_form">
                            <input type="text" value="<%= (String) request.getAttribute("filename") %>" hidden="hidden" name="filename" />
                            <input type="text" placeholder="Search..." name="search" value="<%= (String) request.getAttribute("search_field") %>"/>
                            <button type="submit" class="button">
                                <i class="fa fa-sharp fa-solid fa-search" style="font-size: 1.35em;"></i>
                            </button>
                            <button type="button" onclick="resetSearch()" class="button">Reset</button>
                        </form>
                    </div>

                    <jsp:include page="resetFeatures.jsp" />
                <% } %>

            </div>
        </nav>
    </div>
</div>