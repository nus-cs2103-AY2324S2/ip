package bot;

/**
 * The Task class represents a task with a description and a completion status.
 */

public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructs a new Task object.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done and prints a message.
     */
    public String markAsDone() {
        this.isDone = true;
        return ("Nice! I've marked this task as done: \n" + this.toString() + "\n");
    }

    /**
     * Unmarks the task as done and prints a message.
     */
    public String unmarkDone() {
        this.isDone = false;
       return ("OK, I've marked this task as not done yet: \n" + this.toString() + "\n");
    }

    @Override
    public String toString() {
        return (isDone ? "X" : " ") + " | " + description;
    }

    /**
     * Creates a Task object from a string.
     *
     * @param input The string to parse.
     * @return A new Task object.
     */
    public static Task fromString(String input) {
        Task task;
        if (input.startsWith("T")) {
            task = ToDo.fromString(input);
        } else if (input.startsWith("D")) {
            task = Deadline.fromString(input);
        } else {
            task = Event.fromString(input);
        }
        return task;
    }

    /**
     * Converts the task to a string for file storage.
     *
     * @return A string representation of the task for file storage.
     */
    public String toFileString() {
        return toString();
    }

    public String getDescription() {
        return description;
    }
}
