public class Deadline extends Task{
    String date;
    public Deadline(String description, String date, boolean isDone) {
        super(description);
        isDeadline = true;
        this.date = date;
        this.isDone = isDone;
    }

    public String getDate() {
        return date;
    }
}
