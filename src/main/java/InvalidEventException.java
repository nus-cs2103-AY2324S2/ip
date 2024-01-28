public class InvalidEventException extends DukeException {
    public InvalidEventException() {
        super();
    }

    @Override
    public String toString() {
        return "After I try so hard, you want to cook me by keying the date and time wrongly\n" +
                "try again by adding the time in the format [task description] / from: starttime / end : endtime\n";
    }
}
