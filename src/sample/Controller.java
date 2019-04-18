package sample;
import java.net.URL;
import java.net.URL.*;
import javafx.beans.Observable;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.util.*;
import java.util.concurrent.TimeUnit;
import javafx.animation.*;
import java.time.*;
import javafx.scene.control.Label;
import javafx.event.*;
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
        if(!isThereEmptyFields())
        {
            Date newDate = new Date(add_dateInput.getValue().toEpochDay());
            if(!add_isRecurring.isSelected())
            {
                Expense newExpense = new Expense(add_nameInput.getText(), Double.parseDouble(add_costInput.getText()), add_categoryInput.getText(),
                        newDate, add_noteInput.getText());

                list.addExpense(newExpense);
                possibleWords.add(add_categoryInput.getText());

                add_successfulAdd.setVisible(true);
                timer.schedule(displaySuccessful, 3000);
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

    public void myMethod(final String myString) {

        ScheduledExecutorService scheduledExecutorService =
                Executors.newScheduledThreadPool(5);

        myExecutor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println(myString);
            }
        }, 0, 10000, TimeUnit.MILLISECONDS);
    }

    TimerTask displaySuccessful = new TimerTask()
    {
        public void run()
        {
            add_successfulAdd.setVisible(false);
        }
    };

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

    @FXML   private BarChart<?,?> expenseChart;
    @FXML   private CategoryAxis x;
    @FXML   private NumberAxis y;
    @FXML   private Text total;

    @FXML public void initialize(){
        setUpChartByCategory();
    }

    private void setUpChartByCategory(){
        XYChart.Series set1 = new XYChart.Series();
        Expense e1 = new Expense("Apple", 1.99, "Food", new Date(), "Golden Delicious");
        Expense e2 = new Expense("Banana", 2.99, "Food", new Date(), "Fruit Salad");
        Expense e3 = new Expense("Watermelon", 4.99, "Food", new Date(), "Yummy Yummy");
        Expense e4 = new Expense("Watermelon", 4.99, "Inorganic", new Date(), "Yummy Yummy");
        expenseList.addExpense(e1);
        expenseList.addExpense(e2);
        expenseList.addExpense(e3);
        expenseList.addExpense(e4);
        ArrayList<String> categoriesThatHaveAlreadyBeenComputed = new ArrayList<>();
        double sumOfSums = 0;
        for(int o = 0; o < expenseList.getSize(); o++)
        {
            if (!(categoriesThatHaveAlreadyBeenComputed.contains(expenseList.getExpense(o).getCategory()))){
                expenseList.filterByCategory(expenseList.getExpense(o).getCategory());
                double sum = 0;
                for (int m = 0; m < expenseList.getFilteredList().size(); m++){
                    sum += expenseList.getFilteredList().get(m).getAmount();
                }
                sumOfSums += sum;
                set1.getData().add(new XYChart.Data(expenseList.getExpense(o).getCategory(), sum));
                categoriesThatHaveAlreadyBeenComputed.add(expenseList.getExpense(o).getCategory());
            }

        }
        total.setText("$" + sumOfSums);
        expenseChart.getData().addAll(set1);
    }
}
