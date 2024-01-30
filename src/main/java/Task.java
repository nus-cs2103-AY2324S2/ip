public class Task {
    private String name;
    private boolean completed;

    public Task(String name) {
        this.name = name;
        this.completed = false;
    }

    public void complete() {
        this.completed = true;
    }

    public void unmark() {
        this.completed = false;
    }

    public boolean isComplete() {
        return this.completed;
    }

    public String printTask() {
        String taskMessage = this.completed ? "[X]" : "[ ]";
        taskMessage += this.name;
        return taskMessage;
    }

    public String saveTask() {
        String taskMessage = this.completed ? "1" : "0";
        taskMessage += " |" + this.name;
        return taskMessage;
    }
}

