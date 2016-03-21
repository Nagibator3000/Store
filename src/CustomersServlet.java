import model.Customer;
import model.Product;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CustomersServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String url = req.getPathInfo();
        String lastSegmet = url.substring(url.lastIndexOf("/") + 1, url.length());
        switch (lastSegmet) {
            case "add":
                String name = req.getParameter("customer_name");
                String dateBirthDay = req.getParameter("customer_dateBirthDay");
                System.out.printf(name + "||| " + dateBirthDay);
                Customer customer = new Customer();
                customer.name = name;
                try {
                    customer.dateBirthDay = new SimpleDateFormat("yyyy-MM-dd").parse(dateBirthDay);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                try {
                    WebLauncher.db.insertCustomer(customer);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;

            case "delete":
                try {
                  long customer_id = Long.parseLong(req.getParameter("customer_id"));
                    System.out.print(customer_id);
                    WebLauncher.db.deleteProduct(customer_id);
                } catch (SQLException e) {
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
                "<a href = \"\\products\">Go to products</a><br>" +
                "<a href = \"\\customers\">Go to customers</a><br>" +
                "<a href = \"\\purchases\">Go to purchases</a><br>";


        try {
            outputString += "<table border= '1px'>";
            List<Customer> allCustomers = WebLauncher.db.findAllCustomers();
            for (Customer customer : allCustomers) {
                outputString += "<tr><td>" + customer.id + "</td><td>" + customer.name + "</td><td>" + customer.dateBirthDay +
                        "</td><td>"+"<form action='customers/delete' method ='post'><input type='submit' value ='delete'/>"+
               "<input type = 'hidden' name='customer_id' value='" + customer.id + "'/></form></td></tr>";

            }
            outputString += "</table>";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        outputString += "<form action='customers/add' method='post'>Name<input type='text' name='customer_name'>" +
                "Date<input type='text' name='customer_dateBirthDay'><input type='submit' value='add'></form>";
        outputString += end;

        httpServletResponse.getWriter().println(outputString);
    }
}