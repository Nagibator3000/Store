import model.Customer;
import model.Product;
import model.Purchase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class ConsoleLauncher {

    private static Db db;

    public static void main(String[] args) throws Exception {
        db = new Db();
        readUserInput();
    }

    private static String readString() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String s = in.readLine();
        return s;
    }

    private static void readUserInput() throws IOException, SQLException, ClassNotFoundException, ParseException {
        while (true) {
            System.out.println();
            System.out.println("Choose table");
            System.out.println("1: Products ");
            System.out.println("2: Customers");
            System.out.println("3: Purchases");
            System.out.println("exit: close application");

            String s = readString();
            if (s.equals("exit"))
                break;
            switch (s) {
                case "1":
                    System.out.println("Enter operation");
                    System.out.println("1: print all products");
                    System.out.println("2: insert new product");
                    System.out.println("3: delete product product");
                    String x = readString();
                    switch (x) {
                        case "1":
                            printAllProducts();
                            break;
                        case "2":
                            insertNewProduct();
                            break;
                        case "3":
                            deleteProduct();
                    }
                    break;
                case "2":
                    System.out.println("Enter operation");
                    System.out.println("1: print all customers");
                    System.out.println("2: insert new customers");
                    System.out.println("3: delete customers");
                    String y = readString();
                    switch (y) {
                        case "1":
                            printAllCustomers();
                            break;
                        case "2":
                            insertNewCustomers();
                            break;
                        case "3":
                            deleteCustomers();
                            break;
                    }

                    break;
                case "3":
                    System.out.println("Enter operation");
                    System.out.println("1: print all purchases");
                    System.out.println("2: insert new purchase");
                    String z = readString();
                    switch (z) {
                        case "1":
                            printAllPurchases();
                            break;
                        case "2":
                            insertNewPurchase();
                            break;

                    }
                    break;
            }


        }
    }


    private static void insertNewPurchase() throws IOException, SQLException, ParseException {
        Purchase purchase = new Purchase();
        System.out.println("Enter product id");
        purchase.productId = Long.parseLong(readString());
        System.out.println("Enter customer id");
        purchase.customerId = Long.parseLong(readString());
        System.out.println("Enter amount");
        purchase.amount = Double.parseDouble(readString());
        System.out.println("Enter year of purchase");
        String year = readString();
        System.out.println("Enter month of purchase");
        String month = readString();
        System.out.println("Enter day of purchase");
        String day = readString();
        purchase.purchaseDate = new SimpleDateFormat("yyyy-MM-dd").parse(year + "-" + month + "-" + day);
        db.insert(purchase);
        System.out.println("Purchase has been added");
        System.out.println();
    }

    private static void printAllPurchases() throws SQLException {
        System.out.println("ID | PRODUCT_ID | CUSTOMER_ID | AMOUNT | PURCHASE_DATE");
        List<Purchase> purchaseList = db.findAllPurchases();
        for (Purchase purchase : purchaseList) {
            System.out.println(purchase.id + ",         " + purchase.productId + ",         " + purchase.customerId + ",         " + purchase.amount + ",        " + purchase.purchaseDate);
        }
    }

    private static void deleteCustomers() throws SQLException, IOException {
        System.out.println("Choose option remove");
        System.out.println("1: remove on ID");
        System.out.println("2: remove on name");
        String choice = readString();
        if (choice.equals("1")) {
            System.out.println("Enter ID");
            String id = readString();
            db.deleteCustomer(Long.parseLong(id));
            System.out.println("Customer has been deleted");
            System.out.println();
        } else {
            System.out.println("Enter Name");
            String name = readString();
            db.deleteCustomer(name);

            System.out.println("Customer has been deleted");
            System.out.println();
        }


    }

    private static void insertNewCustomers() throws IOException, SQLException, ParseException {
        Customer customer = new Customer();
        System.out.println("Enter customer name");
        customer.name = readString();
        System.out.println("Enter year");
        String year = readString();
        System.out.println("Enter month");
        String month = readString();
        System.out.println("Enter day");
        String day = readString();

        customer.dateBirthDay = new SimpleDateFormat("yyyy-MM-dd").parse(year + "-" + month + "-" + day);
        db.insertCustomer(customer);
        System.out.println("Customer has been added");
        System.out.println();

    }

    private static void printAllCustomers() throws SQLException {
        System.out.println("ID,NAME,BIRTH_DATE");
        List<Customer> customerList =db.findAllCustomers();
        for (Customer customer : customerList) {
            System.out.println(customer.id+" , "+customer.name+" , "+customer.dateBirthDay);
        }
    }

    private static void deleteProduct() throws IOException, SQLException {
        System.out.println("Choose option remove");
        System.out.println("1: remove on ID");
        System.out.println("2: remove on name");
        String choice = readString();

        if (choice.equals("1")) {
            System.out.println("Enter ID");
            String id = readString();
            db.deleteProduct(Long.valueOf(id));
            System.out.println("Product has been deleted");
            System.out.println();
        } else {
            System.out.println("Enter Name");
            String name = readString();
            db.deleteProduct(name);
            System.out.println("Product has been deleted");
            System.out.println();
        }
    }

    private static void insertNewProduct() throws IOException, SQLException {
        Product product = new Product();
        System.out.println("Enter product name");
        product.name = readString();
        System.out.println("Enter product price");
        product.price = Double.parseDouble(readString());
        db.insertNewProduct(product);
        System.out.println("Product has been added");
        System.out.println();
    }

    public static void printAllProducts() throws ClassNotFoundException, SQLException {
        System.out.println("ID,NAME,PRICE");
        List<Product> productslist  = db.findAllProducts() ;
        for (Product product : productslist) {
            System.out.println(product.id+" , "+product.name+" , "+product.price);
        }
    }
}
