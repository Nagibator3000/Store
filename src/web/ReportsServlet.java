package web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ReportsServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletResponse.setContentType("text/html");
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);

        httpServletResponse.getWriter().println("<!DOCTYPE html><html>\n" +
                "<head>\n" +
                "<title>Store</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>Welcome to store</h1>\n" +
                "  <style type=\"text/css\"> \n" +
                "   #rightcol {\n" +
                "    position: relative; \n" +
                "    left:500px;\n" +
                "    width: 550px; \n" +
                "    background: #e0e0e0;\n" +
                "    padding: 10px;\n" +
                "   }\n" +
                "  </style>"+
                "<div id ='rightcol'><br>Temp in Voronezh ="+ WeatherMesenger.getInstance().getWeatherInVrn()+"&#176C"+"<br>" +
                "<br>Temp in Piter ="+ WeatherMesenger.getInstance().getWeatherInStPtr()+"&#176C"+"<br></div>"+
                "<form action='reports/get' method='post'>First date<input type='text' name='first_date'>Second date<input type='text' name='second_date'>" +
                "<input type='submit' value='get'></form>"+
                "</body>\n" +
                "\n" +
                "</html>");
    }
}
