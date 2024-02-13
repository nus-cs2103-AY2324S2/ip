package lite.task;

public class Deadline extends Task {
    private String due;

    public Deadline(String description, String due) {
        super(description);
        this.due = due;
    }

    /**
     * {@inheritDoc}
     *
     * Date is displayed in the format: Monday 2pm
     *
     */
    @Override
    public String toString() {
        String dueSplit[] = due.split(" ", 2);
        return "[D]" + super.toString() + " (" + dueSplit[0] + ": " + dueSplit[1] + ")";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String saveToFile() {
        return "D!" + super.saveToFile() + "!" + due + "\n";
    }
}
