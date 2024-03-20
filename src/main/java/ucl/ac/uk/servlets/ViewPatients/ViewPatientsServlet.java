package ucl.ac.uk.servlets.ViewPatients;

import ucl.ac.uk.model.ModelFactory;
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

    private String processFilename(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String filename = "data/" + request.getParameter("filename");
        if (filename.equals("data/") || filename.equals("data/null")) {
            response.sendRedirect(request.getContextPath() + "/");
            return null;
        }

        try {
            model = ModelFactory.getModel(filename);
        } catch (IOException exception) {
            request.setAttribute("errorMessage", "Error: " + exception.getMessage());
            redirectToError(request, response);

            return null;
        }

        return filename;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (processFilename(request, response) == null) {
            redirectToError(request, response);
            return;
        }
        AbstractPatientsServlet.filename = processFilename(request, response); // load new filename to static attribute inherited

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