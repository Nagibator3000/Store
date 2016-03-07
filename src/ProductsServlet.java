import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProductsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse
            httpServletResponse) throws ServletException, IOException {
        httpServletResponse.setContentType("text/html");
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        String end = "</body>\n" +
                "\n" +
                "</html>";
        String outputString = "<!DOCTYPE html><html>\n" +
                "<head>\n" +
                "<title>Store</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>Products</h1>\n" +
                "<a href = \"products\">Go to products</a><br>" +
                "<a href = \"customers\">Go to customers</a><br>" +
                "<a href = \"purchases\">Go to purchases</a>";

        for (int i = 0; i < 5; i++) {
            outputString = outputString + "i=" + i + "<br>";
        }
        httpServletResponse.getWriter().println(outputString);
    }
}
