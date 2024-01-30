package Tasks;

public abstract class Task {
    private String name;
    private Boolean done;
    protected String type;

    protected Task(String name) {
        this.name = name;
        this.done = false;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return type;
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

    public String toString() {
        String type = "[" + this.getType() + "]";
        String mark = "[" + (this.isDone() ? "X" : " ") + "]";
        return type + mark + " " + this.getName();
    }
}
