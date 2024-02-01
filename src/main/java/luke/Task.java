import java.io.Serializable;

public class Task implements Serializable {
    protected boolean isDone = false;
    protected String name = "";

    public Task(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isDone() {
        return isDone;
    }

    public void complete() {
        isDone = true;
        //System.out.println("It's about time you got this done. ");
    }

    public void incomplete() {
        isDone = false;
        //System.out.println("Stop dragging your heels on it!");
    }

    public String fullStatus() { //TODO: add type of task later
        String checkbox;
        if (isDone) {
            checkbox = "[X] ";
        } else {
            checkbox = "[ ] ";
        }
        return checkbox + name;
    }

    @Override
    public String toString() {
        return name;
    }
}
