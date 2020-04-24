package practice10;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/input")
public class Servlet extends HttpServlet {
    List<String> listValues = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession(true);
        httpSession.getAttribute("listValues");
        resp.sendRedirect("input.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String inputValue = req.getParameter("input");
        String clear = req.getParameter("clear");

        HttpSession httpSession = req.getSession();

        if(clear != null && clear.equalsIgnoreCase("clear")) {
            listValues.clear();
            httpSession.setAttribute("listValues", listValues);
        } else {
            listValues.add(inputValue);

            httpSession.setAttribute("listValues", listValues);
        }

        resp.sendRedirect("input");
    }
}
