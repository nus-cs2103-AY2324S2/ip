package taskList;

public class Deadline extends Task {

    protected String byDate;

    public Deadline(String item, String byDate) {
        super(item);
        String[] dateString = byDate.split(" ");
        this.byDate = dateString[0].trim() + ": " + dateString[1].trim();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (" + byDate + ")";
    }
}
