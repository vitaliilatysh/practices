package practice9;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class VoteController extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        String list = request.getParameter("list");
        String forward = Path.VOTE_PAGE;
        request.setAttribute("list", list);

        request.getRequestDispatcher(forward).forward(request,response);
    }

}