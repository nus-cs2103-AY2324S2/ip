package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{

    private static DateTimeFormatter dFormatInp = DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm");
    private static DateTimeFormatter dFormatOut = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    private LocalDateTime from;
    private LocalDateTime to;
    private static final String symbol = "E";

    /**
     * Constructor for Event.
     * This constructor will be used when given
     * description, starting time, and
     * finishing time as the parameter.
     *
     * @param des description for the task.
     * @param f starting time.
     * @param t finishing time
     */
    public Event(String des, LocalDateTime f, LocalDateTime t){
        super(des);
        from = f;
        to = t;
    }

    /**
     * Constructor for Event.
     * This constructor will be used when given
     * mark status, description, starting time, and
     * finishing time as the parameter.
     *
     * @param st mark status
     * @param des description for the task.
     * @param f starting time.
     * @param t finishing time
     */
    public Event(String st, String des, LocalDateTime f, LocalDateTime t){
        super(des);
        from = f;
        to = t;
        if (st.equals("true")){
            mark();
        } else {
            unMark();
        }
    }

    /**
     * Returns symbol for Event with String format.
     *
     * @return symbol for Event.
     */
    public String getSymbol() { //method to get symbol
        return symbol;
    }

    /**
     * Returns string representative of Event.
     * The string consist of symbol, mark status,
     * description, starting time and finishing time.
     *
     * @return String representative of Deadline.
     */
    @Override
    public String toString() { //method to get the string representation of Duke.Event
        String s = "[" + this.getSymbol() + "][" + this.getStatusIcon() + "] " + this.description
                + " (from: " + this.from.format(dFormatOut) + " to: " + this.to.format(dFormatOut) + ")";
        return s;
    }

    /**
     * Returns string representative of Event for the write format..
     * The string consist of symbol, mark status,
     * description, starting time and finishing time.
     *
     * @return String representative of Deadline.
     */
    public String toWrite() { //method to get the string representation of Duke.Event
        String s = this.getSymbol() + "/" + this.isDone + "/" + this.description
                + "/" + this.from.format(dFormatInp) + "/" + this.to.format(dFormatInp);
        return s;
    }

    /**
     * Returns format for event command in
     * string format.
     *
     * @return format for event command.
     */
    public static String getFormat(){
        return "event Description /from " + "yyyy-MM-dd HH:mm" + " /to " + "yyyy-MM-dd HH:mm";
    }
}
