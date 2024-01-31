package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    private static DateTimeFormatter dFormatInp = DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm");
    private static DateTimeFormatter dFormatOut = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    private LocalDateTime deadline;

    private static final String symbol = "D";

    public Deadline(String des, LocalDateTime dl){
        super(des);
        deadline = dl;
    }
    public Deadline(String st, String des, LocalDateTime dl){
        super(des);
        deadline = dl;
        if (st.equals("true")){
            mark();
        } else {
            unMark();
        }
    }

    public String getSymbol() { //method to get symbol
        return symbol;
    }
    @Override
    public String toString() { //method to get the string representation of Duke.Deadline
        String s = "[" + this.getSymbol() + "][" + this.getStatusIcon() + "] " + this.description
                + " (by: " + this.deadline.format(dFormatOut) + ")";
        return s;
    }

    public String toWrite() { //method to get the string representation of Duke.Deadline
        String s = this.getSymbol() + "/" + this.isDone + "/" + this.description
                + "/" + this.deadline.format(dFormatInp);
        return s;
    }

    public static String getFormat(){
        return "deadline Description /by " + "yyyy-MM-dd HH:mm";
    }
}
