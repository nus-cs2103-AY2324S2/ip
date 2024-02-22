package shodan.tasks;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import shodan.tasks.impl.Deadline;
import shodan.tasks.impl.Event;
import shodan.tasks.impl.Todo;

/**
 * This class contains static methods that are responsible for serializing
 * Tasks into their string representation, and vice versa.
 */
public class TaskSerializer {
    private static final String DELIMITER = ";";

    /**
     * Serialize a Task into its string format.
     *
     * @param tasks the list of tasks to serialize.
     * @return a string containing the serialized list of tasks.
     */
    public static String serialize(List<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        for (Task t : tasks) {
            String[] taskFields = {"", "", "", "", ""};
            if (t instanceof Todo) {
                Todo todo = (Todo) t;
                taskFields[0] = TaskType.TODO.name();
                taskFields[1] = String.valueOf(todo.isDone());
                taskFields[2] = todo.getName();
            } else if (t instanceof Deadline) {
                Deadline deadline = (Deadline) t;
                taskFields[0] = TaskType.DEADLINE.name();
                taskFields[1] = String.valueOf(deadline.isDone());
                taskFields[2] = deadline.getName();
                taskFields[4] = deadline.getEndDate();
            } else if (t instanceof Event) {
                Event event = (Event) t;
                taskFields[0] = TaskType.EVENT.name();
                taskFields[1] = String.valueOf(event.isDone());
                taskFields[2] = event.getName();
                taskFields[3] = event.getStartDate();
                taskFields[4] = event.getEndDate();
            } else {
                throw new RuntimeException("Unable to cast Task class into any of its subclasses.");
            }
            for (String field : taskFields) {
                if (field.contains(DELIMITER)) {
                    throw new IllegalArgumentException(String.format("Unable to serialize task: "
                            + "%s because one of its fields contains the delimiter used for serializing.", t));
                }
            }
            String serializedTask = String.join(DELIMITER, taskFields);
            sb.append(serializedTask);
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Convert serialized tasks back into their original form.
     *
     * @param text a stream of Strings, where each String represents a serialized task.
     * @return a list of parsed Tasks.
     */
    public static List<Task> parseText(Stream<String> text) {
        return text.filter(Predicate.not(String::isBlank)).map((String t) -> {
            String[] fields = t.split(DELIMITER);
            try {
                boolean isDone;
                if (fields[1].equalsIgnoreCase("true")) {
                    isDone = true;
                } else if (fields[1].equalsIgnoreCase("false")) {
                    isDone = false;
                } else {
                    throw new IllegalArgumentException();
                }

                switch (TaskType.valueOf(fields[0])) {
                case TODO:
                    Todo todo = new Todo(fields[2]);
                    todo.setDone(isDone);
                    return todo;
                case DEADLINE:
                    Deadline deadline = new Deadline(fields[2], LocalDateTime.parse(fields[4]));
                    deadline.setDone(isDone);
                    return deadline;
                case EVENT:
                    Event event = new Event(fields[2], LocalDateTime.parse(fields[3]), LocalDateTime.parse(fields[4]));
                    event.setDone(isDone);
                    return event;
                default:
                    throw new IllegalArgumentException("Unexpected value: " + TaskType.valueOf(fields[0]));
                }
            } catch (IllegalArgumentException e) {
                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toList());
    }
}
