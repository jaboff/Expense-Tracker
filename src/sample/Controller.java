package sample;

import com.sun.java.swing.plaf.windows.TMSchema;
import javafx.beans.Observable;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.*;

public class Controller
{
    ExpenseList expenseList = new ExpenseList();
    //View Pane Stuff
    @FXML    private TableView<Expense> view_tableView;
    @FXML    private TableColumn<Expense, String> view_nameCol;
    @FXML    private TableColumn<Expense, Date> view_dateCol;
    @FXML    private TableColumn<Expense, String> view_categoryCol;
    @FXML    private TableColumn<Expense, Double> view_amountCol;
    @FXML    private TableColumn<Expense, String> view_noteCol;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //set up the columns in the table
        view_nameCol.setCellValueFactory(new PropertyValueFactory<Expense, String>("name"));
        view_amountCol.setCellValueFactory(new PropertyValueFactory<Expense, Double>("amount"));
        view_categoryCol.setCellValueFactory(new PropertyValueFactory<Expense, String>("category"));
        view_dateCol.setCellFactory(new PropertyValueFactory<Expense, Date>("date"));
        view_noteCol.setCellValueFactory(new PropertyValueFactory<Expense, String>("note"));

        //load data
        view_tableView.setItems(getExpenses());
    }

    public ObservableList<Expense>  getExpenses()
    {
        ObservableList<Expense> expenses = new ObservableArray<sample.Expense>();
        expenses.addAll(expenseList.getFilteredList());
        return expenses;
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("Hello");
    }
}
