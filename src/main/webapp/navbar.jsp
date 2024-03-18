<div class="row">
    <div class="col-md-12">
        <nav class="navbar navbar-dark bg-dark fixed-top navbar-expand-sm">
            <div class="container-fluid navbar_wrapper">
                <a class="navbar-brand" href="#">Patient Data App</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="nav navbar-nav me-auto">
                        <li class="nav-item">
                            <a class="nav-link active" href="/">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/patientList.html">patientList</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#"><%= request.getContextPath() %></a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    </div>
</div>

<div class="row">
    <div class="col-md-12">
        <% for (int i = 0; i < 3; i++) { %>
            <br />
        <% } %>
    </div>
</div>