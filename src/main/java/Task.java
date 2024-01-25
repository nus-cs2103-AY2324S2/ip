public class Task {
    private final String  name;
    private boolean isDone = false;
    public Task(String x){
        name = x;

    }
    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }
    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + name;
    }

}
