import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


public class JettyTestLauncher {
    public static void main(String[] args) throws Exception {
        Server server = new Server(80);
        server.setHandler(new HelloWorld());
        server.start();
        server.join();
    }

    private static class HelloWorld extends AbstractHandler {
        @Override
        public void handle(String s, Request request, HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse) throws IOException, ServletException {
            httpServletResponse.setContentType("text/html");
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            request.setHandled(true);
          /*  httpServletResponse.getWriter().println("<h1>Hello from HelloServlet</h1>");*/
            httpServletResponse.getWriter().println("<!DOCTYPE html><html>\n" +
                    "<head>\n" +
                    "<title>Store</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<h1>Welcome to store</h1>\n" +
                    "<a href = \"products\">Go to products</a>"+
                    ""+
                    ""+
                    "</body>\n" +
                    "\n" +
                    "</html>");
        }
    }
}
