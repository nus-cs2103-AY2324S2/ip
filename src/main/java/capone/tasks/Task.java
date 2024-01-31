package capone.tasks;

public abstract class Task {
    protected enum TaskType {
        TODO("todo"), DEADLINE("deadline"), EVENT("event");

        private String taskName;

        TaskType(String name) {
            this.taskName = name;
        }

        @Override
        public String toString() {
            return this.taskName;
        }
    }
    protected TaskType taskType;
    protected String description;
    protected boolean isDone;

    public Task(TaskType type, String description, boolean status) {
        this.taskType = type;
        this.description = description;
        this.isDone = status;
    }

    public String getTaskType() {
        return this.taskType.toString();
    }

    public String getDescription() {
        return this.description;
    }

    public Boolean getStatus() {
        return this.isDone;
    }

    public void markTask() {
        this.isDone = true;
    }

    public void unmarkTask() {
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}
