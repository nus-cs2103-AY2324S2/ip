public class Task {
    private String taskName;
    private boolean status;

    public Task(String taskName) {
        this.taskName = taskName;
        status = false;
    }

    public String getTaskName() {
        return taskName;
    }
    public String getStatusIcon() {
        return status ? "[X]" : "[ ]";
    }

    public void done() {
        status = true;
    }

    public void undone() {
        status = false;
    }
}

