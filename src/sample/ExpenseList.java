package sample;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class ExpenseList {
    private static ArrayList<Expense> list = new ArrayList<Expense>();
    private static ArrayList<Expense> filteredList = new ArrayList<Expense>();

    public int getSize() {
        return list.size();
    }

    public boolean addExpense(Expense e) {
        if (list.add(e)) {
            return true;
        } else {
            list.ensureCapacity(2 * list.size());
            return this.addExpense(e);
        }
    }

    public boolean removeExpense(Expense e) {
        return list.remove(e);
    }

    public void sortByDate() {
        Collections.sort(list, compareDate);
    }

    public void sortByDateR() {
        Collections.sort(list, compareDate.reversed());
    }

    Comparator<Expense> compareDate = new Comparator<Expense>() {
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

    Comparator<Expense> compareAmount = new Comparator<Expense>() {
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

    Comparator<Expense> compareName = new Comparator<Expense>() {
        public int compare(Expense e1, Expense e2) {
            return e1.getName().compareTo(e2.getName());
        }
    };

    public static ArrayList<Expense> getList() {
        return list;
    }

    public ArrayList<Expense> getFilteredList() {
        return filteredList;
    }

    public Expense getExpense(int index) {
        return this.getList().get(index);
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
        filteredList = new ArrayList<Expense>();
        for (int i = 0; i < this.getSize(); i++) {
            if (this.getExpense(i).getCategory().equals(category)) {
                while (!(filteredList.add(this.getList().get(i)))) {
                    filteredList.ensureCapacity(filteredList.size() * 2);
                }
            }
        }
    }

    public void filterByDate(Date start, Date end) {
        filteredList = new ArrayList<Expense>();
        for (int i = 0; i < this.getSize(); i++) {
            if (this.getExpense(i).getDate().after(start) && this.getExpense(i).getDate().before(end)) {
                while (!(filteredList.add(this.getList().get(i)))) {
                    filteredList.ensureCapacity(filteredList.size() * 2);
                }
            }
        }
    }

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

    public void filterByRecurring(){
        filteredList = new ArrayList<Expense>();
        for (int i = 0; i < this.getSize(); i++) {
            if (this.getExpense(i).isScheduled()) {
                while (!(filteredList.add(this.getList().get(i)))) {
                    filteredList.ensureCapacity(filteredList.size() * 2);
                }
            }
        }
    }

    public void addToBothLists(Expense e) {
        list.add(e);
        filteredList.add(e);
    }
}