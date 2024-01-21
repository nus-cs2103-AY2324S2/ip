import java.util.*;

public class Duke {
    private final ArrayList<Task> tasks;
    public Duke() {
        tasks = new ArrayList<>();
    }

    private static void validateComponentKeys(Set<String> expected, Set<String> actual) throws InvalidTaskComponents {
        if (expected.size() != actual.size()) {
            throw new InvalidTaskComponents(actual, expected);
        }

        for (String component : expected) {
            if (!actual.contains(component)) {
                throw new InvalidTaskComponents(actual, expected);
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

    public Task addTask(String type, String data) throws InvalidTaskComponents, InvalidTaskType {
        Map<String, String> components = parseComponents(data);
        Task task;
        switch (type) {
            case "todo":
                validateComponentKeys(new HashSet<String>(List.of("DESCRIPTION")), components.keySet());
                task = new Todo(components.get("DESCRIPTION"));
                break;
            case "deadline":
                validateComponentKeys(new HashSet<String>(List.of("DESCRIPTION", "/by")), components.keySet());
                task = new Deadline(components.get("DESCRIPTION"), components.get("/by"));
                break;
            case "event":
                validateComponentKeys(new HashSet<String>(List.of("DESCRIPTION", "/from", "/to")), components.keySet());
                task = new Event(components.get("DESCRIPTION"), components.get("/from"), components.get("/to"));
                break;
            default:
                throw new InvalidTaskType(type);
        }
        this.tasks.add(task);
        return task;
    }

    public Task getTask(int index) throws TaskNotFound {
        try {
            return tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotFound("Could not find task " + (index + 1), e);
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            s.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return s.toString();
    }

    public static class TaskNotFound extends Exception {
        public TaskNotFound(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static class InvalidTaskType extends Exception {
        public InvalidTaskType(String type) {
            super("Invalid task type: " + type);
        }
    }

    public static class InvalidTaskComponents extends Exception {
        public InvalidTaskComponents(Set<String> actual, Set<String> expected) {
            super("Invalid task components: " + actual + "; expected: " + expected);
        }
    }
}
