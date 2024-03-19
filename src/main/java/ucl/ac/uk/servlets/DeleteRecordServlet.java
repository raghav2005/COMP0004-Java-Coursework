package ucl.ac.uk.servlets;

import ucl.ac.uk.model.ModelFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/delete")
public class DeleteRecordServlet extends ViewPatientsServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        model.deleteAndWrite(filename, request.getParameter("delete"));

        ArrayList<String> columnNames = processColumnNames(request, response);
        if (columnNames == null) return;

        ArrayList<ArrayList<String>> allRows = getAllRows(request);

        request.setAttribute("columnNames", columnNames);
        request.setAttribute("allRows", allRows);
        request.setAttribute("activeNavTab", "patientList");
        request.setAttribute("filename", ViewPatientsServlet.filename);
        request.setAttribute("search_field", request.getParameter("search") == null ? "" : request.getParameter("search"));

        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/patientList.jsp");
        dispatch.forward(request, response);

    }

}
