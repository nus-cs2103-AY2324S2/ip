import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class for task start with deadline
 */
public class Deadline extends Task{
    private LocalDate by;

    /**
     * constructor
     * @param descrip the dicription of the task.
     * @param by by field.
     */
    public Deadline(String descrip, LocalDate by) {
        super(descrip);
        this.by = by;
    }

    /**
     * Override the abstract class
     * @return T
     */
    @Override
    public String getTaskTypeIcon() {
        return "D";
    }

    /**
     * Override the abstract class
     * @return deadline
     */
    @Override
    public String getTaskType() {
        return "deadline";
    }

    /**
     * Override the toString method
     * @return String representation of the task
     */
    @Override
    public String toString() {
        return String.format("%s(by: %s)",super.toString(), this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    /**
     * return by
     * @return return by
     */
    public String getBy() {
        return this.by.toString();
    }
}
