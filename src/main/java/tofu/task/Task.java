package tofu.task;

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

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof Task task) {
            return desc.equals(task.desc);
        } else {
            return false;
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
