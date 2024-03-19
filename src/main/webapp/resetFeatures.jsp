<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script type="text/javascript">

    function resetSort() {
        <% request.setAttribute("sort_field", ""); %>
        document.getElementById("sort_form").submit();
    }

    function resetSearch() {
        <% request.setAttribute("search_field", ""); %>
        document.getElementsByName("search")[0].value = "";
        document.getElementsByName("search")[1].value = "";
        document.getElementById("search_form").submit();
    }

</script>