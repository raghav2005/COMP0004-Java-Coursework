<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">

    const ctx = document.getElementById('graphToShow');

    new Chart(ctx, {
        type: 'bar',
        data: {
            labels: <%= request.getAttribute("labels") %>,
            datasets: [{
                label: "<%= (String) request.getAttribute("columnToDisplay") %>",
                data: <%= request.getAttribute("data") %>,
                borderWidth: 1
            }]
        }
    });

</script>