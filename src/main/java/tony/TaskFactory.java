package tony;

import tony.tasks.Task;
import tony.tasks.Deadline;
import tony.tasks.Event;
import tony.tasks.Todo;
import tony.tasks.TaskType;

import java.time.LocalDateTime;

/**
 * The TaskFactory class creates task objects based on the provided task type and arguments.
 * It supports creating Todo, Deadline, and Event tasks.
 */
public class TaskFactory {

    /**
     * Creates a task object based on the given task type and arguments.
     *
     * @param type The type of task to create (e.g., TaskType.TODO, TaskType.DEADLINE, TaskType.EVENT).
     * @param args The arguments needed to create the task, which vary depending on the task type.
     * @return A task object of the specified type.
     * @throws IllegalArgumentException If an invalid task type is provided.
     */
    public Task createTask(TaskType type, String... args) {
        String description = args[0];
        switch (type) {
            case TODO:
                return new Todo(description);
            case DEADLINE:
                try {
                    String due = args[1].substring(3).trim();
                    LocalDateTime dueDate = Parser.parseDate(due);
                    return new Deadline(description, dueDate);
                } catch (Exception e) {
                    System.out.println("Error parsing LocalDateTime: " + e.getMessage());
                }
            case EVENT:
                try {
                    String fromString = args[1].substring(5).trim();
                    String toString = args[2].substring(3);
                    LocalDateTime from = Parser.parseDate(fromString);
                    LocalDateTime to = Parser.parseDate(toString);
                    return new Event(description, from, to);
                } catch (Exception e) {
                    System.out.println("Error parsing LocalDateTime: " + e.getMessage());
                }
            default:
                throw new IllegalArgumentException("Invalid task type: " + type);
        }
    }
}

