package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    private static DateTimeFormatter dFormatInp = DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm");
    private static DateTimeFormatter dFormatOut = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    private LocalDateTime deadline;

    private static final String symbol = "D";

    /**
     * Constructor for Deadline.
     * This constructor will be used when given
     * description and date as the parameter.
     *
     * @param des description for the task.
     * @param dl deadline date.
     */
    public Deadline(String des, LocalDateTime dl){
        super(des);
        deadline = dl;
    }

    /**
     * Constructor for Deadline.
     * This constructor will be used when given
     * mark status, description, and date as the parameter.
     *
     * @param st mark status
     * @param des description for the task.
     * @param dl deadline date.
     */
    public Deadline(String st, String des, LocalDateTime dl){
        super(des);
        deadline = dl;
        if (st.equals("true")){
            mark();
        } else {
            unMark();
        }
    }

    /**
     * Returns symbol for Deadline with String format.
     *
     * @return symbol for Deadline.
     */
    public String getSymbol() { //method to get symbol
        return symbol;
    }

    /**
     * Returns string representative of Deadline.
     * The string consist of symbol, mark status,
     * description, and deadline date.
     *
     * @return String representative of Deadline.
     */
    @Override
    public String toString() { //method to get the string representation of Duke.Deadline
        String s = "[" + this.getSymbol() + "][" + this.getStatusIcon() + "] " + this.description
                + " (by: " + this.deadline.format(dFormatOut) + ")";
        return s;
    }

    /**
     * Returns string representative of Deadline for the write format.
     * The string consist of symbol, mark status,
     * description, and deadline date.
     *
     * @return String representative of Deadline in write format.
     */
    public String toWrite() { //method to get the string representation of Duke.Deadline
        String s = this.getSymbol() + "/" + this.isDone + "/" + this.description
                + "/" + this.deadline.format(dFormatInp);
        return s;
    }

    /**
     * Returns format for deadline command in
     * string format.
     *
     * @return format for deadline command.
     */
    public static String getFormat(){
        return "deadline Description /by " + "yyyy-MM-dd HH:mm";
    }
}
