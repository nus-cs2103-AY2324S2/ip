package Duke.Activities;

import Duke.Commands.DateFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * The {@code Deadline} class represents a deadline activity, implementing the {@link Activity} interface. It encapsulates
 * the status, name, and due date and time of a task. This class is designed to manage deadlines, allowing for them to be
 * printed, named, and marked as complete or incomplete.
 */
public class Deadline extends Activity {
    private final LocalDate date;

    public Deadline(String name, LocalDate date) {
        super(name);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D] " + super.getState() + " " + super.getName() +
                " (by: " + DateFormat.reformatDate(date) + ")";
    }

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
