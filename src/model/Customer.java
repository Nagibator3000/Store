package model;

import java.util.Date;

public class Customer {
    public long id;
    public String name;
    public Date dateBirthDay;

    public Customer(long id, String name, long date) {
        this.id = id;
        this.name = name;
        if (date > 0) {
            dateBirthDay = new Date(date);
        }
    }

    public Customer() {

    }
}




