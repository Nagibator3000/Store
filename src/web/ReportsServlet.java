package web;

import model.Purchase;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

public class ReportsServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletResponse.setContentType("text/html");
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);

       String outputString ="<!DOCTYPE html><html>\n" +
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
                "<div id ='rightcol'><br>Temp in Voronezh ="+ WeatherManager.getInstance().getWeatherInVrn()+"&#176C"+"<br>" +
                "<br>Temp in Piter ="+ WeatherManager.getInstance().getWeatherInStPtr()+"&#176C"+"<br></div>";
        try {
            outputString += "<table border= '1px'>";
            outputString += "<tr><td>Id</td><td>Product Id</td><td>Product Name</td><td><Customer Id/td><td>Customer Name</td><td>Amount</td><td>Date</td></tr>";
            List<Purchase> allPurchases = WebLauncher.db.findPurchases(WebLauncher.db.first,WebLauncher.db.second);
            for (Purchase purchase : allPurchases) {
                outputString += "<tr><td>" + purchase.id + "</td><td>" + purchase.productId + "</td><td>" + purchase.productName +
                        "</td><td>" + purchase.customerId + "</td><td>" + purchase.customerName + "</td><td>" + purchase.amount + "</td><td>" + new SimpleDateFormat("yyyy-MM-dd").format(purchase.purchaseDate) + "</td></tr>";


            }
            outputString += "</table>";
        } catch (SQLException e) {
            e.printStackTrace();
        }
          outputString+="</body>\n" +
                "\n" +
                "</html>";
        httpServletResponse.getWriter().println(outputString);
    }
}
