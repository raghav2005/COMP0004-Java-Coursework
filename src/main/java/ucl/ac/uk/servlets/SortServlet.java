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

@WebServlet("/sort")
public class SortServlet extends ViewPatientsServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Model model = ModelFactory.getModel();

        ArrayList<String> columnNames = processColumnNames(request, response);
        if (columnNames == null) return;

        String searchWord = request.getParameter("search") == null ? "" : request.getParameter("search");
        ArrayList<ArrayList<String>> allRows = model.sort(request.getParameter("sort"), model.search(searchWord), !request.getParameter("order").equals("ascending"));

        request.setAttribute("columnNames", columnNames);
        request.setAttribute("allRows", allRows);
        request.setAttribute("activeNavTab", "patientList");
        request.setAttribute("filename", ViewPatientsServlet.filename);

        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/patientList.jsp");
        dispatch.forward(request, response);

    }

}
