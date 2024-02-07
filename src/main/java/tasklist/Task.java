package tasklist;
public class Task {
    private String description;
    private boolean isDone;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public Task(String description ,boolean isDone ) {
        this.description = description;
        this.isDone = isDone;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    public String toggleIsDone() {
        isDone = !isDone;
        return getStatusIcon();
    }
    public String saveStorage() {
        String storageIsDone = "0";
        if (isDone) {
            storageIsDone = "1";
        }
        String details = storageIsDone + "|" + description;
        return details;
    }
    @Override
    public String toString(){
        return "["+getStatusIcon()+"]"+ description;
    }
    //...
}
