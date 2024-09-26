package maltese.task;

import maltese.action.Echo;
import maltese.action.TaskList;
import maltese.exception.DuplicateTaskException;
import maltese.exception.EmptyDescriptionException;
import maltese.exception.MalteseException;

/**
 * Represents a task of type ToDo in the maltese application.
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
    /**
     * Parses the command for adding a deadline task.
     *
     * @param command  The command string containing the description and deadline of the task.
     * @param taskList The TaskList containing tasks to which the deadline task will be added.
     * @return An Echo action representing the response message after adding the todo task.
     * @throws MalteseException If the command is invalid or if there are errors in parsing or adding the task.
     */
    public static Echo parse(String command, TaskList taskList) throws MalteseException {
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

