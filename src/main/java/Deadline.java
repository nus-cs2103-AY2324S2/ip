import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Task with a deadline attached.
 *
 * @author KohGuanZeh
 */
public class Deadline extends Task {
    // Format to create Deadline task in program.
    public static String CREATE_DEADLINE_FORMAT = "deadline <task-name> /by <dd-MM-yyyy HH:mm>";

    // Deadline of task.
    private LocalDateTime by;

    /**
     * Creates a Task with given description and specified deadline.
     *
     * @param description Description of task.
     * @param by Deadline for the task.
     */
    private Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Creates a new Deadline task based on user input.
     *
     * @param input Input String after the "deadline" command.
     * @return New Deadline task.
     * @throws TaskException Exception when task cannot be created.
     */
    public static Deadline createDeadline(String input) throws TaskException {
        input = input.trim();
        try {
            String[] tokens = input.split(" /by ");
            String description = tokens[0];
            if (description.isEmpty()) {
                throw new TaskException("Error. Unable to create task.\nFormat: " + Deadline.CREATE_DEADLINE_FORMAT);
            }
            LocalDateTime by = LocalDateTime.parse(tokens[1], Task.INPUT_DATETIME_FORMAT);
            return new Deadline(description, by);
        } catch (IndexOutOfBoundsException | DateTimeParseException e) {
            throw new TaskException("Error. Unable to create task.\nFormat: " + Deadline.CREATE_DEADLINE_FORMAT);
        }
    }

    /**
     * Returns the task, task type, completion status and task deadline.
     * Tasks of Event type are marked with [D].
     * Tasks that are done are marked with "[X]" whereas tasks that are not done are marked with "[ ]".
     *
     * @return Task type, completion status, description and deadline.
     */
    @Override
    public String getTaskInformation() {
        return "[D]" + super.getTaskInformation() + " (by: " + this.by.format(Task.OUTPUT_DATETIME_FORMAT) + ")";
    }

    /**
     * Returns String to be saved in data file and loaded for future use.
     *
     * @return String data of task.
     */
    @Override
    public String saveTaskAsString() {
        return "D | " + (this.getIsDone() ? 1 : 0) + " | " + this.getDescription()
                + " /by " + this.by.format(Task.INPUT_DATETIME_FORMAT);
    }
}
