public class Deadline extends Task {
    private String date;
    public Deadline (String desc, String date) {
        super(desc);
        this.date = date;
    }
    public String toString() {
        return "[D]" + super.toString() + "(" + date + ")";
    }
}
