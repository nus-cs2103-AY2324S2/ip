package cat.task;

import java.util.Map;

/**
 * A task with no extra information.
 */
public class Todo extends Task {
    /**
     * Construct a task with a start and an end from parsed components. The expected components are only DESCRIPTION.
     * @throws InvalidComponents when the parsed components don't match the expected components
     */
    public Todo(Map<String, String> components) throws InvalidComponents {
        super(components.get("DESCRIPTION"));
        validateComponentKeys(keys(), components.keySet());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
