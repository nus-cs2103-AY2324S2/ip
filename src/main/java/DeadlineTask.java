public class DeadlineTask extends Task {
    public String date_time;
    public DeadlineTask(String taskname) {
        super(taskname);
        this.date_time = "";
    }
    public DeadlineTask(String taskname, String date_time) {
        super(taskname);
        this.date_time = date_time;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() +
                String.format("(by: %s)", this.date_time);
    }
}
