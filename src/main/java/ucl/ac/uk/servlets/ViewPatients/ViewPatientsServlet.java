package ucl.ac.uk.servlets.ViewPatients;

import ucl.ac.uk.servlets.AbstractServlets.AbstractPatientsServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/patientList.html", "/patientList"})
public class ViewPatientsServlet extends AbstractPatientsServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        AbstractPatientsServlet.filename = processFilename(request, response);
        if (AbstractPatientsServlet.filename == null) {
            redirectToError(request, response);
            return;
        }

        if (updateRequestAttributes(request, response) == null) {
            redirectToError(request, response);
            return;
        }
        request = updateRequestAttributes(request, response);

        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/patientList.jsp");
        dispatch.forward(request, response);
    }

}