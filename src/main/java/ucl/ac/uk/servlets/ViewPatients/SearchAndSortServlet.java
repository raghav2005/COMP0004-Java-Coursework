package ucl.ac.uk.servlets.ViewPatients;

import ucl.ac.uk.servlets.AbstractServlets.AbstractPatientsFeaturesServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/search", "/sort"})
public class SearchAndSortServlet extends AbstractPatientsFeaturesServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        super.processRequestAndResponse(request, response);
    }

}
