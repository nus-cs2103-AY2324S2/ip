package BotChat;

/**
 * Represents a todo task in the botChat application, extending the base Task class.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo object with the specified description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Provides a string representation of the todo task, including its status icon and description.
     *
     * @return A string representation of the todo task.
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}