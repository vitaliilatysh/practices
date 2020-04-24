package practice9;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CalcController extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        int result;
        String command = request.getParameter("op");
        Integer x = Integer.parseInt(request.getParameter("x"));
        Integer y = Integer.parseInt(request.getParameter("y"));

        if (command.equalsIgnoreCase("minus")) {
            result = x - y;
        } else {
            result = x + y;
        }

        String forward = Path.RESULT_PAGE;

        request.setAttribute("result", result);

        request.getRequestDispatcher(forward).forward(request, response);
    }

}