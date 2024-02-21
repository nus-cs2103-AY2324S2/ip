package anxi.tasks;

/**
 * ToDo task template.
 */
public class ToDo extends Task {

    /**
     * ToDo constructor.
     * @param description   Task name or description of task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * ToDo constructor.
     *
     * @param description   Task name or description of task.
     * @param isDone        Marks task as completed/uncompleted. [True: complete, False: uncompleted]
     */
    public ToDo(String description, boolean isDone) {
        super(description);
        super.updateIsDone(isDone);
    }

    /**
     * Formats ToDo as a string to be saved to file.
     * @return saveTask     Returns the task as a string in the format compatible with file.
     */
    @Override
    public String saveFileString() {
        return "T | " + super.saveFileString();
    }

    @Override
    public String toString() {
        return "[T][" + (isDone ? "X" : " ") + "] " + this.description;
    }
}
