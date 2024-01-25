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

    private String curStatus() {
        return "[" + (isDone ? 'X' : ' ') + "]";
    }

    public String toString() {
        return curStatus() + ' ' + desc;
    }
}
