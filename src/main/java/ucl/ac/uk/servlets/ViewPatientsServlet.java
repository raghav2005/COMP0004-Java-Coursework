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
        try {
            model = ModelFactory.getModel("data/patients100.csv");
        } catch (IOException exception) {
            throw new IOException(exception);
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

        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/patientList.jsp");
        dispatch.forward(request, response);
    }

}