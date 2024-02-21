package duke.tasks;

import duke.common.Utils;

/**
 * The ToDo class represents a task with a description only.
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    /**
     * Converts the tasks's status and description into a formatted string for storage.
     * 
     * @return The method is returning a string representation of the task for storage purposes. The
     *         string is formatted as "todo~statusValue~description", where statusValue is either 1 or 0
     *         depending on the status of the task, and description is the description of the task.
     */
    @Override
    public String toStorageString() {
        int statusValue = this.getStatus() ? 1 : 0;
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("todo~%d~%s", statusValue, this.description));

        for (String tag : this.getTags()) {
            sb.append("~").append(tag);
        }

        return sb.toString();
    }

    /**
     * Returns a string representation of a ToDo with its status and description.
     * 
     * @return Returns a string representation of a Todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
