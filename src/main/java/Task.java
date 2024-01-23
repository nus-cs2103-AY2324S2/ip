public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public String getDescription() {
        return this.description;
    }

    public void updateTask(boolean status) {
        if (status == true){
            isDone = true;
        } else {
            isDone = false;
        }
    }
}
