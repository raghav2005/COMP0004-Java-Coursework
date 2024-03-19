<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script type="text/javascript">

    function deleteRecord(delete_id) {
        document.getElementById("delete_field").value = delete_id;
        document.getElementById("delete_form").submit();
    }

    function editRecord(edit_id) {
        document.getElementById("edit_form_" + edit_id).submit();
    }

</script>