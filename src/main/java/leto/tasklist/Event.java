package leto.tasklist;

public class Event extends Task {
    private String from;
    private String to;

    private Event(Boolean completed, String TaskString, String fromString, String toString) {
        // Call to 'super()' must be first statement in constructor body
        super(completed, TaskString);
        this.from = fromString;
        this.to = toString;
    }


    public static Event EventFactory(String message) throws InvalidTaskException {
        message = message.replaceFirst("(?i)event ", "");
        String[] messageParts = message.split(" /from ");
        if (messageParts.length < 2) {
            throw new EventInvalidCmdException();
        }
        String taskMessage = messageParts[0];
        messageParts = message.split(" /to ");
        if (messageParts.length < 2) {
            throw new EventInvalidCmdException();
        }
        messageParts[0] = messageParts[0].split(" /from ")[1];
        return new Event(false, taskMessage, messageParts[0], messageParts[1]);
    }

    /**
     * Create an Event task from csv format, Type,Completed,Task,By,From,To
     * @param entry text string containing the row in the csv
     * @return an Event task
     */
    public static Event EventFromCSV(String entry) {
        String[] parts = entry.split(",");
        Boolean completed = parts[1].equals("Y");
        String message = parts[2];
        String fromString = parts[4];
        String toString = parts[5];
        return new Event(completed, message, fromString, toString);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to +")";
    }

    /**
     * Returns the object as a row in a csv table according to format
     * Type,Completed,Task,By,From,To
     * @return String in csv format
     */
    public String toCSVString() {
        return "E," + super.toCSVString() + ",," + this.from + "," + this.to;
    }
}
