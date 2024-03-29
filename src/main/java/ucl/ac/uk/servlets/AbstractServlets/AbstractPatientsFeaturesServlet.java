package ucl.ac.uk.servlets.AbstractServlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AbstractPatientsFeaturesServlet extends AbstractPatientsServlet {

    @Override
    protected HttpServletRequest updateRequestAttributes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (super.updateRequestAttributes(request, response) == null) return null;

        request = super.updateRequestAttributes(request, response);
        request.setAttribute("search_field", request.getParameter("search") == null ? "" : request.getParameter("search"));

        return request;

    }

    protected void processRequestAndResponse(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (updateRequestAttributes(request, response) == null) {
            redirectToError(request, response);
            return;
        }
        request = updateRequestAttributes(request, response);

        // all subclasses to patientList
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/patientList.jsp");
        dispatch.forward(request, response);

    }

}
