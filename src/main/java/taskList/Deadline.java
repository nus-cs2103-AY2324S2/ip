package taskList;

public class Deadline extends Task {

    protected String byDate;

    public Deadline(String item, String byDate) {
        super(item);
        try {
            String[] dateString = byDate.split(" ", 2);
            this.byDate = dateString[0].trim() + ": " + dateString[1].trim();
         } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("Please enter a date");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (" + byDate + ")";
    }
}
