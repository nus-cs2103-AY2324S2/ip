public class Task {
    protected String taskDescription;
    protected boolean isCompleted;
    protected TaskType taskType;
    public Task(String taskDescription, TaskType taskType) {
        this.taskDescription = taskDescription;
        this.isCompleted = false;
        this.taskType = taskType;
    }
    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }
    public void markAsComplete() {
        this.isCompleted = true;
    }
    public void markAsIncomplete() {
        this.isCompleted = false;
    }

    public String getTaskDescription() {
        return taskDescription;
    }
    public String getStatusIcon() {
        return (isCompleted ? "X" : " ");
    }
    public String getTaskType() {
        String type = taskType.toString();
        if (type.equalsIgnoreCase("todo")) {
            return "T";
        } else if (type.equalsIgnoreCase("event")) {
            return "E";
        } else if (type.equalsIgnoreCase("deadline")) {
            return "D";
        } else {
            return "";
        }
    }
}
