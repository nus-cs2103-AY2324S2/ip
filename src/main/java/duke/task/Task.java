package duke.task;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class Task implements Serializable {
    protected static DateTimeFormatter describeTimeFormat = DateTimeFormatter.ofPattern("EE',' dd MMMM yy 'at' hh:mma");

    private enum Status {
        Complete("completed", 'X'), Incomplete("pending", ' ');

        private final char icon;
        private final String description;

        Status(String description, char icon) {
            this.icon = icon;
            this.description = description;
        }
    }

    private final String description;
    private Status status;

    protected Task(String description) {
        this.description = description;
        status = Status.Incomplete;
    }

    public final void setComplete() {
        status = Status.Complete;
    }

    public final void setIncomplete() {
        status = Status.Incomplete;
    }

    public final String status() {
        return status.description;
    }

    /**
     * A more verbose english explanation of the task.
     *
     * @return description of the task
     */
    public String describe() {
        return description;
    }

    protected static Set<String> keys(String... keys) {
        return new HashSet<>(List.of(keys));
    }

    protected static void validateComponentKeys(Set<String> expected, Set<String> actual) throws InvalidComponents {
        // DESCRIPTION is assumed to be implicit
        actual.remove("DESCRIPTION");

        if (expected.size() != actual.size()) {
            throw new InvalidComponents(actual, expected);
        }

        for (String component : expected) {
            if (!actual.contains(component)) {
                throw new InvalidComponents(actual, expected);
            }
        }
    }

    private static Map<String, String> parseComponents(String data) {
        HashMap<String, StringBuilder> builders = new HashMap<>();

        String key = "DESCRIPTION";
        String[] words = data.split(" +");
        for (String word : words) {
            if (word.startsWith("/")) {
                key = word;
            } else {
                builders.compute(key, (k, v) -> (v == null) ? new StringBuilder(word) : v.append(" ").append(word));
            }
        }

        HashMap<String, String> components = new HashMap<>();
        builders.forEach((k, v) -> components.put(k, v.toString()));
        return components;
    }

    protected static LocalDateTime parseDateTime(String input) throws DateTimeParseException {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return LocalDateTime.from(f.parse(input));
    }

    public static Task of(String type, String data) throws InvalidType, InvalidComponents, DateTimeParseException {
        Map<String, String> components = parseComponents(data);
        Task task;
        switch (type) {
        case "todo":
            task = new Todo(components);
            break;
        case "deadline":
            task = new Deadline(components);
            break;
        case "event":
            task = new Event(components);
            break;
        default:
            throw new InvalidType(type);
        }
        return task;
    }

    public static class InvalidType extends Exception {
        public InvalidType(String type) {
            super("Invalid task type: " + type);
        }
    }

    public static class InvalidComponents extends Exception {
        public InvalidComponents(Set<String> actual, Set<String> expected) {
            super("Invalid task components: " + actual + "; expected: " + expected);
        }
    }

    @Override
    public String toString() {
        return "[" + status.icon + "] " + description;
    }
}
