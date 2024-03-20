package ucl.ac.uk.servlets.AbstractServlets;

import ucl.ac.uk.model.Model;
import ucl.ac.uk.model.ModelFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public abstract class AbstractPatientsServlet extends HttpServlet {

    protected static String filename;
    protected static Model model;

    // error page with request.setAttribute("errorMessage", <some value>) before this called
    protected void redirectToError(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/error.jsp");
        dispatch.forward(request, response);
    }

    protected ArrayList<String> processColumnNames(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        model = ModelFactory.getModel();

        ArrayList<String> columnNames;
        try {
            columnNames = model.getDataFrame().getColumnNames();
        } catch (NullPointerException exception) {
            request.setAttribute("errorMessage", "Error: " + exception.getMessage());
            redirectToError(request, response);

            return null;
        }

        return columnNames;

    }

    protected ArrayList<ArrayList<String>> getAllRowsAfterSearchSort(HttpServletRequest request) {
        String searchWord = request.getParameter("search") == null ? "" : request.getParameter("search");
        String order = request.getParameter("order") == null ? "" : request.getParameter("order");

        ArrayList<ArrayList<String>> allRows = model.sort(request.getParameter("sort"), model.search(searchWord), !order.equals("ascending"));

        return allRows;

    }

    protected HttpServletRequest updateRequestAttributes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<String> columnNames = processColumnNames(request, response);
        if (columnNames == null) return null;

        ArrayList<ArrayList<String>> allRows = getAllRowsAfterSearchSort(request);

        // set all attributes
        request.setAttribute("columnNames", columnNames);
        request.setAttribute("allRows", allRows);
        request.setAttribute("activeNavTab", "patientList");
        request.setAttribute("filename", filename);
        request.setAttribute("search_field", "");

        return request;

    }

}
