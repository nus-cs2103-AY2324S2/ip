public class Task {
    private String taskName;
    protected boolean status;

    public Task(String taskName, boolean status) {
        this.taskName = taskName;
        this.status = status;
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

    @Override
    public String toString() {
        return getStatusIcon() + " " + taskName;
    }

    public String toStore() {
        return "," + Boolean.toString(status) +  "," + taskName;
    }
}

