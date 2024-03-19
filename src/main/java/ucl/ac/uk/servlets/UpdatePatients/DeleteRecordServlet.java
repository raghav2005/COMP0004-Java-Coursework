package ucl.ac.uk.servlets.UpdatePatients;

import ucl.ac.uk.servlets.AbstractServlets.AbstractPatientsFeaturesServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete")
public class DeleteRecordServlet extends AbstractPatientsFeaturesServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        model.deleteAndWrite(filename, request.getParameter("delete"));
        super.processRequestAndResponse(request, response);
    }

}
