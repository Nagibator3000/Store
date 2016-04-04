package web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HomeServlet extends HttpServlet {
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
                "    position: relative; /* Относительное позиционирование */\n" +
                "    left:500px; /* Смещаем слой вправо на ширину левого слоя */\n" +
                "    width: 550px; /* Ширина слоя */\n" +
                "    background: #e0e0e0; /* Цвет фона */\n" +
                "    padding: 10px; /* Поля вокруг текста */\n" +
                "   }\n" +
                "  </style>"+
                "<div id ='rightcol'><br>Temp in Voronezh ="+ WeatherMesenger.getInstance().getWeatherInVrn()+"&#176C"+"<br>" +
                "<br>Temp in Piter ="+ WeatherMesenger.getInstance().getWeatherInStPtr()+"&#176C"+"<br></div>"+
                "<a href = \"\\products\">Go to products</a><br>"+
                "<a href = \"\\customers\">Go to customers</a><br>"+
                "<a href = \"\\purchases\">Go to purchases</a>"+
                "</body>\n" +
                "\n" +
                "</html>");
    }
}
