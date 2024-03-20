<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <jsp:include page="/head.jsp" />
  <title>View Graph</title>
</head>
<body>
<div class="container-fluid">
  <jsp:include page="/navbar.jsp" />

  <div class="row">
    <div class="col-md-12">
      <canvas id="graphToShow"></canvas>
    </div>
  </div>

  <script type="text/javascript">

    <%
      String column = (String) request.getAttribute("columnToDisplay");
    %>

    const ctx = document.getElementById('graphToShow');

    new Chart(ctx, {
      type: 'bar',
      data: {
        labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
        datasets: [{
          label: '# of Votes',
          data: [12, 19, 3, 5, 2, 3],
          borderWidth: 1
        }]
      },
      options: {
        scales: {
          y: {
            beginAtZero: true
          }
        }
      }
    });

  </script>

</div>
<jsp:include page="/foot.jsp" />
</body>
</html>
