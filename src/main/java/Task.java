public class Task {
    private String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    @Override
    public String toString() {
        String str = "";
        if (isDone) {
            str = String.format("[X] %s", name);
        } else {
            str = String.format("[ ] %s", name);
        }
        return str;
    }

    public void mark() {
        isDone = true;
        System.out.println("Good job! You have completed this task:");
        System.out.println(toString());
    }

    public void unmark() {
        isDone = false;
        System.out.println("Alright. This task has been unmarked");
        System.out.println(toString());
    }
}
