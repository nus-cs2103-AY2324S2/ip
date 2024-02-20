package notduke.notdukeexception;

public class NotDukeInvalidDateTimeFormat extends NotDukeException {
    private String timeFormat;
    public NotDukeInvalidDateTimeFormat(String timeFormat) {
        super();
        this.timeFormat = timeFormat;
    }
    @Override
    public String toString() {
        return String.format("%s Date - Time Format is wrong! Please Follow format: %s", super.toString(), timeFormat);
    }
}
