package web.ApiServlets;

import com.google.gson.Gson;
import model.Customer;
import model.Product;
import web.WebLauncher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class CustomersApiServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getPathInfo();
        String lastSegment = url.substring(url.lastIndexOf("/") + 1, url.length());
        System.out.println("customers Api Servlet doPost lastSegment: " + lastSegment);
        switch (lastSegment) {
            case "delete":
                try {
                    long customer_id = Long.parseLong(req.getParameter("customer_id"));
                    System.out.print(customer_id);
                    WebLauncher.db.deleteCustomer(customer_id);
                    resp.getWriter().println("Ok");
                } catch (SQLException e) {
                    resp.getWriter().println(e.getLocalizedMessage());
                    e.printStackTrace();
                }
                break;
           case "add":
                String name = req.getParameter("customer_name");
                String dateBirthDay = req.getParameter("customer_dateBirthDay");
                Customer customer = new Customer();
                customer.name = name;
               SimpleDateFormat format = new SimpleDateFormat ("yyyy-MM-dd");
               try {
                   customer.dateBirthDay = format.parse(dateBirthDay);
               } catch (ParseException e) {
                   e.printStackTrace();
               }
               try {
                    WebLauncher.db.insertCustomer(customer);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_OK);

        String outputString = "error";
        try {
            List<Customer> allCustomers = WebLauncher.db.findAllCustomers();
            outputString = new Gson().toJson(allCustomers);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        resp.getWriter().println(outputString);
    }
}
