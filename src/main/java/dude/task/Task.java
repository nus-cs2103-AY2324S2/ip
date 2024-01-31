package dude.task;

public abstract class Task {
    private boolean done = false;
    private String name;

    public Task(String name) {

        this.name = name;
    }

    public Task(String name, boolean done) {

        this.name = name;
        this.done = done;
    }
    public String getStorageString() {
        return String.format("%s | %s", this.done ? "1" : "0", this.name);
    };

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    public boolean contains(String keyword) {
        return this.name.contains(keyword);
    }

    @Override
    public String toString() {
        String checkbox = "[" + (this.done ? "x" : " ") + "] ";
        return checkbox + this.name;
    }
}
