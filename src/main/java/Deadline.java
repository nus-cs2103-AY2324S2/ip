public class Deadline extends Task{
    private String date;

    public Deadline(String task, String date) {
        super(task);
        this.date = date;
    }

    @Override
    public String printTask() {
        return "[D]" + super.printTask() + " (by: " + this.date + ")";
    }

    @Override
    public String toString() {
        return String.format("D | %s | %s", super.toString(), this.date);
    }
}
