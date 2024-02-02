package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    private static final DateTimeFormatter DATE_FORMAT_INP = DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm");
    private static final DateTimeFormatter DATE_FORMAT_OUT = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    private LocalDateTime deadline;

    private static final String SYMBOL = "D";

    public Deadline(String des, LocalDateTime dl) {
        super(des);
        deadline = dl;
    }
    public Deadline(String st, String des, LocalDateTime dl) {
        super(des);
        deadline = dl;
        if (st.equals("true")) {
            setMark();
        } else {
            setUnMark();
        }
    }

    public String getSymbol() { //method to get symbol
        return SYMBOL;
    }
    @Override
    public String toString() {
        String s = "[" + this.getSymbol() + "][" + this.getStatusIcon() + "] " + this.description
                + " (by: " + this.deadline.format(DATE_FORMAT_OUT) + ")";
        return s;
    }

    public String toWrite() {
        String s = this.getSymbol() + "/" + this.isDone + "/" + this.description
                + "/" + this.deadline.format(DATE_FORMAT_INP);
        return s;
    }

    public static String getFormat(){
        return "deadline Description /by " + "yyyy-MM-dd HH:mm";
    }
}
