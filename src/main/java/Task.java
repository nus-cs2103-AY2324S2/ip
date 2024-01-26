
public abstract class Task {
    private Boolean done;
    private String taskName;
    protected String identifier;

    public Task(String taskName, Boolean done) {
        this.taskName = taskName;
        this.done = done;
        this.identifier = "";
    }

    public void markDone() {
        done = true;
    }

    public void markUndone() {
        done = false;
    }

    public String getIdentifier() {
        return identifier;
    }

    @Override
    public String toString() {
        return String.format("[%s] [%s] %s",identifier,  done ? "X" : " ", taskName);
    }

    public String[] encode() {
        return new String[]{identifier, done.toString() , taskName};
    }

}


