public class Task {
    private String taskName;
    private boolean taskDone = false;

    public Task(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public boolean isTaskDone() {
        return this.taskDone;
    }

    public String getStatusIcon() {
        return (this.taskDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.taskName;
    }

    public void changeStatus(String action) {
        if (action.equals("mark")) {
            this.taskDone = true;
        } else {
            this.taskDone = false;
        }
        MessagePrinter.markActionPrint(action, this);
    }



}
