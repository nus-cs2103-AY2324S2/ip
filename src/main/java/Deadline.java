public class Deadline extends Task{
    String date;
    public Deadline(String description, String date) {
        super(description);
        isDeadline = true;
        this.date = date;
    }
}
