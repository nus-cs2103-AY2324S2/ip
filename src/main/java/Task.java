public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    public void mark(){
        this.isDone = true;
    }
    public void unmark(){
        this.isDone = false;
    }
    @Override
    public String toString(){
        String output = "[" + this.getStatusIcon() + "] " + this.description;
        return output;
    }
}
