package micromanager.tasks;

/**
 * Todo class represents a todo task in the task list.
 * It extends the Task class and provides methods to manage todo tasks.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo object with the specified task description.
     *
     * @param taskDescription The description of the todo task.
     */
    public Todo(String taskDescription) {
        super(taskDescription);
    }

    /**
     * Converts the todo task to a string representation for saving to a file.
     *
     * @return A string representation of the todo task for saving to a file.
     */
    public String toFileString() {
        return String.format("T,%b,%s", this.isDone, this.taskDescription);
    }

    /**
     * Returns a string representation of the todo task.
     * Overrides the toString method in the Task class.
     *
     * @return A string representation of the todo task.
     */
    @Override
    public String toString() {
        return String.format("[T][%s] %s", this.isDone ? "X" : " ", this.taskDescription);
    }
}
