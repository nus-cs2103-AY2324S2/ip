package Duke.Activities;

import Duke.Commands.DateFormat;

import java.time.LocalDate;

/**
 * Represents a Deadline activity, which is a type of activity with a specific due date.
 */
public class Deadline extends Activity {

    /**
     * The due date of the Deadline activity.
     */
    private final LocalDate date;

    /**
     * Constructor to initialize a Deadline activity with a specified name and due date.
     *
     * @param name The name of the Deadline activity.
     * @param date The due date of the Deadline activity.
     */
    public Deadline(String name, LocalDate date) {
        super(name);
        this.date = date;
    }

    /**
     * Returns a formatted string representation of the Deadline activity.
     *
     * @return A string containing details of the Deadline activity, including name, completion state,
     *         and due date.
     */
    @Override
    public String toString() {
        return "[D] " + super.getState() + " " + super.getName() +
                " (by: " + DateFormat.reformatDate(date) + ")";
    }

    /**
     * Converts the Deadline activity to a storage-friendly string format.
     *
     * @return A string representing the Deadline activity in a storage-friendly format.
     */
    @Override
    public String toStorage() {
        String str = "deadline ";
        str += super.getName() + " /by " + date;

        if (super.isMarked) {
            str += " /isMarked 1";
        } else {
            str += " /isMarked 0";
        }
        return str;
    }
}

