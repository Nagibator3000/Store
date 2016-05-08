package web.ApiServlets;

import com.google.gson.Gson;
import model.Product;
import web.WebLauncher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ProductsApiServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getPathInfo();
        String lastSegment = url.substring(url.lastIndexOf("/") + 1, url.length());
        System.out.println("products Api Servlet doPost lastSegment: "+lastSegment);
        switch (lastSegment) {
            case "delete":
                try {
                    long product_id = Long.parseLong(req.getParameter("product_id"));
                    System.out.print(product_id);
                    WebLauncher.db.deleteProduct(product_id);
                    resp.getWriter().println("Ok");
                } catch (SQLException e) {
                    resp.getWriter().println(e.getLocalizedMessage());
                    e.printStackTrace();
                }
                break;
            case "add":
                String name = req.getParameter("product_name");
                String price = req.getParameter("product_price");
                Product product = new Product();
                product.name = name;
                product.price = Double.parseDouble(price);
                try {
                    WebLauncher.db.insertNewProduct(product);
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
            List<Product> allProducts = WebLauncher.db.findAllProducts();
            outputString = new Gson().toJson(allProducts);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        resp.getWriter().println(outputString);
    }
}
