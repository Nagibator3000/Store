package model;

import java.util.Date;

public class Customer {
    public Long id;
    public String name;
    public Date dateBirthDay;
    public Customer(long id,String name, long date) {
        this.id = id;
        this.name = name;
        dateBirthDay = new Date(date);
    }

    public Customer() {

    }
}




