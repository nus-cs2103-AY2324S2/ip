public class EventTask extends Task {
    public String date_time1;
    public String date_time2;
    public EventTask(String taskname) {
        super(taskname);
        this.date_time1 = "";
        this.date_time2 = "";
    }
    public EventTask(String taskname, String date_time1, String date_time2) {
        super(taskname);
        this.date_time1 = date_time1;
        this.date_time2 = date_time2;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() +
                String.format("(from: %sto: %s)", this.date_time1, this.date_time2);
    }
}
