package duke.task;

import duke.action.Echo;
import duke.action.TaskList;
import duke.exception.DukeException;
import duke.exception.DuplicateTaskException;
import duke.exception.EmptyDescriptionException;

/**
 * Represents a task of type ToDo in the Duke application.
 */

public class ToDo extends Task {
    private static final int TODO_START_INDEX = 5;

    /**
     * Constructs a ToDo task with the specified description.
     *
     * @param description The description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Gets the type code representing the task type.
     *
     * @return A string representing the task type code.
     */
    public String getType() {
        return "T";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ToDo) {
            ToDo toDo = (ToDo) obj;
            return this.getDescription().equals(toDo.getDescription());
        }
        return false;
    }

    public static Echo parse(String command, TaskList taskList) throws DukeException {
        String[] words = command.split(" ");
        if (words.length > 1) {
            String description = command.substring(TODO_START_INDEX).trim();
            if (description.isEmpty()) {
                throw new EmptyDescriptionException();
            }
            ToDo todo = new ToDo(description);
            if (taskList.contains(todo)) {
                throw new DuplicateTaskException();
            } else {
                taskList.addTask(todo);
                return new Echo("Got it. I've added this task:\n  " + todo + "\nNow you have "
                        + taskList.size() + " tasks in the list.");
            }
        } else {
            throw new EmptyDescriptionException();
        }
    }


    /**
     * Converts the task to a string representation.
     *
     * @return A formatted string representing the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

