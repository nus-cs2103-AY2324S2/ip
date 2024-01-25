public class Task {
    private final String desc;
    private boolean isDone;

    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }
    
    Task(String desc) {
        this.desc = desc;
        isDone = false;
    }

    public String toString() {
        return "[" + (isDone ? 'X' : ' ') + "] " + desc;
    }
}
