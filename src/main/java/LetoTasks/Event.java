package LetoTasks;

public class Event extends Task {
    private String from;
    private String to;

    private Event(String TaskString, String fromString, String toString) {
        // Call to 'super()' must be first statement in constructor body
        super(TaskString);
        this.from = fromString;
        this.to = toString;
    }


    public static Event EventFactory(String message) throws LetoInvalidTaskException {
        message = message.replaceFirst("(?i)event ", "");
        String[] messageParts = message.split(" /from ");
        if (messageParts.length < 2) {
            throw new LetoEventInvalidCmdException();
        }
        String taskMessage = messageParts[0];
        messageParts = message.split(" /to ");
        if (messageParts.length < 2) {
            throw new LetoEventInvalidCmdException();
        }
        messageParts[0] = messageParts[0].split(" /from ")[1];
        return new Event(taskMessage, messageParts[0], messageParts[1]);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to +")";
    }
}
