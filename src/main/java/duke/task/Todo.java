package duke.task;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(Map<String, String> components) throws InvalidComponents {
        this(components.get("DESCRIPTION"));
        validateComponentKeys(keys(), components.keySet());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
