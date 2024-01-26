public class Task {
    private final String name;
    private boolean isDone;


    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");

    }

    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.name);
    };


    public void mark() {
        this.isDone = true;

        String o = String.format("Nice! I've marked this task as done: \n   %s",  this.toString());

        Utils.encaseLines(o);
    }

    public void unmark() {
        this.isDone = false;

        String o = String.format("OK, I've marked this task as not done yet: \n   %s", this.toString());

        Utils.encaseLines(o);
    }
}
