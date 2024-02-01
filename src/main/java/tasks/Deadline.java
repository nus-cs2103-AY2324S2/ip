package tasks;

public class Deadline extends Task {
    private final String date;
    public Deadline(String description, String date) {
        super(description, 'D');
        this.date = date;
    }

    public Deadline(String description, Boolean isDone, String date) {
        super(description, isDone,'D');
        this.date = date;
    }
    @Override
    public String toString() {

        return super.toString() + String.format(" (by: %s)\n", this.date);
    }

    @Override
    public String prepareStore() {
        return super.prepareStore() + String.format(" | %s", this.date);
    }
}