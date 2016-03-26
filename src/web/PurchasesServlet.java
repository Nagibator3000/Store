package web;

import model.Customer;
import model.Product;
import model.Purchase;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class PurchasesServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String url = req.getPathInfo();
        String lastSegmet = url.substring(url.lastIndexOf("/") + 1, url.length());
        switch (lastSegmet) {
            case "add":
                String product_name = req.getParameter("product_name");
                String customer_name = req.getParameter("customer_name");
                String purchases_amount = req.getParameter("purchases_amount");
                String purchases_purchaseDate = req.getParameter("purchases_purchaseDate");
                System.out.printf(customer_name + "||| " +product_name + "|||" + "");
                Purchase purchase = new Purchase();
                purchase.productId = Long.parseLong(product_name);
                purchase.customerId = Long.parseLong(customer_name);
                purchase.amount = Double.parseDouble(purchases_amount);
                try {
                    purchase.purchaseDate = new SimpleDateFormat("yyyy-MM-dd").parse(purchases_purchaseDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                try {
                    WebLauncher.db.insert(purchase);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;

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
                "<h1>Purchases</h1>\n" +
                "<a href = \"\\products\">Go to products</a><br>" +
                "<a href = \"\\customers\">Go to customers</a><br>" +
                "<a href = \"\\purchases\">Go to purchases</a><br>";


        try {
            outputString += "<table border= '1px'>";
            outputString += "<tr><td>Id</td><td>Product Id</td><td>Product Name</td><td><Customer Id/td><td>Customer Name</td><td>Amount</td><td>Date</td></tr>";
            List<Purchase> allPurchases = WebLauncher.db.findAllPurchases();
            for (Purchase purchase : allPurchases) {
                outputString += "<tr><td>" + purchase.id + "</td><td>" + purchase.productId + "</td><td>" + purchase.productName +
                        "</td><td>" + purchase.customerId + "</td><td>" + purchase.customerName + "</td><td>" + purchase.amount + "</td><td>" + new SimpleDateFormat("yyyy-MM-dd").format(purchase.purchaseDate) + "</td></tr>";


            }
            outputString += "</table>";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        outputString += "<form action='purchases/add' method='post'>ProductName<select name = 'product_name'><option></option>";
        try {
            List<Product> allProducts = WebLauncher.db.findAllProducts();
            for (Product product : allProducts) {
                outputString += "<option value = '"+product.id+"'>" + product.name + "</option>";
            }
        } catch (SQLException e) {


        }
        outputString += "</select>" +
                "CustomerName<select name = 'customer_name'><option></option>";
        try {
            List<Customer> allCustomers = WebLauncher.db.findAllCustomers();
            for (Customer customer : allCustomers) {
                outputString += "<option value='"+customer.id+"'>"+customer.name+"("+customer.id+")"+"</option>";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
       outputString+= "</select>Amount<input type='text' name='purchases_amount'>" +
                "Date<input type='text' name='purchases_purchaseDate'>" +
                "<input type='submit' value='add'></form>";

        outputString += end;

        httpServletResponse.getWriter().println(outputString);
    }
}
