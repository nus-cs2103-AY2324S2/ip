package dav;

/**
 * Represents a todo task with a description and completion status.
 */
class TodoTask extends Task {

    /**
     * Constructs a TodoTask with the specified description.
     * @param description The description of the todo task.
     */
    public TodoTask(String description) {
        super(description);
    }

    /**
     * Parses todo task data string and returns a TodoTask object.
     * @param data Data string representing the todo task.
     * @return TodoTask object if parsing is successful, otherwise null.
     */
    public static Task parseTask(String data) {
        String[] parts = data.split(" \\| ");
        if (parts.length == 3) {
            TodoTask todoTask = new TodoTask(parts[2]);
            todoTask.isDone = parts[1].equals("1");
            return todoTask;
        }
        return null;
    }

    /**
     * Converts the todo task to a string for data storage.
     * @return Formatted string for data storage.
     */
    @Override
    public String toDataString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Converts the todo task to a string for display.
     * @return Formatted string for display.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

