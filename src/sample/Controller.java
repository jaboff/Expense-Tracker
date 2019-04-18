package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.util.*;
import java.util.concurrent.TimeUnit;

import javafx.scene.control.Label;
import javafx.scene.control.*;
import org.controlsfx.control.textfield.TextFields;

public class Controller
{
    ExpenseList list = new ExpenseList();

    @FXML
    private Button saveButton;
    @FXML
    private Button clearButton;

    @FXML
    private TextField add_nameInput;
    @FXML
    private TextField add_categoryInput;
    @FXML
    private TextField add_costInput;
    @FXML
    private DatePicker add_dateInput;
    @FXML
    private TextField add_frequencyInput;
    @FXML
    private TextField add_noteInput;
    @FXML
    private DatePicker add_stopDateInput;
    @FXML
    private CheckBox add_isRecurring;

    ArrayList<String> possibleWords = new ArrayList<String>();

    @FXML
    private void saveButtonAction(ActionEvent event)
    {
        if(!isThereEmptyFields())
        {
            Date newDate = new Date(add_dateInput.getValue().toEpochDay());
            if(!add_isRecurring.isSelected())
            {
                Expense newExpense = new Expense(add_nameInput.getText(), Double.parseDouble(add_costInput.getText()), add_categoryInput.getText(),
                        newDate, add_noteInput.getText());
            }
            else
            {
                Expense newExpense = new Expense(add_nameInput.getText(), Double.parseDouble(add_costInput.getText()), add_categoryInput.getText(),
                        newDate, add_noteInput.getText(), TimeUnit.DAYS.toMillis(Long.parseLong(add_frequencyInput.getText())));
                list.addExpense(newExpense);
                possibleWords.add(add_categoryInput.getText());

                System.out.println("" + list.toString());
            }
        }
    }

    private boolean isThereEmptyFields()
    {
        if(add_costInput.getText() == null || add_costInput.getText().trim().isEmpty())
        {
            Alert emptyCostAlert = new Alert(Alert.AlertType.WARNING);
            emptyCostAlert.setContentText("Please enter cost");
            emptyCostAlert.show();
            return true;
        }

        if(add_nameInput.getText() == null || add_costInput.getText().trim().isEmpty())
        {
            Alert emptyCostAlert = new Alert(Alert.AlertType.WARNING);
            emptyCostAlert.setContentText("Please enter name for item");
            emptyCostAlert.show();
            return true;
        }
        if(add_dateInput.getValue() == null)
        {
            Alert emptyCostAlert = new Alert(Alert.AlertType.WARNING);
            emptyCostAlert.setContentText("Please enter date");
            emptyCostAlert.show();
            return true;
        }
        if(add_categoryInput.getText() == null || add_costInput.getText().trim().isEmpty())
        {
            Alert emptyCostAlert = new Alert(Alert.AlertType.WARNING);
            emptyCostAlert.setContentText("Please enter category");
            emptyCostAlert.show();
            return true;
        }
        if(add_frequencyInput.getText() == null || add_costInput.getText().trim().isEmpty())
        {
            Alert emptyCostAlert = new Alert(Alert.AlertType.WARNING);
            emptyCostAlert.setContentText("Please enter frequency");
            emptyCostAlert.show();
            return true;
        }
        if(add_noteInput.getText() == null || add_noteInput.getText().trim().isEmpty())
        {
            add_noteInput.setText("");
        }

        return false;
    }

    @FXML
    private void clearButtonAction(ActionEvent event)
    {
        add_nameInput.clear();
        add_categoryInput.clear();
        add_costInput.clear();
        add_dateInput.getEditor().clear();
        add_stopDateInput.getEditor().clear();
        add_frequencyInput.clear();
    }

    public void categoryAutoFill()
    {
        TextFields.bindAutoCompletion(add_categoryInput, possibleWords);
    }

    public void testSort()
    {
        /*
        Date today = new Date();
        Expense exp1 = new Expense("Taco1", 5.46, "Food", today,"");
        Expense exp2 = new Expense("Cheese", 34.46, "Food", today,"");
        Expense exp3 = new Expense("Apple", 67.46, "Food", today,"");
        Expense exp4 = new Expense("Shoe", 12.46, "Clothes", today,"");
        Expense exp5 = new Expense("Pie", 3.46, "Food", today,"");
        list.addExpense(exp1); list.addExpense(exp2); list.addExpense(exp3); list.addExpense(exp4); list.addExpense(exp5);
        */
        list.sortByAmount();


    }

    public void viewPaneController()
    {
        
    }
}
