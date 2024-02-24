package lucky.tasks;

/**
 * The ToDo class represents a task with a description only.
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    /**
     * Converts the tasks' status and description into a formatted string for storage.
     *
     * @return A string representation of the task for storage purposes. The
     *         string is formatted as "todo~statusValue~description", where statusValue is either 1 or 0
     *         depending on the status of the task, and description is the description of the task.
     */
    @Override
    public String toStorageString() {
        int statusValue = this.getIsMarked() ? 1 : 0;
        assert statusValue == 0 || statusValue == 1 : "Status value must be equal to 0 or 1";

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
     * @return A string representation of a Todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
