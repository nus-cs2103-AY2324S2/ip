package model;
public class ToDo implements Task {
    private final String name;
    private final boolean done;

    ToDo(String name) {
        this.name = name;
        this.done = false;
    }

    private ToDo(String name, boolean done) {
        this.name = name;
        this.done = done;
    }

    public ToDo mark() {
        return new ToDo(this.name, true);
    }

    public ToDo unmark() {
        return new ToDo(this.name, false);
    }

    @Override
    public String toString() {
        String d = this.done ? "X" : " ";
        return String.format("[T][%s] %s", d, name);
    }
}
