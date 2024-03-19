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

@WebServlet("/search")
public class SearchServlet extends ViewPatientsServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Model model = ModelFactory.getModel();

        ArrayList<String> columnNames = processColumnNames(request, response);
        if (columnNames == null) return;

        String searchWord = request.getParameter("search") == null ? "" : request.getParameter("search");
        String order = request.getParameter("order") == null ? "" : request.getParameter("order");

        ArrayList<ArrayList<String>> allRows = model.sort(request.getParameter("sort"), model.search(searchWord), !order.equals("ascending"));

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
