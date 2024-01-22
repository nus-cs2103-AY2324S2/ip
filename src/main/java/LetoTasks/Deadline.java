package LetoTasks;

public class Deadline extends Task {
    private String deadline;

    private Deadline(String TaskString, String DeadlineString) {
        // Call to 'super()' must be first statement in constructor body
        super(TaskString);
        this.deadline = DeadlineString;
    }

    public static Deadline DeadlineFactory(String message) throws LetoInvalidTaskException {
        message = message.replaceFirst("deadline ", "");
        String[] messageParts = message.split(" /by ");
        if (messageParts.length < 2) {
            throw new LetoInvalidTaskException("Task need to follow\n   `deadline _task_ /by _time_` format");
        }
        return new Deadline(messageParts[0], messageParts[1]);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}
