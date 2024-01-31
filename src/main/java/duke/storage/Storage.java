package duke.storage;

import duke.parser.DateParser;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.core.ChatbotException;
import java.time.LocalDateTime;

public class Storage {
    public static String taskToFileString(Task task) {
        StringBuilder sb = new StringBuilder();

        // Append the type of the task
        if (task instanceof ToDo) {
            sb.append("T");
        } else if (task instanceof Deadline) {
            sb.append("D");
        } else if (task instanceof Event) {
            sb.append("E");
        }

        // Append the done status
        sb.append(" | ").append(task.getStatus() ? "1 | " : "0 | ").append(task.getInitialDesc());

        // Append the deadline or event time if applicable
        if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            sb.append(" | ").append(deadline.getBy()); // Append the 'by' time for deadlines
        } else if (task instanceof Event) {
            Event event = (Event) task;
            sb.append(" | ").append(event.getFrom()).append(" - ").append(event.getTo()); // Append the 'from - to' times for events
        }

        return sb.toString();
    }


    public static Task fileStringToTask(String fileString) {
        String[] parts = fileString.split(" \\| ");
        if (parts.length < 3) {
            ChatbotException.getError(ChatbotException.ErrorType.TASK_CORRUPT);
        }

        String type = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2];

        Task task = new Task(description);

        switch (type) {
            case "T":
                task = createTask("todo", description);
                break;
            case "D":
                if (parts.length < 4) ChatbotException.getError(ChatbotException.ErrorType.TODO_CORRUPT);
                description += " /by " + parts[3];
                task = createTask("deadline", description);
                break;
            case "E":
                String timeInfo = parts[3].trim();
                String[] timeParts = timeInfo.split(" - ");
                if (timeParts.length < 2) ChatbotException.getError(ChatbotException.ErrorType.EVENT_CORRUPT);
                description += " /from " + timeParts[0].trim() + " /to " + timeParts[1].trim();
                task = createTask("event", description);
                break;
            default:
                ChatbotException.getError(ChatbotException.ErrorType.UNKNOWN_TASK);
        }

        task.mark(isDone);
        return task;
    }

    public static Task createTask(String command, String description) {
        if (command.startsWith("todo")) {
            return new ToDo(description);

        } else if (command.startsWith("deadline")) {
            String[] parts = description.split("/by", 2);
            LocalDateTime by = DateParser.parseDateTime(parts[1].trim());
            return new Deadline(parts[0], by);

        } else {
            String[] parts = description.split("\\s+/from\\s+|\\s+/to\\s+");
            LocalDateTime from = DateParser.parseDateTime(parts[1].trim());
            LocalDateTime to = DateParser.parseDateTime(parts[2].trim());
            return new Event(parts[0], from, to);

        }
    }
}
