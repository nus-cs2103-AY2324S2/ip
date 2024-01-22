public class Deadline extends Task {
    private String deadline;

    private Deadline(String TaskString, String DeadlineString) {
        // Call to 'super()' must be first statement in constructor body
        super(TaskString);
        this.deadline = DeadlineString;
    }

    public static Deadline DeadlineFactory(String message) {
        message = message.replaceFirst("deadline ", "");
        String[] messageParts = message.split(" /by ");
        return new Deadline(messageParts[0], messageParts[1]);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}
