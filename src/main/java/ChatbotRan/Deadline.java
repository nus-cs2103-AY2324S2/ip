package ChatbotRan;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Deadlines are Tasks with a date by which they should be completed.
 */
public class Deadline extends Task {
    private LocalDate deadline;

    public Deadline(String contents, LocalDate deadline) {
        super(contents);
        this.deadline = deadline;
    }

    public Deadline(String contents, String deadline) {
        this(contents, LocalDate.parse(deadline));
    }

    /**
     * Parses user input into a Deadline with delimiter /by.
     *
     * @param line  line of user input
     * @param space index to start parsing from
     * @return Deadline object
     * @throws TaskException if unparseable
     */
    public static Deadline parse(String line, int space) {
        String[] texts = Util.parse(line, space, "/by");
        try {
            return new Deadline(texts[0], texts[1]);
        } catch (DateTimeParseException e) {
            throw new TaskException("Invalid date: " + e.getParsedString());
        }
    }

    @Override
    String getType() {
        return "D";
    }

    public LocalDate getDeadline() {
        return this.deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.deadline + ")";
    }

    @Override
    String writeTask() {
        return String.format("D\\%b\\%s\\%s", completed, contents, deadline);
    }
}
