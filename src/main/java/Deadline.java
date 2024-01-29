public class Deadline extends Task {
    private String date;
    public Deadline(String task, String date) {
        super(task);
        this.date = date;
    }

    public Deadline(String task, String date, boolean status) {
        super(task, status);
        this.date = date;
    }

    @Override
    public String stringify() {
        return "[D]" + super.stringify() + "(by: " + this.date + ")";
    }

    @Override
    public String toString() {
        return "D | " + super.toString() + " | " + date;
    }

}
