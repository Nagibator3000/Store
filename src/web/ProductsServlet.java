package web;

import model.Product;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ProductsServlet extends HttpServlet {
    public static final String SQL_ERROR = "sqlerror";
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String url = req.getPathInfo();
        String lastSegmet = url.substring(url.lastIndexOf("/") + 1, url.length());
        switch (lastSegmet) {
            case "add":
                String name = req.getParameter("product_name");
                String price = req.getParameter("product_price");
                System.out.printf(name + " " + price);
                Product product = new Product();
                product.name = name;
                product.price = Double.parseDouble(price);
                try {
                    WebLauncher.db.insertNewProduct(product);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;

            case "delete":
                try {
                    long product_id = Long.parseLong(req.getParameter("product_id"));
                    System.out.print(product_id);
                    WebLauncher.db.deleteProduct(product_id);
                } catch (SQLException e) {
                    resp.sendRedirect("./sqlerror");
                    e.printStackTrace();
                }
        }


        resp.sendRedirect(".");
    }

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
                "  <style type=\"text/css\"> \n" +
                "   #rightcol {\n" +
                "    position: relative; /* Относительное позиционирование */\n" +
                "    left: 200px; /* Смещаем слой вправо на ширину левого слоя */\n" +
                "    width: 550px; /* Ширина слоя */\n" +
                "    background: #e0e0e0; /* Цвет фона */\n" +
                "    padding: 10px; /* Поля вокруг текста */\n" +
                "   }\n" +
                "  </style>"+
                "<div id ='rightcol'><br>Temp in Voronezh ="+ WeatherMesenger.getInstance().getWeatherInVrn()+"&#176C"+"<br>"+
                "<br>Temp in Piter ="+ WeatherMesenger.getInstance().getWeatherInStPtr()+"&#176C"+"<br></div>"+
                "<a href = \"\\products\">Go to products</a><br>" +
                "<a href = \"\\customers\">Go to customers</a><br>" +
                "<a href = \"\\purchases\">Go to purchases</a><br>";
        String url = request.getPathInfo();
        if (url != null) {
            String lastSegment = url.substring(url.lastIndexOf("/") + 1, url.length());
            if (lastSegment.equals(SQL_ERROR)) {
                outputString += "operation failed,cause of sqlException";
            }
        }


        try {
            outputString += "<table border= '1px'>";
            outputString+="<tr><td>Id</td><td>Name</td><td>Price</td></tr>" ;
            List<Product> allProducts = WebLauncher.db.findAllProducts();
            for (Product product : allProducts) {

                outputString +=
                        "<tr><td>" + product.id + "</td><td>" + product.name + "</td><td>" + product.price + "</td><td>" +
                                "<form action = 'products/delete' method = 'post'><input type='submit' value ='delete'/>" +
                                "<input type = 'hidden' name='product_id' value='" + product.id + "'/></form></td></tr>";


            }
            outputString += "</table>";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        outputString += "<form action='products/add' method='post'>Name<input type='text' name='product_name'>" +
                "Price<input type='text' name='product_price'><input type='submit' value='add'></form>";
        outputString += end;

        httpServletResponse.getWriter().println(outputString);
    }
}
