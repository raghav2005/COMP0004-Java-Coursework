<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script type="text/javascript">

    function clearInputs() {
        Array.from(document.getElementsByClassName("columnInputs")).forEach(element => element.value = "");
        <%
           ArrayList<String> columnNames = (ArrayList<String>) request.getAttribute("columnNames");
           String value;
           for (int i = 1; i < columnNames.size(); i++) {
               value = columnNames.get(i).toUpperCase();
        %>
            Array.from(document.getElementsByName("<%= value %>_field")).forEach(element => element.value = "");
        <% } %>
    }

</script>