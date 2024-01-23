public class Task {
    protected String description;
    protected boolean isDone;
    protected Character taskType;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.taskType = ' ';
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return description;
    }

    public void setAsNotDone() {
        isDone = false;
    }

    public void setAsDone() {
        isDone = true;
    }

    public Character getTaskType(){
        return this.taskType;
    }

    @Override
    public String toString(){
        return this.description;
    }
}
