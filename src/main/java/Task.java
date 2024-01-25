public class Task {
    private String description;
    private boolean isDone;
    private int type;

    public Task(String description, int type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    public String toggleIsDone() {
        isDone = !isDone;
        return getStatusIcon();
    }
    public String getType() {
        if (type == 0) {
            return "T";
        } else if (type ==1) {
            return "E";
        } else if (type == 2) {
            return "D";
        } else {
            return "None";
        }
    }
    @Override
    public String toString(){
        return description;
    }
    //...
}
