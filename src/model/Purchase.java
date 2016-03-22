package model;

import java.util.Date;

public class Purchase {
    public long id;
    public long productId;
    public long customerId;
    public double amount;
    public Date purchaseDate;
    public String customerName;
    public String productName;



    public Purchase(long id, long productId, long customerId, double amount, long date) {

        this.id = id;
        this.productId = productId;
        this.customerId = customerId;
        this.amount = amount;
        purchaseDate = new Date(date);
    }

    public Purchase() {

    }
}
