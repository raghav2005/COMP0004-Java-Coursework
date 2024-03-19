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
        if (updateRequestAttributes(request, response) == null) return;
        request = updateRequestAttributes(request, response);

        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/addPatient.jsp");
        dispatch.forward(request, response);
    }

    // to go to the add page
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("ID", model.generateUniqueUUID());
        processRequestAndResponse(request, response);
    }

    // for when the "add patient" button clicked in the add page
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ArrayList<String> columnNames = processColumnNames(request, response);
        if (columnNames == null) return;

        if (updateRequestAttributes(request, response) == null) return;
        request = updateRequestAttributes(request, response);

        ArrayList<String> newRow = new ArrayList<>();
        for (String columnName : columnNames) {
            newRow.add(request.getParameter(columnName + "_field"));
        }
        model.addAndWrite(filename, newRow);

        super.processRequestAndResponse(request, response);

    }

}
