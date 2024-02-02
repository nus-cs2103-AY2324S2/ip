package pingmebot.task;

/**
 * A category of task that has a task description.
 */
public class ToDos extends Task {
    protected String description;

    /**
     * Creates a Deadline object with a specified task description.
     *
     * @param description Task's description.
     */
    public ToDos(String description) {
        super(description);
        this.description = description;
    }

    /**
     * Returns a string representation of the ToDos task.
     *
     * @return A string representation of the ToDos task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }


    /**
     * Returns a string representation that is saved to the local file whenever a ToDos task is completed.
     *
     * @param isCompleted Integer boolean flag to indicate if the task is completed.
     * @return A string representation when a ToDos task is completed.
     */
    public String updateToDoText(int isCompleted) {
        String text = "";
        text += "todo | " + isCompleted + " | " + this.description;
        return text;
    }

    /**
     * Returns true only when 2 objects have the same description.
     *
     * @param obj The object that is being compared to.
     * @return A boolean to indicate if 2 objects have the same description.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        ToDos otherToDo = (ToDos) obj;
        return this.description.equals(otherToDo.description);
    }
}
