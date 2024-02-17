package duke.task;

import duke.exception.DukeException;
import duke.ui.Ui;

/**
 * The ToDo class represents a task of type ToDo in the Duke application.
 * It is a subclass of the Task class.
 */
public class ToDo extends Task {
    private Ui ui;
    /**
     * Constructs a ToDo task with the specified description.
     *
     * @param description The description of the ToDo task.
     * @param priority The priority of the task.
     * @throws DukeException If there is an issue with the task creation.
     */
    public ToDo(String description, Ui ui, TaskPriority priority) throws DukeException {
        super(TaskType.T, description, priority);
        this.ui = ui;
        assert this.ui != null : "UI should not be null.";
    }

    /**
     * Returns a string representation of the ToDo task.
     *
     * @return A string representation of the ToDo task.
     */
    public String getMessage() {
        assert this.ui != null : "UI should not be null.";
        String priorityFormatted = (priority != null) ? " [Priority: " + priority + "]" : "";
        return ui.printMessage("Got it. I've added this task: \n [T][" + getStatusIcon() + "] " + getDescription()
                + priorityFormatted);
    }
}
