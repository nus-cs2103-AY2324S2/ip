public class Task {
    private boolean completed;
    private String name;

    public Task(String name) {
        this.name = name;
        this.completed = false;
    }

    public void markDone() {
        if (this.completed) {
            System.out.println(Nicole.botName + ": " + this.name + " is already marked complete -_-");
        } else {
            this.completed = true;
            System.out.println(Nicole.botName + ": I marked " + this.name + " as completed. Good job :3");
        }
    }

    public void markUndone() {
        if (!this.completed) {
            System.out.println(Nicole.botName + ": " + this.name + " is already marked incomplete -_-");
        } else {
            this.completed = false;
            System.out.println(Nicole.botName + ": I marked " + this.name + " as incomplete.");
        }
    }

    @Override
    public String toString() {
        return this.completed ? "[x] " + this.name : "[] " + this.name;
    }

    public boolean equals(Task task) {
        return this.completed == task.completed;
    }
}
