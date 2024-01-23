public class Task {
    // Task number.
    protected int n = 0;
    // Task name
    protected String name = "";
    // Whether the task is completed.
    protected boolean isDone = false;

    // Constructor for Task
    public Task(int n, String name) {
        this.n = n;
        this.name = name;
    }

    public void markDone() {
        isDone = true;
        System.out.println("Oink! You have completed this task! Nice nice nice\n    [x] " + this.name);
    }

    public void markUndone() {
        isDone = false;
        System.out.println("Oink! You have unmarked this task! Why why why\n    [ ] " + this.name);
    }

    public void printTaskList() {
        if (isDone) {
            System.out.println(n + ". [x] " + this.name);
        } else System.out.println(n + ". [ ] " + this.name);
    }

    public void printAddTask() {
        System.out.println("Oink! You have added a new task:\n    [ ]" + this.name);
    }
}
