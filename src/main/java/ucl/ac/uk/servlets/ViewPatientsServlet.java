package ucl.ac.uk.servlets;

import ucl.ac.uk.model.Model;
import ucl.ac.uk.model.ModelFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/patientList.html")
public class ViewPatientsServlet extends HttpServlet {

    protected static String filename;

    protected String processFilename(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Model model;
        String filename = "data/" + request.getParameter("filename");

        if (filename.equals("data/") || filename.equals("data/null")) {
            response.sendRedirect(request.getContextPath() + "/");
            return null;
        }

        try {
            model = ModelFactory.getModel(filename);
        } catch (IOException exception) {
            request.setAttribute("errorMessage", "Error: " + exception.getMessage());

            ServletContext context = getServletContext();
            RequestDispatcher dispatch = context.getRequestDispatcher("/error.jsp");
            dispatch.forward(request, response);

            return null;
        }

        return filename;

    }

    protected ArrayList<String> processColumnNames(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Model model = ModelFactory.getModel();

        ArrayList<String> columnNames = null;
        try {
            columnNames = model.getDataFrame().getColumnNames();
        } catch (NullPointerException exception) {
            request.setAttribute("errorMessage", "Error: " + exception.getMessage());

            ServletContext context = getServletContext();
            RequestDispatcher dispatch = context.getRequestDispatcher("/error.jsp");
            dispatch.forward(request, response);

            return null;
        }

        return columnNames;

    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        filename = processFilename(request, response);
        if (filename == null) return;

        Model model = ModelFactory.getModel();

        ArrayList<String> columnNames = processColumnNames(request, response);
        if (columnNames == null) return;

        ArrayList<ArrayList<String>> allRows = model.getAllRows();

        request.setAttribute("columnNames", columnNames);
        request.setAttribute("allRows", allRows);
        request.setAttribute("activeNavTab", "patientList");
        request.setAttribute("filename", filename);
        request.setAttribute("search_field", "");

        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/patientList.jsp");
        dispatch.forward(request, response);

    }

}