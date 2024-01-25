public class Task {
    public enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }
    protected String description;
    protected boolean isDone;
    protected TaskType taskType;

    public Task(String description, TaskType taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;

    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    //function to mark as done
    public void markAsDone() {
        this.isDone = true;
    }
    //function to unmark
    public void unMark(){
        this.isDone = false;
    }
    public String toString(){
        return "["+ this.getStatusIcon() +"] " + this.description;
    }

    //...
}

