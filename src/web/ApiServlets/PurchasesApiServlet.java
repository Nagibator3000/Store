package web.ApiServlets;

import com.google.gson.Gson;
import model.Customer;
import model.Product;
import model.Purchase;
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

public class PurchasesApiServlet extends HttpServlet {
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
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_OK);

        String outputString = "error";
        try {
            List<Purchase> allPurchases = WebLauncher.db.findAllPurchases();
            outputString = new Gson().toJson(allPurchases);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        resp.getWriter().println(outputString);
    }
}
