package dylanbot;

public class Task {

    public String type, desc;
    public boolean completed;

    public Task(String type, String desc) {
        this.type  = type;
        this.desc = desc;
        this.completed = false;
    }

    public String toString() {
        return "[" + type + "] "
                + (completed ? "[X]" : "[ ]")
                + " " + desc;
    }

}

