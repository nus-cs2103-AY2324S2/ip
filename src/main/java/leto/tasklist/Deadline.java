package leto.tasklist;

public class Deadline extends Task {
    private String deadline;

    private Deadline(Boolean completed, String TaskString, String DeadlineString) {
        // Call to 'super()' must be first statement in constructor body
        super(completed, TaskString);
        this.deadline = DeadlineString;
    }

    public static Deadline DeadlineFactory(String message) throws InvalidTaskException {
        message = message.replaceFirst("deadline ", "");
        String[] messageParts = message.split(" /by ");
        if (messageParts.length < 2) {
            throw new InvalidTaskException("Task need to follow\n   `deadline _task_ /by _time_` format");
        }
        return new Deadline(false, messageParts[0], messageParts[1]);
    }

    /**
     * Create a Deadline task from csv format, Type,Completed,Task,By,From,To
     * @param entry text string containing the row in the csv
     * @return a Deadline task
     */
    public static Deadline DeadlineFromCSV(String entry) {
        String[] parts = entry.split(",");
        Boolean completed = parts[1].equals("Y");
        String message = parts[2];
        String deadlineString = parts[3];
        return new Deadline(completed, message, deadlineString);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }

    /**
     * Returns the object as a row in a csv table according to format
     * Type,Completed,Task,By,From,To
     * @return String in csv format
     */
    public String toCSVString() {
        return "D," + super.toCSVString() + "," + this.deadline + ",,";
    }
}
