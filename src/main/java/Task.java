public class Task {
    protected String name;
    protected boolean status;

    public Task(String name) {
        this.name = name;
        this.status = false;
    }

    public void taskInfo() {
        if (status) {
            System.out.print("[X]");
        } else {
            System.out.print("[ ]");
        }
        System.out.print(" " + name);
    }

    public void mark() {
        this.status = true;
        System.out.println("Nice! I've marked this task as done:");
        this.taskInfo();
    }

    public void unmark() {
        this.status = false;
        System.out.println("OK, I've marked this task as not done yet:");
        this.taskInfo();
    }
}