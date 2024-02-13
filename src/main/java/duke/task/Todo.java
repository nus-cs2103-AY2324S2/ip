package duke.task;

/**
 * The Todo class represents a task todo in the Duke chatbot.
 * It extends the Task class.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo task with the given description.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Generates the formatted content of the Todo task for storage in a file.
     *
     * @return The formatted content of the Todo task.
     */
    @Override
    public String writeContent() {
        return "T |" + (this.isDone ? " 1 | " : " 0 | ") + this.getDescription();
    }

    /**
     * Generates a string representation of the Todo task to display.
     *
     * @return The string representation of the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Todo)) {
            return false;
        }
        Todo other = (Todo) o;
        return other.description.equals(this.description);
    }
}
