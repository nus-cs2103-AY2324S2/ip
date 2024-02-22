package victor.tasktype;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class is a child class of the Task class.
 * It inherits the variables from the Task Class, along with their
 * methods, and has overridden several methods in order to
 * add their own specific information to it.
 *
 * @author Dominic Fu Ming Jun
 */
public class Deadline extends Task {

    /**
     * The by variable is a String to indicate when the Deadline is due by.
     */
    protected String by;

    /**
     * The tempBy variable is a LocalDate that is hold the LocalDate of the by
     * variable when it is converted from a String into a LocalDate.
     */
    protected LocalDate tempBy;

    /**
     * The Deadline constructor takes in a String description and a boolean isDone
     * which is added to their respective variables of the same name.
     * This is done using the super keyword.
     * It also takes in a new input of by which is used to indicate
     * when the deadline is due by, and is added to the variable of the same name.
     * It also converts the String by into a LocalDate and added into the tempBy variable.
     *
     * @param description The description of the task.
     * @param isDone      States whether the task is done or not.
     * @param by          The date that indicate when the deadline is due by.
     */
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern(
                "MMM dd yyyy");
        tempBy = LocalDate.parse(by);
        this.by = tempBy.format(formatter);
    }

    /**
     * The toString method is overridden from the Task toString method to include
     * the additional information of [D] to indicate that this Task type is an
     * Deadline.
     *
     * @return A string that contains the extra information of the Event class,
     *         along with the normal information displayed from the Task toString method.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * The saveInput is overridden from the Task class. It is used to
     * write out all the data from the Deadline class in the format
     * of the data file before being saved into the data file.
     *
     * @return A string that is in the format to be added to the data file.
     */
    @Override
    public String saveInput() {
        assert !tempBy.getClass().equals(LocalDate.class): "tempBy is not a LocalDate. When accessing it \" +\n" +
                "                \"from the datanase, it will not format correctly.";
        return "D | " + isDone + " | " + description + " | " + tempBy;
    }
}
