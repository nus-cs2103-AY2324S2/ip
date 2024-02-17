package cat.task;

import java.util.Map;

/**
 * A task with no extra information.
 */
public class Todo extends Task {
    /**
     * Construct a task with a start and an end from parsed components. There are no expected components.
     * @throws InvalidComponents When the parsed components don't match the expected components.
     */
    public Todo(String description, Map<String, String> components) throws InvalidComponents {
        super(description);
        validateComponentKeys(keys(), components.keySet());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
