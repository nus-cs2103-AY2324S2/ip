package bot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Deadline class represents a task with a deadline.
 */
public class Deadline extends Task {
    private static Ui ui = new Ui();
    protected LocalDateTime by;
    static Parser parser = new Parser();

    /**
     * Constructs a new Deadline object.
     *
     * @param description The description of the task.
     * @param by          The deadline of the task, in the format "dd/MM/yyyy HHmm".
     * @throws DukeException If the deadline is not in the correct format.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        assert description != null : "Description is null";
        assert by != null : "Deadline is null";
        try {
            this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        } catch (DateTimeParseException e) {
            throw new DukeException(
                    "Invalid date format. Please use dd/MM/yyyy HHmm format. Example: 02/12/2019 1800\n");
        }
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    @Override
    public String toString() {
        return "D | " + super.toString() + " | "
                + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma"));
    }

    /**
     * Creates a new Deadline object from a string.
     *
     * @param input The string to create the Deadline object from.
     * @return A new Deadline object, or null if the Deadline object could not be
     *         created.
     */
    public static Deadline fromString(String input) {
        assert input != null : "Input is null";
        String[] split = parser.parseDeadlineFromStorage(input);
        try {
            Deadline deadline = new Deadline(split[2], split[3]);
            if (split[1].equals("X")) {
                deadline.markAsDone();
            }
            return deadline;
        } catch (DukeException e) {
            ui.printMessage("Error creating deadline");
            return null;
        }
    }

    /**
     * Returns a string representation of the task that can be written to a file.
     *
     * @return A string representation of the task that can be written to a file.
     */
    public String toFileString() {
        return "D | " + super.toString() + " | "
                + by.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    public static String getHelpString() {
        return "Add Deadline: Adds a task with a deadline\n"
                + "  To add a deadline, use the following format\n"
                + "    deadline <description> /by <deadline>\n"
                + "  The deadline should be in the format 'dd/MM/yyyy HHmm'.\n"
                + "    Example: deadline return book /by 02/12/2019 1800\n";
    }
}