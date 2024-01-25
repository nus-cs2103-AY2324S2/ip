public class Task {

    private String description;
    private boolean isComplete = false;
    private char type;

    public Task(String desc) {
        this.description = desc;
    }

    public Task(String desc, char type) {
        this.description = desc;
        this.type = type;
    }

    public void mark(boolean b) {
        this.isComplete = b;
    }
    @Override
    public String toString() {
        String completion = isComplete ? "[X]" : "[ ]";
        return completion + description;
    }
}
