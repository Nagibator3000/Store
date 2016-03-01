import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class SqlLiteTest {


    private static Connection conn;

    public static void main(String[] args) throws Exception {
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:Db");
        readUserInput();
        conn.close();
    }

/*
    private static void simpleSelectFromeStore() throws ClassNotFoundException, SQLException, IOException {
        System.out.println("Starting...");
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection("jdbc:sqlite:Db");


        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery("select c.name 'customer_name',p.[PURCHASE_DATE], pr.name 'product_name',pr.[PRICE]*p.amount 'Summa', p.amount\n" +
                "from purchases p,customers c,products pr\n" +
                "where p.customer_id = c.id and p.product_id = pr.id");
        while (rs.next()) {
            System.out.println("name = " + rs.getString("customer_name") + " PRODUCT_NAME = " + rs.getString("product_name"));
        }
        rs.close();
        conn.close();
    }
*/


    private static String readString() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String s = in.readLine();
        return s;
    }

    private static void readUserInput() throws IOException, SQLException, ClassNotFoundException {
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
                    String y =readString();
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
                    System.out.println("3: delete purchase");
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


    private static void insertNewPurchase() throws IOException, SQLException {
        System.out.println("Enter product id");
        String prId = readString();
        System.out.println("Enter customer id");
        String cId = readString();
        System.out.println("Enter amount");
        String amount = readString();
        System.out.println("Enter year of purchase");
        String year = readString();
        System.out.println("Enter month of purchase");
        String month = readString();
        System.out.println("Enter day of purchase");
        String day = readString();
        String dataPurchaseDay = year+"-"+month+"-"+day;
        PreparedStatement prep = conn.prepareStatement("INSERT  INTO  Purchases (PRODUCT_ID,CUSTOMER_ID,AMOUNT,PURCHASE_DATE) VALUES (?,?,?,?)");
        prep.setInt(1, Integer.parseInt(prId));
        prep.setInt(2, Integer.parseInt(cId));
        prep.setInt(3, Integer.parseInt(amount));
        prep.setString(4, dataPurchaseDay);
        prep.addBatch();

        conn.setAutoCommit(false);
        prep.executeBatch();
        conn.setAutoCommit(true);
        System.out.println("Purchase has been added");
        System.out.println();

    }

    private static void printAllPurchases() throws SQLException {
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery("SELECT * FROM PURCHASES");
        System.out.println("ID | PRODUCT_ID | CUSTOMER_ID | AMOUNT | PURCHASE_DATE");
        while (rs.next()) {
            System.out.println(rs.getString(1) + ",         " + rs.getString(2) + ",         " + rs.getString(3)+ ",         " + rs.getString(4)+ ",        " + rs.getString(5));
        }
        rs.close();


    }

    private static void deleteCustomers() throws SQLException, IOException {
        System.out.println("Choose option remove");
        System.out.println("1: remove on ID");
        System.out.println("2: remove on name");
        String choice = readString();
        PreparedStatement prep;
        if (choice.equals("1")) {
            System.out.println("Enter ID");
            String id = readString();
            prep = conn.prepareStatement("DELETE FROM Customers WHERE ID=?");
            prep.setInt(1, Integer.parseInt(id));

            prep.addBatch();
            conn.setAutoCommit(false);
            prep.executeBatch();
            conn.setAutoCommit(true);
            System.out.println("Product has been deleted");
            System.out.println();
        } else {
            System.out.println("Enter Name");
            String name = readString();
            prep = conn.prepareStatement("DELETE FROM Customers WHERE NAME=?");
            prep.setString(1, name);

            prep.addBatch();
            conn.setAutoCommit(false);
            prep.executeBatch();
            conn.setAutoCommit(true);
            System.out.println("Customer has been deleted");
            System.out.println();
        }


    }

    private static void insertNewCustomers() throws IOException, SQLException {
        System.out.println("Enter customer name");
        String name = readString();
        System.out.println("Enter year");
        String year = readString();
        System.out.println("Enter month");
        String month = readString();
        System.out.println("Enter day");
        String day = readString();
        String dataBirthDay = year+"-"+month+"-"+day;
        PreparedStatement prep = conn.prepareStatement("INSERT INTO Customers  VALUES (null,?,?)");
        prep.setString(1, name);
        prep.setString(2, dataBirthDay);
        prep.addBatch();

        conn.setAutoCommit(false);
        prep.executeBatch();
        conn.setAutoCommit(true);
        System.out.println("Customer has been added");
        System.out.println();

    }

    private static void printAllCustomers() throws SQLException {
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery("SELECT * FROM CUSTOMERS");
        System.out.println("ID,NAME,BIRTH_DATE");
        while (rs.next()) {
            System.out.println(rs.getString(1) + ", " + rs.getString(2) + ", " + rs.getString(3));
        }
        rs.close();

    }

    private static void deleteProduct() throws IOException, SQLException {
        System.out.println("Choose option remove");
        System.out.println("1: remove on ID");
        System.out.println("2: remove on name");
        String choice = readString();
        PreparedStatement prep;
        if (choice.equals("1")) {
            System.out.println("Enter ID");
            String id = readString();
            prep = conn.prepareStatement("DELETE FROM Products WHERE ID=?");
            prep.setInt(1, Integer.parseInt(id));

            prep.addBatch();
            conn.setAutoCommit(false);
            prep.executeBatch();
            conn.setAutoCommit(true);
            System.out.println("Product has been deleted");
            System.out.println();
        } else {
            System.out.println("Enter Name");
            String name = readString();
            prep = conn.prepareStatement("DELETE FROM Products WHERE NAME=?");
            prep.setString(1, name);

            prep.addBatch();
            conn.setAutoCommit(false);
            prep.executeBatch();
            conn.setAutoCommit(true);
            System.out.println("Product has been deleted");
            System.out.println();
        }

    }

    private static void insertNewProduct() throws IOException, SQLException {
        System.out.println("Enter product name");
        String name = readString();
        System.out.println("Enter product price");
        String price = readString();
        PreparedStatement prep = conn.prepareStatement("INSERT INTO Products  VALUES (null,?,?)");
        prep.setString(1, name);
        prep.setDouble(2, Double.parseDouble(price));
        prep.addBatch();

        conn.setAutoCommit(false);
        prep.executeBatch();
        conn.setAutoCommit(true);
        System.out.println("Product has been added");
        System.out.println();
    }

    private static void printAllProducts() throws ClassNotFoundException, SQLException {
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery("SELECT * FROM PRODUCTS");
        System.out.println("ID,NAME,PRICE");
        while (rs.next()) {
            System.out.println(rs.getString(1) + ", " + rs.getString(2) + ", " + rs.getString(3));
        }
        rs.close();

    }


    private static void createTestDataBase() throws ClassNotFoundException, SQLException {
        System.out.println("Starting...");
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection("jdbc:sqlite:test.db");
        Statement stat = conn.createStatement();
        stat.executeUpdate("drop table if exists people;");
        stat.executeUpdate("create table people (name, occupation);");
        PreparedStatement prep = conn.prepareStatement("insert into people values (?, ?);");
        prep.setString(1, "Gandi");
        prep.setString(2, "politics");
        prep.addBatch();
        prep.setString(1, "Truning");
        prep.setString(2, "computers");
        prep.addBatch();
        prep.setString(1, "Jobs");
        prep.setString(2, "computers");
        prep.addBatch();

        conn.setAutoCommit(false);
        prep.executeBatch();
        conn.setAutoCommit(true);

        ResultSet rs = stat.executeQuery("select * from people order by occupation;");
        while (rs.next()) {
            System.out.println("name = " + rs.getString("name") + " occupation = " + rs.getString("occupation"));
        }
        rs.close();

    }
}
