public class Deadlines extends Task{
    private String date;
    public Deadlines(String task, String date) {
        super(task);
        this.date = date;
    }

    @Override
    public String getTag() {
        return "[D]";
    }
}
