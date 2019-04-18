package sample;
import java.net.URL;
import java.net.URL.*;
import javafx.beans.Observable;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.time.LocalDate;
import java.util.*;

public class Controller
{
    private ExpenseList expenseList = new ExpenseList();
    //View Pane Stuff
    @FXML    private TableView<Expense> view_tableView;
    @FXML    private TableColumn<Expense, String> view_nameCol;
    @FXML    private TableColumn<Expense, Date> view_dateCol;
    @FXML    private TableColumn<Expense, String> view_categoryCol;
    @FXML    private TableColumn<Expense, Double> view_amountCol;
    @FXML    private TableColumn<Expense, String> view_noteCol;



    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("Hello");
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

    private final ScheduledExecutorService scheduler = new ScheduledThreadPoolExecutor(1);

    public void startUpdateFiveSeconds(){
        scheduleTester = 0;
        final Runnable updater = new Runnable(){
            public void run(){
                //Stuff we want to happen every 5 seconds goes here
                updateScheduledExpenses();
            };
            final ScheduledFuture<?> updaterHandle = scheduler.scheduleAtFixedRate(updater, 5, 5, SECONDS);
        }
    }

    private void updateScheduledExpenses(){
        Expense e;
        expenseList.filterByRecurring();
        for(int i = 0; i < expenseList.getFilteredList().size(); i++){
            if(expenseList.getFilteredList().get(i).needsUpdate()){
                e = new Expense(expenseList.getFilteredList().get(i).getName(),expenseList.getFilteredList().get(i).getAmount(),expenseList.getFilteredList().get(i).getCategory(),expenseList.getFilteredList().get(i).getNextOccurrence(),expenseList.getFilteredList().get(i).getNote(),expenseList.getFilteredList().get(i).getFrequency());
                //Need function that puts lets user confirm stuff

            }
        }
    }
}

