package practice9;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Arrays;

public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        String listAsString = event.getServletContext().getInitParameter("list");

        String [] sportTypes = listAsString.split(" ");

        event.getServletContext().setAttribute("list", Arrays.asList(sportTypes));
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {

    }
}
