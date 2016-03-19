import model.Customer;
import model.Product;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CustomersServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


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
                "<a href = \"\\products\">Go to products</a><br>" +
                "<a href = \"\\customers\">Go to customers</a><br>" +
                "<a href = \"\\purchases\">Go to purchases</a><br>";


        try {
            outputString += "<table border= '1px'>";
            List<Customer> allCustomers = WebLauncher.db.findAllCustomers();
            for (Customer customer : allCustomers) {

                outputString +=
                        "<tr><td>" + customer.id + "</td><td>" + customer.name + "</td><td>" + customer.dateBirthDay + "</td><td>";



            }
            outputString += "</table>";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /*outputString += "<form action='customers/add' method='post'>Name<input type='text' name='customer_name'>" +
                "Price<input type='text' name='customer_name'><input type='submit' value='add'></form>";*/
        outputString += end;

        httpServletResponse.getWriter().println(outputString);
    }
}