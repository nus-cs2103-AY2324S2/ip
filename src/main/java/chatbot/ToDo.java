package chatbot;

/**
 * Represents a to-do task.
 */
public class ToDo extends chatbot.Task {
    public ToDo(String description) {
        super(description);
    }
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
