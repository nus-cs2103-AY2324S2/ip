public class Task {
    private boolean isDone = false;
    private String name = "";

    public Task(String name) {
        this.name = name;
    }

    public void complete() {
        isDone = true;
        System.out.println("It's about time you got this done. ");
    }

    public void incomplete() {
        isDone = false;
        System.out.println("Stop dragging your heels on it!");
    }
}
