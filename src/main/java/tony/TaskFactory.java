package tony;

import tony.tasks.*;
import tony.exceptions.*;

import java.time.LocalDateTime;

public class TaskFactory {
    public Task createTask(TaskType type, String... args) throws InvalidTaskException {
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
                    throw new InvalidTaskException("Error creating DEADLINE task " + e.getMessage());
                }
            case EVENT:
                try {
                    String fromString = args[1].substring(5).trim();
                    String toString = args[2].substring(3);
                    LocalDateTime from = Parser.parseDate(fromString);
                    LocalDateTime to = Parser.parseDate(toString);
                    return new Event(description, from, to);
                } catch (Exception e) {
                    throw new InvalidTaskException("Error creating EVENT task " + e.getMessage());
                }
            default:
                throw new IllegalArgumentException("Invalid task type: " + type);
        }
    }
}

