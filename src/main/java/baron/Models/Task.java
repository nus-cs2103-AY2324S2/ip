package baron.Models;

public class Task {

    private long id;
    private final String name;
    private boolean isDone;

    public Task (String name) {
        this.name = name;
        this.isDone = false;
    }

    public Task (String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public Task (int id, String name, boolean isDone) {
        this.id = id;
        this.name = name;
        this.isDone = isDone;
    }

    public long getId () {
        return this.id;
    }

    public void setId (long id) {
        this.id = id;
    }

    @Override
    public String toString () {
        String done = isDone ? "X" : " ";
        return "[" + done + "] " + name;
    }

    public String toDataString () {
        String done = this.isDone() ? "1" : "0";
        return done + " | " + this.getName();
    }

    public boolean isDone () {
        return isDone;
    }

    public String getName () {
        return name;
    }

    public void setDone (boolean isDone) {
        this.isDone = isDone;
    }
}
