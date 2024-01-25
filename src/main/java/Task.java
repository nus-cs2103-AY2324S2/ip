/**
 * Task class
 * This class contains informations about the task.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Task Constructor
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * isCompleted Method
     * It is to set isDone to true.
     */
    public void isCompleted() {
        this.isDone = true;
    }

    /**
     * isNotCompleted Method
     * It is to set isDone to false.
     */
    public void isNotCompleted() {
        this.isDone = false;
    }

    /**
     * getStatusIcon Method
     * It is to check isDone and return X or " ".
     * @return X or " " depending on isDone.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * toString Method
     * @return a string that describe the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
