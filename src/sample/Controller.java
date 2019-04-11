package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.util.*;
import javafx.scene.control.Label;

public class Controller
{
    ExpenseList list = new ExpenseList();

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("Hello");
    }

    public void testSort()
    {
        Date today = new Date();
        Expense exp1 = new Expense("Taco1", 5.46, "Food", today,"");
        Expense exp2 = new Expense("Cheese", 34.46, "Food", today,"");
        Expense exp3 = new Expense("Apple", 67.46, "Food", today,"");
        Expense exp4 = new Expense("Shoe", 12.46, "Clothes", today,"");
        Expense exp5 = new Expense("Pie", 3.46, "Food", today,"");
        list.addExpense(exp1); list.addExpense(exp2); list.addExpense(exp3); list.addExpense(exp4); list.addExpense(exp5);

        list.sortByAmount();

        list.printList();
    }

    public void viewPaneController()
    {
        
    }
}
