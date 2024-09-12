package dav;

import java.util.Set;

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
        if (parts.length < 3) {
            return null;
        }

        TodoTask todoTask = new TodoTask(parts[2]);
        todoTask.isDone = parts[1].equals("1");

        if (parts.length > 3) {
            String[] tagParts = parts[3].split(", ");
            for (String tag : tagParts) {
                todoTask.addTag(tag);
            }
        }

        return todoTask;
    }

    /**
     * Converts the todo task to a string for data storage.
     * @return Formatted string for data storage.
     */
    @Override
    public String toDataString() {
        StringBuilder dataString = new StringBuilder("T | " + (isDone ? "1" : "0") + " | " + description);
        Set<String> todoTags = getTags();
        if (!todoTags.isEmpty()) {
            dataString.append(" | ");
            for (String tag : todoTags) {
                dataString.append(tag).append(", ");
            }
            dataString.delete(dataString.length() - 2, dataString.length());
        }

        return dataString.toString();
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

