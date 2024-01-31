package Duke.Task.ToDos;

import Duke.Task.Task;

/**
 * This class contains the functions for todos tasks.
 * @author Tang Hao Liang
 */
public class ToDos extends Task {
    /**
     * Constructor that updates description for the task.
     *
     * @param description Todos' description.
     */
    public ToDos(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "  [T]" + super.toString();
    }

    @Override
    public String toFile() {
        if (isDone) {
            return "T|1|" + description;
        } else {
            return "T|0|" + description;
        }
    }
}
