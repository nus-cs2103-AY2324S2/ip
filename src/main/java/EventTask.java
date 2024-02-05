public class EventTask extends Task {
    public DateTime dateTimeFrom;
    public DateTime dateTimeTo;
    public EventTask(String taskname, String dateTimeFrom, String dateTimeTo) {
        super(taskname);
        this.dateTimeFrom = new DateTime(dateTimeFrom);
        this.dateTimeTo = new DateTime(dateTimeTo);
        this.hasDate = true;
    }
    @Override
    public Boolean isWithinDate(DateTime dt) {
        return dt.isWithinDate(this.dateTimeFrom, this.dateTimeTo);
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() +
                String.format("(from: %s to: %s)", this.dateTimeFrom, this.dateTimeTo);
    }
}
