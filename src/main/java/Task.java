public class Task {
    private String name;
    private Boolean done;

    Task(String name) {
        this.name = name;
        this.done = false;
    }

    public String getName() {
        return this.name;
    }

    public Boolean isDone() {
        return this.done;
    }

    public void setDone() {
        this.done = true;
    }

    public void setUndone() {
        this.done = false;
    }
}
