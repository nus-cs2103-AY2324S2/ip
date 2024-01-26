public class TimeFormatException extends DukeException{
    public TimeFormatException() {
        super();
    }

    @Override
    public String getMessage() {
        return String.format("%s your time should in this format: yyyy-mm-dd", super.getMessage());
    }
}
