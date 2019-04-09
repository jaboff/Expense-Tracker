package sample;
import java.util.*;
class ScheduledExpense extends Expense{

    private long frequency; //gap between occurrences in milliseconds
    private Date nextOccurrence; //next occurrence

    ScheduledExpense(String n, double a, String c, Date d, String t, long f) {
        super(n,a,c,d,t);
        this.frequency = f;
        this.nextOccurrence = new Date();
        this.updateNext();
    }

    public void updateNext()
    {
        Date today = new Date();
        if(today.after(this.nextOccurrence)) {
            Date nextOcc = new Date(this.nextOccurrence.getTime() + frequency);
            this.nextOccurrence = nextOcc;
        }
    }
}