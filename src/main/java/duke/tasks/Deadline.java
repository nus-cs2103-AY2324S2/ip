package duke.tasks;

public class Deadline extends Task {
    protected DateTask by;

    public Deadline(String description, String by) {
        this(description, by, false);
    }

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = new DateTask(by);
    }

    @Override
    public String saveFormat() {
        return String.format("%s;;%s;;%s",
                "D",super.saveFormat(), by.saveFormat());
    }

    @Override
    public String toString() {
        return String.format("%s%s%s",
                "[D]",
                super.toString(),
                " (by: " + by + ")");
    }
    
}
