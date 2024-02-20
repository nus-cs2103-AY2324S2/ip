package duke.tasks;

/**
 * Class represents a task.
 */
public class Task {
    private Boolean isDone;
    private String detail;

    /**
     * Initializes a Task object with given params.
     *
     * @param isDone True for completed, False for not completed yet.
     * @param detail Detail information of the task.
     */
    public Task(Boolean isDone, String detail) {
        this.isDone = isDone;
        this.detail = detail;
    }

    public Boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(Boolean isDone) {
        this.isDone = isDone;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    /**
     * Formats object to be stored in file.
     *
     * @return Formatted string to be stored in file.
     */
    public String inFileStringFormat() {
        String intStatus = this.isDone ? "1" : "0";
        return intStatus + "|" + this.detail;
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.detail;
        } else {
            return "[ ] " + this.detail;
        }
    }
}
