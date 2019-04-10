package sample;
import java.util.*;
class ScheduledExpense extends Expense{

    private long frequency; //gap between occurrences in milliseconds
    private Date nextOccurrence; //next occurrence

    ScheduledExpense(String name, double amount, String category, Date date, String note, long frequency) {
        super(name,amount,category,date,note);
        this.frequency = frequency;
        this.nextOccurrence = new Date(date.getTime() + frequency);
    }

    public boolean isScheduled() {
        return true;
    }

    public boolean needsUpdate()
    {
        Date today = new Date();
        return today.after(this.getNextOccurrence());
    }

    public Date getNextOccurrence() {
        return nextOccurrence;
    }

    public void setNextOccurrence(Date nextOccurrence) {
        this.nextOccurrence = nextOccurrence;
    }

    public long getFrequency() {
        return frequency;
    }

    public void setFrequency(long frequency) {
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return super.toString() + " <- [Occurs every " + this.getFrequency() + " milliseconds]";
    }
}