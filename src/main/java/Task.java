public class Task {
    private String name;
    private boolean isDone;
    private String type;
    private String[] times;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public void changeIsDone() {
        this.isDone ^= true;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getType() {
        return this.type;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public String[] getTimes() {
        return this.times;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.name;
    }
}
