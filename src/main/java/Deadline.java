/**
 * Task with a deadline attached.
 *
 * @author KohGuanZeh
 */
public class Deadline extends Task {
    // Format to create Deadline task in program.
    public static String CREATE_DEADLINE_FORMAT = "deadline <task-name> /by <deadline>";

    // Deadline of task.
    private String deadline;

    /**
     * Creates a Task with given description and specified deadline.
     *
     * @param description Description of task.
     * @param deadline Deadline for the task.
     */
    private Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
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
            String description = tokens[0], by = tokens[1];
            if (description.isEmpty() || by.isEmpty()) {
                throw new TaskException("Error. Unable to create task.\nFormat: " + Deadline.CREATE_DEADLINE_FORMAT);
            }
            return new Deadline(description, by);
        } catch (IndexOutOfBoundsException e) {
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
        return "[D]" + super.getTaskInformation() + " (by: " + this.deadline + ")";
    }

}
