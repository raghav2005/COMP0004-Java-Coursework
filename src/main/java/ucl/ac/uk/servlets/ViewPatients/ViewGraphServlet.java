package ucl.ac.uk.servlets.ViewPatients;

import ucl.ac.uk.servlets.AbstractServlets.AbstractPatientsFeaturesServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

@WebServlet("/graph")
public class ViewGraphServlet extends AbstractPatientsFeaturesServlet {

    @Override
    protected void processRequestAndResponse(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (updateRequestAttributes(request, response) == null) {
            redirectToError(request, response);
            return;
        }
        request = updateRequestAttributes(request, response);

        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/graphPage.jsp"); // different page
        dispatch.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String columnName = request.getParameter("graph");
        request.setAttribute("columnToDisplay", columnName);

        HashMap<String, Integer> frequencies = model.getBarChart(columnName, request.getParameter("search"));
        request.setAttribute("labels", frequencies.keySet().stream().map(String::valueOf).collect(Collectors.toCollection(ArrayList::new))); // convert to correct format for javascript
        request.setAttribute("data", frequencies.values());

        processRequestAndResponse(request, response);
    }

}
