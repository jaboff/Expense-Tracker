package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
        System.out.println("Table is updating!!!!");
        //ObservableList<Expense> expenses = new ObservableList<Expense>();
        //view_tableView.setItems(new ObservableList<Expense>());

        // Create column UserName (Data type of String).
        TableColumn<Expense, String> nameCol //
                = new TableColumn<Expense, String>("Name");

        // Create column Email (Data type of String).
        TableColumn<Expense, String> categoryCol//
                = new TableColumn<Expense, String>("Category");

        // Create column FullName (Data type of String).
        TableColumn<Expense, String> dateCol//
                = new TableColumn<Expense, String>("Date");

        // Create 2 sub column for FullName.
        TableColumn<Expense, String> startDateCol //
                = new TableColumn<Expense, String>("Start");

        TableColumn<Expense, String> endDateCol //
                = new TableColumn<Expense, String>("End");

        // Add sub columns to the FullName
        dateCol.getColumns().addAll(startDateCol, endDateCol);

        // Active Column
        TableColumn<Expense, Boolean> priceCol
                = new TableColumn<Expense, Boolean>("Price");

        view_tableView.getColumns().addAll(nameCol, categoryCol, dateCol, priceCol);
    }

    /*
    public ObservableList<Expense>  getExpenses(boolean getFiltered)
    {
        ObservableList<Expense> expenses = new ObservableArray<sample.Expense>();
        if(getFiltered)
            expenses.addAll(expenseList.getFilteredList());
        else
            expenses.addAll(expenseList.getList());
        return expenses;
    }
    //*/



    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("Hello");
    }
}
