public class Task {
    String description;
    boolean isDone;
    boolean isTodo;
    boolean isDeadline;
    boolean isEvent;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getStatusIcon1() {
        if (isTodo) {
            return "T";
        } else if (isDeadline) {
            return "D";
        } else {
            return "E";
        }
    }

    public String getStatusIcon2() {
        return (isDone ? "X" : " ");
    }
}

