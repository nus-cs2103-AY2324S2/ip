package megatron.task;

/**
 * Abstract class for task types
 */
public abstract class Task {
    /** Name or description of given task */
    protected String name;
    /** Denotes if task is completed or not */
    protected boolean isDone;

    /**
     * Constructor, initialised as not done
     * @param name as name of task
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
        assert !name.isEmpty() : "Name cannot be empty";
    }

    /**
     * Returns alphabetical code for given task
     *
     * @return "E" for Event, "D" for Deadline, "T" for ToDo
     */
    public abstract String getType();

    /**
     * Returns completion status icon for given task
     *
     * @return "X" if completed else " "
     */
    protected String getStatusIcon() {
        return (this.isCompleted() ? "X" : " ");
    }

    /**
     * Returns boolean completion status for given task
     *
     * @return True if task is completed
     */
    public boolean isCompleted() {
        return this.isDone;
    }

    /**
     * Marks that task is complete
     */
    public void setMarked() {
        this.isDone = true;
    }

    /**
     * Unmark the task as not complete
     */
    public void setNotMarked() {
        this.isDone = false;
    }

    /**
     * Returns task description
     *
     * @return name of task
     */
    public String getDetails() {
        return this.name;
    }
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.name;
    }
}
