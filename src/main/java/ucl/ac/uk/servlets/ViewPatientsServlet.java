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

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Model model;
        String filename = request.getParameter("filename");

        if (filename == null || filename.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }

        try {
            model = ModelFactory.getModel(filename);
        } catch (IOException exception) {
            request.setAttribute("errorMessage", "Error: " + exception.getMessage());
//            request.getRequestDispatcher("error.jsp").forward(request, response);
//            response.sendRedirect(request.getContextPath() + "/error.html");
            ServletContext context = getServletContext();
            RequestDispatcher dispatch = context.getRequestDispatcher("/error.jsp");
            dispatch.forward(request, response);
            return;
        }

        ArrayList<String> columnNames = model.getDataFrame().getColumnNames();
        ArrayList<ArrayList<String>> allRows = new ArrayList<>();
        ArrayList<String> row;

        for (int i = 0; i < model.getDataFrame().getRowCount(); i++) {
            row = new ArrayList<>();
            for (String columnName : columnNames) {
                row.add(model.getDataFrame().getValue(columnName, i));
            }
            allRows.add(row);
        }

        request.setAttribute("columnNames", columnNames);
        request.setAttribute("allRows", allRows);
        request.setAttribute("activeNavTab", "patientList");
        request.setAttribute("filename", filename);

        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/patientList.jsp");
        dispatch.forward(request, response);
    }

}