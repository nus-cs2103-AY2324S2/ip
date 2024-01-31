package duke.task;

/**
 * The class representing a generic task.
 * */
public class Task {
    String description;
    boolean done;
    String display;

    public Task(String description) {
        this.description = description;
        this.done = false;
        this.display = "[ ]";
    }

    /**
     * Changes the display (completion status) from 'done' to 'undone' and vice-versa.
     * */
    public void changeDone() {
        this.done = !this.done;
        if (this.done) {
            this.display = "[X]";
        } else {
            this.display = "[ ]";
        }
    }

    /**
     * Converts the task into the form to be stored in db.txt.
     *
     * @return The formatted string.
     * */
    public String toDBString() {
        return "";
    }
}
