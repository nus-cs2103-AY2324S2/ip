package duke.dukeexception;

public class DukeInvalidDateTimeFormat extends DukeException{
    String timeFormat;
    public DukeInvalidDateTimeFormat(String timeFormat) {
        super();
        this.timeFormat = timeFormat;
    }
    @Override
    public String toString() {
        return String.format("%s Date - Time Format is wrong! Please Follow format: %s", super.toString(), timeFormat);
    }
}
