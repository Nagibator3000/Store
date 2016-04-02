package web;

import model.Db;
import org.eclipse.jetty.plus.servlet.ServletHandler;
import org.eclipse.jetty.server.Server;


public class WebLauncher {
    public static Db db;

    public static void main(String[] args) throws Exception {
        db = new Db();
        Server server = new Server(8080);
        ServletHandler handler = new ServletHandler();
        handler.addServletWithMapping(ProductsApiServlet.class, "/api/products/*");
        handler.addServletWithMapping(HomeServlet.class, "/");
        handler.addServletWithMapping(ProductsServlet.class, "/products/*");
        handler.addServletWithMapping(CustomersServlet.class, "/customers/*");
        handler.addServletWithMapping(PurchasesServlet.class, "/purchases/*");
        server.setHandler(handler);
        server.start();
        server.join();
    }

}

