package ucl.ac.uk.servlets.MainPages;

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

@WebServlet({"", "/index.html", "/index"})
public class HomePageServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Model model = ModelFactory.getModel();
        ArrayList<String> files = new ArrayList<>();
        try {
            files = model.getDataFiles();
        } catch (IOException exception) {
            // will always be in data directory
            files.add("patients100.csv");
        }

        request.setAttribute("activeNavTab", "home"); // won't be able to see patientList in navbar
        request.setAttribute("files", files);

        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/index.jsp");
        dispatch.forward(request, response);
    }

}