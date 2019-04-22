package sample;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class ExpenseList implements Serializable {
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

    public void filterByRecurring() {
        filteredList = new ArrayList<Expense>();
        for (int i = 0; i < this.getSize(); i++) {
            if (this.getExpense(i).isSchedued()) {
                while (!(filteredList.add(this.getList().get(i)))) {
                    filteredList.ensureCapacity(filteredList.size() * 2);
                }
            }
        }
    }

    /**
      * Calculates the total cost of the user's expenses.
      * @return The total dollar amount of expenses
      */
    public double calculateTotalExpenses() {
        double total_cost = 0.00; //initializes total cost as $0.00

        for (int i = 0; i < list.getSize(); i++) { //for each expense in the expense list
            total_cost += list.getExpense(i).getAmount(); //add each amount to the total cost
        }

        return total_cost;
    }

    /**
      * Saves the user's data to an external file.
      *
      * @param fileName String representation of the file name
      */
    public void saveUserData(String fileName) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(new FileOutputStream(fileName)); //writes data to a file

          for (int i = 0; i < list.getSize(); i++) {
            System.out.println("Expense #" + (i+1) ": "); //prints what number the expense is in the list
            pw.println(list.getExpense(i).getName()); //then prints its data
            pw.println(list.getExpense(i).getAmount());
            pw.println(list.getExpense(i).getCategory());
            pw.println(list.getExpense(i).getDate());
            pw.println(list.getExpense(i).getNote());
            System.out.println();
          }

        pw.close();
    }

    /**
      * Loads the user's data from an external file.
      *
      * @param fileName String representation of the file name
      */
    public void loadUserData(String fileName) throws FileNotFoundException {
      FileInputStream input = new FileInputStream("filename.txt");
      ObjectInputStream output = new ObjectInputStream(input);
      ExpenseList list = (ExpenseList) output.readObject();
      output.close();
    }



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
