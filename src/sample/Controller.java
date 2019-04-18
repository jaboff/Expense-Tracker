package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.util.*;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javafx.animation.*;
import java.time.*;
import javafx.scene.control.Label;
import javafx.event.*;
import static java.util.concurrent.TimeUnit.SECONDS;
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
    @FXML
    private Label add_successfulAdd;
    @FXML
    private Label add_unSuccessfulAdd;

    ArrayList<String> possibleWords = new ArrayList<String>();

    Timer timer = new Timer();

    @FXML
    private void saveButtonAction(ActionEvent event)
    {
        if(!isThereEmptyFields() && isThereValidFields())
        {
            Date newDate = new Date(add_dateInput.getValue().toEpochDay());
            if(!add_isRecurring.isSelected())
            {
                Expense newExpense = new Expense(add_nameInput.getText(), Double.parseDouble(add_costInput.getText()), add_categoryInput.getText(),
                        newDate, add_noteInput.getText());

                list.addExpense(newExpense);
                possibleWords.add(add_categoryInput.getText());

                add_successfulAdd.setVisible(true);
                System.out.println("" + list.toString());
            }
            else
            {
                Expense newExpense = new Expense(add_nameInput.getText(), Double.parseDouble(add_costInput.getText()), add_categoryInput.getText(),
                        newDate, add_noteInput.getText(), TimeUnit.DAYS.toMillis(Long.parseLong(add_frequencyInput.getText())));

                list.addExpense(newExpense);
                possibleWords.add(add_categoryInput.getText());

                add_unSuccessfulAdd.setVisible(false);
                add_successfulAdd.setVisible(true);
                //timer.schedule(displaySuccessful, 5001);
                System.out.println("" + list.toString());
            }
        }
        else
        {
            add_successfulAdd.setVisible(false);
            add_unSuccessfulAdd.setVisible(true);
        }
    }

    private boolean isThereValidFields()
    {
        double returnVal = 0;
        try {
            Double.parseDouble(add_costInput.getText());
        }
        catch(Exception e) {
            //  Block of code to handle errors
            Alert emptyCostAlert = new Alert(Alert.AlertType.WARNING);
            emptyCostAlert.setContentText("Please enter valid numbers for cost and frequency");
            emptyCostAlert.show();
            return false;
        }
        try {
            Double.parseDouble(add_frequencyInput.getText());
        }
        catch(Exception e) {
            //  Block of code to handle errors
            Alert emptyCostAlert = new Alert(Alert.AlertType.WARNING);
            emptyCostAlert.setContentText("Please enter valid numbers for cost and frequency");
            emptyCostAlert.show();
            return false;
        }
        return true;
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
        if(add_isRecurring.isSelected() && add_frequencyInput.getText() == null || add_costInput.getText().trim().isEmpty())
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
    private void toggleFrequency()
    {
        if(add_isRecurring.isSelected())
        {
            add_frequencyInput.setVisible(true);
        }
        else
        {
            add_frequencyInput.setVisible(false);
        }
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

    private void updateScheduledExpenses(){
        Expense e;
        list.filterByRecurring();
        for(int i = 0; i < list.getFilteredList().size(); i++){
            if(list.getFilteredList().get(i).needsUpdate()){
                e = new Expense(list.getFilteredList().get(i).getName(),list.getFilteredList().get(i).getAmount(),
                        list.getFilteredList().get(i).getCategory(),list.getFilteredList().get(i).getNextOccurrence(),
                        list.getFilteredList().get(i).getNote(),list.getFilteredList().get(i).getFrequency());
                //Need function that puts lets user confirm stuff
                list.addToBothLists(e);
            }
        }
    }

    private final ScheduledExecutorService scheduler = new ScheduledThreadPoolExecutor(1);

    public void startUpdateFiveSeconds(){

        final Runnable updater = new Runnable(){
            public void run(){
                //Stuff we want to happen every 5 seconds goes here
                updateScheduledExpenses();
            }};
            final ScheduledFuture<?> updaterHandle = scheduler.scheduleAtFixedRate(updater, 5, 5, SECONDS);
        }

    public void viewPaneController()
    {
        
    }
}
