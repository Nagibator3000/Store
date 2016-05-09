package web;

import model.Db;
import org.eclipse.jetty.plus.servlet.ServletHandler;
import org.eclipse.jetty.server.Server;
import web.ApiServlets.CustomersApiServlet;
import web.ApiServlets.ProductsApiServlet;
import web.ApiServlets.PurchasesApiServlet;


public class WebLauncher {
    public static Db db;

    public static void main(String[] args) throws Exception {
        db = new Db();
        Server server = new Server(8080);
        ServletHandler handler = new ServletHandler();
        handler.addServletWithMapping(ProductsApiServlet.class, "/api/products/*");
        handler.addServletWithMapping(CustomersApiServlet.class, "/api/customers/*");
        handler.addServletWithMapping(PurchasesApiServlet.class, "/api/purchases/*");
        handler.addServletWithMapping(HomeServlet.class, "/");
        handler.addServletWithMapping(ProductsServlet.class, "/products/*");
        handler.addServletWithMapping(CustomersServlet.class, "/customers/*");
        handler.addServletWithMapping(PurchasesServlet.class, "/purchases/*");
        handler.addServletWithMapping(ReportsServlet.class, "/reports/*");
        server.setHandler(handler);
        server.start();
        server.join();
    }

}

