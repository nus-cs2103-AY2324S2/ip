package duke.task;

/**
 * The class representing a generic task.
 * */
public class Task {
    /* Description of the current task. */
    String description;

    /* Completion status of the current task. */
    boolean isDone;

    /* Mark reflecting completion status for the current task. */
    String display;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.display = "[ ]";
    }

    /**
     * Changes the display (completion status) from 'done' to 'undone' and vice-versa.
     * */
    public void changeDone() {
        this.isDone = !this.isDone;
        if (this.isDone) {
            this.display = "[X]";
        } else {
            this.display = "[ ]";
        }
    }

    /**
     * Getter method for task description.
     *
     * @return The task description.
     * */
    public String getDescription() {
        return description;
    }

    /**
     * Converts the task into the form to be stored in db.txt.
     *
     * @return The formatted string.
     * */
    public String toDbString() {
        return "";
    }
}
