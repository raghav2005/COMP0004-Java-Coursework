package ucl.ac.uk.servlets.UpdatePatients;

import ucl.ac.uk.servlets.AbstractServlets.AbstractPatientsFeaturesServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/add")
public class AddRecordServlet extends AbstractPatientsFeaturesServlet {

    @Override
    protected void processRequestAndResponse(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (updateRequestAttributes(request, response) == null) {
            redirectToError(request, response);
            return;
        }
        request = updateRequestAttributes(request, response);

        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/addPatient.jsp"); // different JSP page
        dispatch.forward(request, response);
    }

    // from patientList page to adding page
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("ID_value", model.generateUniqueUUID()); // for readonly ID field
        request.setAttribute("routePath", "add"); // edit or add
        processRequestAndResponse(request, response);
    }

    // adding new row to patientList and showing it
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ArrayList<String> columnNames = processColumnNames(request, response);
        if (columnNames == null) {
            redirectToError(request, response);
            return;
        }

        if (updateRequestAttributes(request, response) == null) {
            redirectToError(request, response);
            return;
        }
        request = updateRequestAttributes(request, response);

        ArrayList<String> newRow = new ArrayList<>();
        for (String columnName : columnNames) {
            newRow.add(request.getParameter(columnName + "_field"));
        }
        model.addAndWrite(filename, newRow);

        super.processRequestAndResponse(request, response);

    }

}
