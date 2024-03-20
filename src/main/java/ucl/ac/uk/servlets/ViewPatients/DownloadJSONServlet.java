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
        // get filename w/ timestamp and change extention from .csv to .json (first 5 are data/)
        String newFilename = model.getNewFilename(filename).substring(5, model.getNewFilename(filename).lastIndexOf(".")) + ".json";
        String data = null;

        try {
            data = JSONWriter.writeJSON(model.getDataFrame());
        } catch (JsonProcessingException exception) {
            request.setAttribute("errorMessage", exception.getMessage());
            redirectToError(request, response);
            return;
        }

        // let user download file & choose name & location
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + newFilename + "\""); // default filename for new file

        PrintWriter writer = response.getWriter();
        writer.print(data);
        writer.flush();
    }

}
