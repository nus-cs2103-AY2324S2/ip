package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{

    private static final DateTimeFormatter DATE_FORMAT_INP = DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm");
    private static final DateTimeFormatter DATE_FORMAT_OUT = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    private LocalDateTime from;
    private LocalDateTime to;
    private static final String SYMBOL = "E";

    public Event(String des, LocalDateTime f, LocalDateTime t) {
        super(des);
        from = f;
        to = t;
    }

    public Event(String st, String des, LocalDateTime f, LocalDateTime t) {
        super(des);
        from = f;
        to = t;
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
                + " (from: " + this.from.format(DATE_FORMAT_OUT) + " to: " + this.to.format(DATE_FORMAT_OUT) + ")";
        return s;
    }

    public String toWrite() {
        String s = this.getSymbol() + "/" + this.isDone + "/" + this.description
                + "/" + this.from.format(DATE_FORMAT_INP) + "/" + this.to.format(DATE_FORMAT_INP);
        return s;
    }
    public static String getFormat(){
        return "event Description /from " + "yyyy-MM-dd HH:mm" + " /to " + "yyyy-MM-dd HH:mm";
    }
}
