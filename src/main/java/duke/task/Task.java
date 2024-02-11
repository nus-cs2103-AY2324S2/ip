package duke.task;

public class Task {
    private String desc;
    private boolean isDone;

    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }

    public Task(String desc, boolean isDone) {
        this.desc = desc;
        this.isDone = isDone;
    }

    public boolean equals(Task task) {
        if (task == this) {
            return true;
        } else {
            return task.desc == desc && task.isDone == isDone;
        }
    }

    public boolean contains(String keyword) {
        return desc.contains(keyword);
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String toStore() {
        // need to store status as well
        return isDone + " | " + desc;
    }

    public String toString() {
        String s = "[ ]";
        if (isDone) {
            s = "[X]";
        }
        return  s + " " + desc;
    }
}
