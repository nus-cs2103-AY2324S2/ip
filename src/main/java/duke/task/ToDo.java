package duke.task;

import duke.exception.DukeException;

/**
 * The ToDo class represents a task of type ToDo in the Duke application.
 * It is a subclass of the Task class.
 */
public class ToDo extends Task {
    /**
     * Constructs a ToDo task with the specified description.
     *
     * @param description The description of the ToDo task.
     * @throws DukeException If there is an issue with the task creation.
     */
    public ToDo(String description) throws DukeException {
        super(TaskType.T, description);
    }

    /**
     * Returns a string representation of the ToDo task.
     *
     * @return A string representation of the ToDo task.
     */
    @Override
    public String toString() {
        return "Got it. I've added this task: \n [T][" + getStatusIcon() + "] " + getDescription();
    }
}