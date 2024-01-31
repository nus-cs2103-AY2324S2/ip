import java.io.Serializable;
public class Task implements Serializable{
    enum Status {
        DONE,
        NOT_DONE
    }
    protected String description;
    protected Status status;

    public Task(String description) {
        this.description = description;
        this.status = Status.NOT_DONE;
    }

    public String getStatusIcon() {
        return (status == Status.DONE ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
    public void setStatus(Task.Status status) {
        this.status = status;
    }

}