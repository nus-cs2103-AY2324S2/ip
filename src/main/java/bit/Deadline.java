package bit;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A task that has a deadline, which is represented with a LocalDate object
 */
public class Deadline extends Task {

    private LocalDate deadline;
    private boolean isValid = true;

    /**
     * Initializes a deadline task. If timing does not follow YYYY-mm-dd format,
     * will print out an error message.
     * @param description The description of the task
     * @param timing the deadline of the task
     */
    public Deadline(String description, String timing) {
        super(description);
        try {
            timing = timing.trim();
            System.out.println(timing);
            this.deadline = LocalDate.parse(timing);
        } catch (DateTimeException e) {
            System.out.println(e);
            deadline = null;
            isValid = false;
        }
    }

    @Override
    public String toString() {
        assert isValid : "The deadline being printed is invalid!";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        if (this.isDone) {
            return "[D][X] " + DESCRIPTION + " (by: " + deadline.format(formatter) + ")";
        }

        return "[D][ ] " + DESCRIPTION + " (by: " + deadline.format(formatter) + ")";
    }

    public String getDeadline() {
        return deadline.toString();
    }

    public String getFormatDeadline() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        return deadline.format(format);
    }


    public boolean getValid() {
        return isValid;
    }
}
