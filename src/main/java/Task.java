public class Task {
    private String task;
    private boolean isDone = false;
    private String taskType;

    public Task(String taskDesc) {
        this.task = taskDesc;
    }

    public Task(String taskDesc, String taskType) {
        this.task = taskDesc;
        this.taskType = taskType;
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

    public String getTaskType() {
        return "[" + this.taskType + "]";
    }

    public String announcement() {
        return "New task created!";
    }
}
