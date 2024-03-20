package ucl.ac.uk.servlets.ViewPatients;

import com.fasterxml.jackson.core.JsonProcessingException;
import ucl.ac.uk.model.JSONWriter;
import ucl.ac.uk.servlets.AbstractServlets.AbstractPatientsFeaturesServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/JSONDownload")
public class DownloadJSONServlet extends AbstractPatientsFeaturesServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String newFilename = model.getNewFilename(filename).substring(5, model.getNewFilename(filename).lastIndexOf(".")) + ".json";
        String data = null;

        try {
            data = JSONWriter.writeJSON(model.getDataFrame());
        } catch (JsonProcessingException exception) {
            request.setAttribute("errorMessage", exception.getMessage());
            redirectToError(request, response);
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + newFilename + "\"");

        PrintWriter writer = response.getWriter();
        writer.print(data);
        writer.flush();
    }

}
