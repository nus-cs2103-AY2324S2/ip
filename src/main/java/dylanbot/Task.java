package dylanbot;

public class Task {

    private String type, desc;
    private boolean completed;

    public Task(String type, String desc) {
        this.type  = type;
        this.desc = desc;
        this.completed = false;
    }

    public String getType() {
        return this.type;
    }

    public String getDesc() {
        return this.desc;
    }

    public boolean isCompleted() {
        return this.completed;
    }

    public void mark() {
        this.completed = true;
    }

    public void unmark() {
        this.completed = false;
    }

    public String toString() {
        return "[" + type + "] "
                + (completed ? "[X]" : "[ ]")
                + " " + desc;
    }

}

