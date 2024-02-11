package duke.task;

import duke.task.Task;
/**
 * This class is implementation of Task which contains description.
 */
public class ToDos extends Task {

    /**
     * Creates an instance of ToDos which contains description detail of the task.
     * @param description
     */
    public ToDos(String description) {
        super(description);
    }

    /**
     * Returns String of task detail with type of Task (Events) and description of the task.
     * @return
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
