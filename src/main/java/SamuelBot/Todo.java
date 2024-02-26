package SamuelBot;

/**
 * The Todo class represents a todo task.
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo object with the given description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Gets the type of the task.
     *
     * @return The type of the task, which is "T" for todo.
     */
    @Override
    public String getTaskType() {
        return "T";
    }

    /**
     * Converts the todo task to a string format for saving to a file.
     *
     * @return A string representation of the todo task in file format.
     */
    @Override
    public String toFileString() {
        return "T | " + (isDone ? "X" : " ") + " | " + description;
    }

    /**
     * Gets the description of the todo task.
     *
     * @return The description of the todo task.
     */
    @Override
    public String getDescription(){
        return description;
    }

    /**
     * Checks if the todo task is done.
     *
     * @return true if the todo task is done, false otherwise.
     */
    public boolean isDone(){
        return isDone;
    }
}
