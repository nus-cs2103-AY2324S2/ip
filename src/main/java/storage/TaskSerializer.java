package storage;

import java.time.LocalDateTime;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

/**
 * responsible for serialising tasks into strings and parsing strings back into tasks.
 * facilitates the conversion of Task objects to and from a serializable format for storage purposes.
 */
public class TaskSerializer {
    private static final String DELIMITER = ", ";

    /**
     * Serializes a list of tasks into a string.
     *
     * @param t The list of tasks to be serialized.
     * @return A string representing the serialized tasks.
     */
    public static String serialize(Task t) {
        String[] taskFields = {"", "", "", "", ""};
        taskFields[1] = t.getStatus() ? "1" : "0";
        taskFields[2] = t.getDescription();

        if (t instanceof Todo) {
            taskFields[0] = "T";
        } else if (t instanceof Deadline) {
            taskFields[0] = "D";
            taskFields[4] = ((Deadline) t).getDueDate().toString();
        } else if (t instanceof Event) {
            taskFields[0] = "E";
            taskFields[3] = ((Event) t).getStartDate().toString();
            taskFields[4] = ((Event) t).getEndDate().toString();
        } else {
            throw new RuntimeException("Unable to cast Task class into any of its subclasses.");
        }
        String serializedTask = String.join(DELIMITER, taskFields);
        return serializedTask;
    }

    /**
     * Parses a string into a Task object.
     *
     * @param text The string representation of the task.
     * @return The parsed Task object.
     */
    public static Task parseText(String text) {
        String[] fields = text.split(DELIMITER);
        String taskType = fields[0];
        Task t = null;
        boolean status = fields[1].equals("0") ? false : true;

        switch (taskType) {
        case "T":
            t = new Todo(fields[2]);
            t.setStatus(status);
            break;
        case "D":
            LocalDateTime by = LocalDateTime.parse(fields[4]);
            t = new Deadline(fields[2], by);
            t.setStatus(status);
            break;
        case "E":
            LocalDateTime startDate = LocalDateTime.parse(fields[3]);
            LocalDateTime endDate = LocalDateTime.parse(fields[4]);
            t = new Event(fields[2], startDate, endDate);
            t.setStatus(status);
            break;
        default:
            break;
        }

        return t;
    }
}
