import java.util.*;

class Task {
    protected final String taskname;
    boolean is_marked;

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

    String added(int i) {
        return "   added: " + this.taskname;
    }

    public String toString() {
        return "   " + this.getStatusIcon() + " " + this.taskname;
    }
}