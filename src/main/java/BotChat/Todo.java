package BotChat;

/**
 * Represents a tod0 task in the botChat application, extending the base Task class.
 */
public class Todo extends Task {

    /**
     * Constructs a Tod0 object with the specified description.
     *
     * @param description The description of the tod0 task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
