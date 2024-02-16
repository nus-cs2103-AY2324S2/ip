package rochin;

public class DeadlineTask extends Task {
    protected String by;

    public DeadlineTask(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Return a new deadline task.
     *
     * @param descriptionWithDate description with date.
     * @return A new deadline task.
     */
    public DeadlineTask createTask(String descriptionWithDate) throws RochinException {
        String[] parts = descriptionWithDate.split("/by");
        if (parts.length == 2) {
            String description = parts[0].trim();
            String by = parts[1].trim();
            return new DeadlineTask(description, by);
        } else {
            throw new RochinException("OOPS!!! Please provide both a description and a deadline for a deadline task.");
        }
    }

    /**
     * Return a string representation of the task type.
     *
     * @return A string representation of the task type.
     */
    @Override
    public String getTaskType() {
        return "D";
    }

    /**
     * Return a string representation of the task.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
