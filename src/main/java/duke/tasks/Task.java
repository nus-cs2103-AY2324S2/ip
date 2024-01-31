package duke.tasks;

/**
 * Class represents a task.
 */
public class Task {
    private Boolean status;
    private String detail;

    /**
     * Initializes a Task object with given params.
     * @param status True for completed, False for not completed yet.
     * @param detail Detail information of the task.
     */
    public Task(Boolean status, String detail) {
        this.status = status;
        this.detail = detail;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    /**
     * Formats object to be stored in file.
     * @return Formatted string to be stored in file.
     */
    public String inFileStringFormat() {
        String intStatus = this.status? "1":"0";
        return intStatus + "|" + this.detail;
    }
    @Override
    public String toString() {
        if (this.status) {
            return "[X] " + this.detail;
        } else {
            return "[ ] " + this.detail;
        }
    }
}
