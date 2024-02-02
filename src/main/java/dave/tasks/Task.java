package dave.tasks;

public class Task {
    /** Name or description of task. */
    protected String desc;
    /** Completion status of task. */
    protected Boolean completed;

    /**
     * Creates new Task object.
     * 
     * @param desc Name or description of Task object.
     */
    public Task(String desc) {
        this.desc = desc;
        this.completed = false;
    }

    public String getStatusIcon() {
        return (this.completed ? "X" : " "); // mark X if task is completed
    }

    public String getTaskName() {
        return this.desc;
    }

    public void isCompleted() {
        this.completed = true;
    }

    public void isNotCompleted() {
        this.completed = false;
    }

    /**
     * Formats the printing of the Task object when shown to user.
     * 
     * @return Printing of the Task object.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.desc);
    }

    /**
     * Formats the output of the Task object in output file.
     * 
     * @return The output to be written in the output file.
     */
    public String fileString() {
        return String.format("%s | %s", this.getStatusIcon().equals("X") ? "COMPLETED" : "INCOMPLETE", this.desc);
    }
}
