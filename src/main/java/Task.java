public class Task {
    private boolean completed;
    private String name;

    public Task() {
        this.completed = false;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Task taskFactory(String name, int taskType) throws NicoleException {
        if (taskType == 0) {
            return new Todo(name);
        } else if (taskType == 1) {
            return new Deadline(name);
        } else {
            return new Event(name);
        }
    }

    public void markDone() throws NicoleException {
        if (this.completed) {
            throw new NicoleException("That is already marked complete -_-");
        } else {
            this.completed = true;
            System.out.println(Nicole.botName + ": Marked as completed! Good job :3");
        }
    }

    public void markUndone() throws NicoleException {
        if (!this.completed) {
            throw new NicoleException("That is already marked incomplete -_-");
        } else {
            this.completed = false;
            System.out.println(Nicole.botName + ": Marked as incomplete. We'll get em next time");
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
