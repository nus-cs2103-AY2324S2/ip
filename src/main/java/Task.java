public abstract class Task {
    private final String name;
    private boolean isDone;


    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String getName() {
        return this.name;

    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");

    }

    public abstract String getType();

    public abstract String getDesc();

    public void mark() {
        this.isDone = true;

        String o = String.format("Nice! I've marked this task as done: \n [%s] %s", getStatusIcon(), this.name);

        Utils.encaseLines(o);
    }

    public void unmark() {
        this.isDone = false;

        String o = String.format("OK, I've marked this task as not done yet: \n [%s] %s", getStatusIcon(), this.name);

        Utils.encaseLines(o);
    }
}
