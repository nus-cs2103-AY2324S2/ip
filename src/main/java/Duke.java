import java.util.*;

public class Duke {
    private ArrayList<Task> tasks;

    public Duke() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    private static void validateComponents(Set<String> expected, Set<String> actual) throws InvalidTaskComponents {
        if (expected.size() != actual.size()) {
            throw new InvalidTaskComponents(actual, expected);
        }

        for (String component : expected) {
            if (!actual.contains(component)) {
                throw new InvalidTaskComponents(actual, expected);
            }
        }
    }

    public void addTask(String type, Map<String, StringBuilder> components) throws InvalidTaskComponents, InvalidTaskType {
        Task task;
        switch (type) {
            case "todo":
                validateComponents(new HashSet<String>(List.of("DESCRIPTION")), components.keySet());
                task = new Todo(components.get("DESCRIPTION").toString());
                break;
            case "deadline":
                validateComponents(new HashSet<String>(List.of("DESCRIPTION", "/by")), components.keySet());
                task = new Deadline(components.get("DESCRIPTION").toString(), components.get("/by").toString());
                break;
            case "event":
                validateComponents(new HashSet<String>(List.of("DESCRIPTION", "/from", "/to")), components.keySet());
                task = new Event(components.get("DESCRIPTION").toString(), components.get("/from").toString(), components.get("/to").toString());
                break;
            default:
                throw new InvalidTaskType(type);
        }
        this.tasks.add(task);
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
