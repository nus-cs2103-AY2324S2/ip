public class Task {
    String task;
    boolean completed;
    public Task (String task) {
        this.task = task;
        this.completed = false;
    }

    // To mark the task as completed
    public void mark() {
        this.completed = true;
    }

    // To mark the task as uncompleted
    public void unmark() {
        this.completed = false;
    }

    public String add(){
        return this.task;
    }
    @Override
    public String toString() {
        if (completed) {
            return "[X] " + this.task;
        } else {
            return "[ ] " + this.task;
        }
    }
}
