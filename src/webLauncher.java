import org.eclipse.jetty.plus.servlet.ServletHandler;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


public class WebLauncher {
    public static Db db;

    public static void main(String[] args) throws Exception {
        db = new Db();
        Server server = new Server(8080);
        ServletHandler handler = new ServletHandler();
        handler.addServletWithMapping(HomeServlet.class, "/");
        handler.addServletWithMapping(ProductsServlet.class, "/products/*");
        handler.addServletWithMapping(CustomersServlet.class, "/customers/*");
        handler.addServletWithMapping(PurchasesServlet.class, "/purchases/*");
        server.setHandler(handler);
        server.start();
        server.join();
    }

}

