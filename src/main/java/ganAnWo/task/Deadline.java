package ganAnWo.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class for task type deadline.
 *
 */
public class Deadline extends Task {
    private static final String SYMBOL = "D";
    private static final DateTimeFormatter DATE_FORMAT_INP = DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm");
    private static final DateTimeFormatter DATE_FORMAT_OUT = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    private LocalDateTime deadline;

    /**
     * Constructor for Deadline.
     * This constructor will be used when given
     * description and date as the parameter.
     *
     * @param des description for the task.
     * @param dl deadline date.
     */
    public Deadline(String des, LocalDateTime dl) {
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
    public Deadline(String st, String des, LocalDateTime dl) {
        super(des);
        deadline = dl;
        if (st.equals("true")) {
            setMark();
        } else {
            setUnMark();
        }
    }

    /**
     * Returns symbol for Deadline with String format.
     *
     * @return symbol for Deadline.
     */
    public String getSymbol() { //method to get symbol
        return SYMBOL;
    }

    /**
     * Returns string representative of Deadline.
     * The string consist of symbol, mark status,
     * description, and deadline date.
     *
     * @return String representative of Deadline.
     */
    @Override
    public String toString() {
        String s = "[" + this.getSymbol() + "][" + this.getStatusIcon() + "] " + this.description
                + " (by: " + this.deadline.format(DATE_FORMAT_OUT) + ")";
        return s;
    }

    /**
     * Returns string representative of Deadline for the write format.
     * The string consist of symbol, mark status,
     * description, and deadline date.
     *
     * @return String representative of Deadline in write format.
     */
    public String toWrite() {
        String s = this.getSymbol() + "/" + this.isDone + "/" + this.description
                + "/" + this.deadline.format(DATE_FORMAT_INP);
        return s;
    }

    /**
     * Returns format for deadline command in
     * string format.
     *
     * @return format for deadline command.
     */
    public static String getFormat() {
        return "deadline Description /by " + "yyyy-MM-dd HH:mm";
    }

    /**
     * Check whether the deadline instance date
     * is equal to other deadline instance date.
     *
     * @param task Deadline instance to be checked.
     * @return boolean result of the check.
     */
    public boolean isEqualDeadline(Deadline task) {
        return deadline.equals(task.deadline);
    }
    @Override
    public boolean isEqual(Task task) {
        if (!(task instanceof Deadline)) {
            return false;
        }
        boolean isEqualDesc = description.equals(task.description);
        boolean isEqualDl = isEqualDeadline((Deadline) task);
        return isEqualDl && isEqualDesc;
    }
}
