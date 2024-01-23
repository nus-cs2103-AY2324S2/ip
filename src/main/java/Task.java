public class Task {
    private String task;
    private boolean isDone = false;

    public Task(String taskDesc) {
        this.task = taskDesc;
    }

    public void mark(){
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String getStatus() {
        if (isDone) {
            return "[X]";
        } else {
            return "[]";
        }
    }

    public String getTask() {
        return this.task;
    }
}
