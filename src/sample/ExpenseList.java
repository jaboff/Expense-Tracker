package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class ExpenseList {
    private static ExpenseList expenseList = null;
    private static ObservableList<Expense> list = null;
    private static FilteredList<Expense> filteredList = null;

    private ExpenseList()
    {
        list = FXCollections.observableArrayList();
        filteredList = null;
    }

    public static ExpenseList getExpenseList()
    {
        if(expenseList == null)
            expenseList = new ExpenseList();

        return expenseList;
    }

    public static ObservableList<Expense> getList() {
        return list;
    }

    public ObservableList<Expense> getFilteredList() {
        return filteredList;
    }

    public int getSize() {
        return list.size();
    }

    public boolean addExpense(Expense e) {
        return list.add(e);
    }

    public Expense getExpense(int index) {
        return this.getList().get(index);
    }

    public boolean removeExpense(Expense e) {
        return list.remove(e);
    }

    public void clear()
    {
        if(list != null)
            list.clear();
        if(filteredList != null)
            filteredList.clear();
    }

    @Override
    public String toString() {
        String retS = "{ ";
        for (int i = 0; i < this.getSize(); i++) {
            retS += "" + this.getList().get(i).toString();
            if (i != this.getSize() - 1) {
                retS += ", ";
            }
        }
        retS += " }";
        return retS;
    }

    public void filterByCategory(String category) {
        filteredList.clear();
        for (int i = 0; i < this.getSize(); i++) {
            if (this.getExpense(i).getCategory().equals(category)) {
                filteredList.add(this.getList().get(i));
            }
        }
    }


    public void filterByDate(Date start, Date end) {
        filteredList.clear();
        for (int i = 0; i < this.getSize(); i++) {
            if (this.getExpense(i).getDate().after(start) && this.getExpense(i).getDate().before(end)) {
                filteredList.add(this.getList().get(i));
            }
        }
    }

    public void filterByRecurring(){
        filteredList.clear();
        for (int i = 0; i < this.getSize(); i++) {
            if (this.getExpense(i).isScheduled()) {
                filteredList.add(this.getList().get(i));
            }
        }
    }

    /*
    public void sortByDate() {
        Collections.sort(list, compareDate);
    }

    public void sortByDateR() {
        Collections.sort(list, compareDate.reversed());
    }

    private Comparator<Expense> compareDate = new Comparator<Expense>() {
        public int compare(Expense e1, Expense e2) {
            return e1.getDate().compareTo(e2.getDate());
        }
    };

    public void sortByAmount() {
        Collections.sort(list, compareAmount);
    }

    public void sortByAmountR() {
        Collections.sort(list, compareAmount.reversed());
    }

    private Comparator<Expense> compareAmount = new Comparator<Expense>() {
        public int compare(Expense e1, Expense e2) {
            return (new Double(e1.getAmount()).compareTo(new Double(e2.getAmount())));
        }
    };

    public void sortByName() {
        Collections.sort(list, compareName);
    }

    public void sortByNameR() {
        Collections.sort(list, compareName.reversed());
    }

    private Comparator<Expense> compareName = new Comparator<Expense>() {
        public int compare(Expense e1, Expense e2) {
            return e1.getName().compareTo(e2.getName());
        }
    };

    public void fSortByName() {
        Collections.sort(filteredList, compareName);
    }

    public void fSortByDate() {
        Collections.sort(filteredList, compareAmount);
    }

    public void fSortByAmount() {
        Collections.sort(filteredList, compareAmount);
    }

    public void fSortByNameR() {
        Collections.sort(filteredList, compareName.reversed());
    }

    public void fSortByDateR() {
        Collections.sort(filteredList, compareAmount.reversed());
    }

    public void fSortByAmountR() {
        Collections.sort(filteredList, compareAmount.reversed());
    }



    public void addToBothLists(Expense e) {
        list.add(e);
        filteredList.add(e);
    }

  /*
  public void updateScheduledExpenses(){
        Expense e;
        this.filterByRecurring();
        for(int i = 0; i < filteredList.size(); i++){
            if(filteredList.get(i).needsUpdate()){
                e = new Expense(filteredList.get(i).getName(),filteredList.get(i).getAmount(),filteredList.get(i).getCategory(),filteredList.get(i).getDate(),filteredList.get(i).getNote(),filteredList.get(i).getFrequency());
                this.addToBothLists(e);
            }
        }
    }
    */

/*
    public static void main(String[] args) {
        Expense e1 = new Expense("Apple", 1.99, "Food", new Date(), "Golden Delicious");
        Expense e2 = new Expense("Banana", 2.99, "Food", new Date(), "Fruit Salad");
        Expense e3 = new Expense("Watermelon", 4.99, "Food", new Date(), "Yummy Yummy");

        ExpenseList list = new ExpenseList();
        list.addExpense(e1);
        list.addExpense(e2);
        list.addExpense(e3);
        System.out.println(list.toString());
    }
 */
}
