package sample;

import java.util.*;
import java.lang.*;

class Expense {

    private String name;
    private double amount;
    private String category;
    private Date date;
    private String note;

    //Constructor
    Expense(String n, double a, String c, Date d, String t) {
        this.name = n;
        this.amount = a;
        this.category = c;
        this.date = d;
        this.note = t;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }
}
