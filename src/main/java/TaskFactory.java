import tasks.Task;
import tasks.Deadline;
import tasks.Event;
import tasks.Todo;
import tasks.TaskType;

import java.time.LocalDateTime;

public class TaskFactory {
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

