package duke.task;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * A task with no extra information.
 */
public class Todo extends Task {
    /**
     * Constructs a task
     * @param description description of the task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Construct a task with a start and an end from parsed components. The expected components are only DESCRIPTION.
     * @param components components parsed from the user command.
     * @throws InvalidComponents when the parsed components don't match the expected components
     */
    public Todo(Map<String, String> components) throws InvalidComponents {
        this(components.get("DESCRIPTION"));
        validateComponentKeys(keys(), components.keySet());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
