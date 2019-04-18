package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;

import java.time.LocalDate;
import java.util.ResourceBundle;
import java.net.URL;
import java.sql.Date;

public class Controller implements Initializable
{
    ExpenseList expenseList = new ExpenseList();

    @FXML
    private TableView<Expense> view_tableView;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1)
    {
        updateTable();
    }

    @FXML
    public void updateTable()
    {
        // Create columns for each expense entry
        TableColumn<Expense, String> nameCol = new TableColumn<Expense, String>("Name");
        TableColumn<Expense, Double> amountCol = new TableColumn<Expense, Double>("Amount");
        TableColumn<Expense, String> categoryCol = new TableColumn<Expense, String>("Category");
        TableColumn<Expense, String> dateCol = new TableColumn<Expense, String>("Date");

        // Set the factory values for each entry
        // These will ensure that the each field in an expense will map to the correct column
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));

        // Display the column on the table
        view_tableView.getColumns().addAll(nameCol, amountCol, categoryCol, dateCol);


        // Create a sample expense list and populate it with data
        //* Then add that sample data to the table for testing
        ExpenseList exList = new ExpenseList();
        for (int i = 0; i < 10; i++)
        {
            Expense randExpense = new Expense("Item"+i, i, "Grocery", Date.valueOf(LocalDate.now()), "A note");
            exList.addExpense(randExpense);
        }
        view_tableView.setItems(ExpenseList.getList());
        //*/
    }


    @FXML
    private void handleButtonAction(ActionEvent event)
    {
        System.out.println("Hello");
    }
}
