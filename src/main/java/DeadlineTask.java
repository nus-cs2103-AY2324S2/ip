public class DeadlineTask extends Task {
    private String by;

    public DeadlineTask(String task) {
        super(task);
        parseDeadline(task);
    }

    private void parseDeadline(String task) {
        String[] split = task.split("/by", 2);

        if (split.length == 2) {
            this.task = split[0];
            this.by = split[1].trim();
        }
    }

    @Override
    public String tag() {
        return "[D]";
    }

    @Override
    public String toString() {
        return tag() + mark() + " " + task + " (by: " + by + ")";
    }
}