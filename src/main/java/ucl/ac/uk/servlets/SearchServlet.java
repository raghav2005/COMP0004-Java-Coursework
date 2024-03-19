package ucl.ac.uk.servlets;

import ucl.ac.uk.model.Model;
import ucl.ac.uk.model.ModelFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet({"/search", "/search/clear"})
public class SearchServlet extends ViewPatientsServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Model model = ModelFactory.getModel();

        ArrayList<String> columnNames = processColumnNames(request, response);
        if (columnNames == null) return;

        ArrayList<ArrayList<String>> allRows = model.search(request.getParameter("search"));

        request.setAttribute("columnNames", columnNames);
        request.setAttribute("allRows", allRows);
        request.setAttribute("activeNavTab", "patientList");
        request.setAttribute("filename", ViewPatientsServlet.filename);

        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/patientList.jsp");
        dispatch.forward(request, response);

    }

}
