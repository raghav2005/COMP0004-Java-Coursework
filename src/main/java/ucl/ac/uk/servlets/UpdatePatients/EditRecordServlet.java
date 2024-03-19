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

@WebServlet("/edit")
public class EditRecordServlet extends AbstractPatientsFeaturesServlet {

    @Override
    protected void processRequestAndResponse(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (updateRequestAttributes(request, response) == null) return;
        request = updateRequestAttributes(request, response);

        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/addPatient.jsp");
        dispatch.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ArrayList<String> columnNames = processColumnNames(request, response);
        if (columnNames == null) return;

        ArrayList<String> editingRow = model.getDataFrame().getRow(columnNames.getFirst(), request.getParameter("editIDValue"));

        for (int i = 0; i < columnNames.size(); i++) {
            request.setAttribute(columnNames.get(i) + "_value", editingRow.get(i));
        }
        request.setAttribute("routePath", "edit");

        processRequestAndResponse(request, response);
    }

}
