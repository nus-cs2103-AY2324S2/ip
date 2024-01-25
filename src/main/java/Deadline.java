public class Deadline implements Task {
    private final String name;
    private final boolean done;
    private final String deadline;

    Deadline(String name, String deadline) {
        this.name = name;
        this.done = false;
        this.deadline = deadline;
    }

    private Deadline(String name, boolean done, String deadline) {
        this.name = name;
        this.done = done;
        this.deadline = deadline;
    }

    public Deadline mark() {
        return new Deadline(this.name, true, this.deadline);
    }

    public Task unmark() {
        return new Deadline(this.name, false, this.deadline);
    }

    @Override
    public String toString() {
        return "[D][" + (this.done?"X":" ") + "] " + this.name +
            " (by: " + this.deadline + ")";
    }
}
