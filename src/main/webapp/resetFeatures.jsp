<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script type="text/javascript">

    function resetSort() {
        <% request.setAttribute("sort_field", ""); %>
        document.getElementById("sort_form").submit();
    }

    function resetSearch() {
        <% request.setAttribute("search_field", ""); %>
        Array.from(document.getElementsByName("search")).forEach(element => element.value = "");
        document.getElementById("search_form").submit();
    }

</script>