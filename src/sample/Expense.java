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
    Expense(String name, double amount, String category, Date date, String note) {
        this.name = name;
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.note = note;
    }

    //Constructor with no note
    Expense(String name, double amount, String category, Date date) {
        this.name = name;
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.note = "";
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

    @Override
    public String toString() {
        return "(" + this.getName() + ", $" + this.getAmount() + ", " + this.getCategory() + ", " + this.getDate().toString() + ", " + this.getNote() + ")";
    }
}