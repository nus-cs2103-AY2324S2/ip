import java.util.*;

class Task {
    private final String taskname;
    private boolean is_marked;

    Task(String str) {
        this.taskname = str;
        this.is_marked = false;
    }

    void marked() {
        this.is_marked = true;
    }

    void unmarked() {
        this.is_marked = false;
    }

    String getStatusIcon() {
        return (is_marked ? "[X]" : "[ ]");
    }

    String added() {
        return "   added: " + this.taskname;
    }

    public String toString() {
        return "   " + this.getStatusIcon() + " " + this.taskname;
    }
}