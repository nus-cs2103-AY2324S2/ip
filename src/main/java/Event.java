public class Event extends Task {
    private String from;
    private String to;

    private Event(String TaskString, String fromString, String toString) {
        // Call to 'super()' must be first statement in constructor body
        super(TaskString);
        this.from = fromString;
        this.to = toString;
    }


    public static Event EventFactory(String message) {
        message = message.replaceFirst("(?i)event ", "");
        String[] messageParts = message.split(" /from ");
        String taskMessage = messageParts[0];
        messageParts = message.split(" /to ");
        messageParts[0] = messageParts[0].split(" /from ")[1];
        return new Event(taskMessage, messageParts[0], messageParts[1]);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to +")";
    }
}
