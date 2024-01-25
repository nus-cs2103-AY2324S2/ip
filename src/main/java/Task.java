public class Task {
    private final String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String getName() {
        return this.name;

    }

    public String getIcon() {
        return (isDone ? "X" : " ");

    }

    public void mark() {
        this.isDone = true;

        String o = String.format("Nice! I've marked this task as done: \n [%s] %s", this.getIcon(), this.name);

        Utils.encaseLines(o);
    }

    public void unmark() {
        this.isDone = false;

        String o = String.format("OK, I've marked this task as not done yet: \n [%s] %s", this.getIcon(), this.name);

        Utils.encaseLines(o);
    }
}
