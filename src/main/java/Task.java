import java.time.LocalDate;


public class Task {
    protected String name;
    protected boolean isDone;

    public Task(String name, Boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public String saveOutput() {
        return String.format("| %d | %s", isDone ? 1 : 0, name);
    }

    public void happenOn(LocalDate date) {
    }

    public void taskInfo() {
        if (isDone) {
            System.out.print("[X]");
        } else {
            System.out.print("[ ]");
        }
        System.out.print(" " + name);
    }

    public void mark() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        this.taskInfo();
    }

    public void unmark() {
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:");
        this.taskInfo();
    }
}