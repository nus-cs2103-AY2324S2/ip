package shodan.tasks;

import shodan.tasks.impl.Deadline;
import shodan.tasks.impl.Event;
import shodan.tasks.impl.Todo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TaskSerializer {
    private final static String DELIMITER = ";";

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
                    throw new IllegalArgumentException(String.format("Unable to serialize task: %s because one of its fields contains the delimiter used for serializing.", t));
                }
            }
            String serializedTask = String.join(DELIMITER, taskFields);
            sb.append(serializedTask);
            sb.append("\n");
        }
        return sb.toString();
    }

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
                    if (isDone) {
                        todo.done();
                    }
                    return todo;
                case DEADLINE:
                    Deadline deadline = new Deadline(fields[2], LocalDateTime.parse(fields[4]));
                    if (isDone) {
                        deadline.done();
                    }
                    return deadline;
                case EVENT:
                    Event event = new Event(fields[2], LocalDateTime.parse(fields[3]), LocalDateTime.parse(fields[4]));
                    if (isDone) {
                        event.done();
                    }
                    return event;
                }
            } catch (IllegalArgumentException e) {
                return null;
            }
            return null;
        }).filter(Objects::nonNull).collect(Collectors.toList());
    }
}
