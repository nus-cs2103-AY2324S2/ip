public class Task {

    protected String name;
    protected boolean isDone = false;

    public Task(String name) {
        this.name = name;
    }

    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    @Override
    public String toString() {
        String x = isDone ? "X" : " ";
        return "[" + x + "] " + name;
    }

    public String addToFile() {
        String isDone = this.isDone ? "1" : "0";
        return isDone + " | " + name;
    }

}
