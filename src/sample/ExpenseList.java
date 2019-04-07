package sample;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class ExpenseList
{
    private static ArrayList<Expense> list = new ArrayList<Expense>();
    private ArrayList<Expense> filteredList = new ArrayList<Expense>();

    public int getSize(){
        return list.size();
    }

    public boolean addExpense(Expense e){
        if( list.add(e) )
        {
            return true;
        }
        else
        {
            list.ensureCapacity(2 * list.size() );
            return this.addExpense(e);
        }
    }

    public boolean removeExpense(Expense e){
        return list.remove(e);
    }

    public void sortByDate()
    {
        Collections.sort(list, compareDate);
    }

    Comparator<Expense> compareDate = new Comparator<Expense>() {
        public int compare(Expense e1, Expense e2){
            return e1.getDate().compareTo(e2.getDate());
        }
    };

    public void sortByAmount(){
        Collections.sort(list, compareAmount);
    }

    Comparator<Expense> compareAmount = new Comparator<Expense>() {
        public int compare(Expense e1, Expense e2){
            return (new Double(e1.getAmount()).compareTo(new Double(e2.getAmount()));
        }
    };

    public void sortByName(){
        Collections.sort(list, compareName);
    }

    Comparator<Expense> compareName = new Comparator<Expense>() {
        public int compare(Expense e1, Expense e2){
            return e1.getName().compareTo(e2.getName());
        }
    };
}
