package exception;

public class InvalidFormatException extends DukeException {
    private String task;
    private String time;

    public InvalidFormatException(String task, String time) {
        this.task = task;
        this.time = time;
    }

    @Override
    public String getMessage() {
        return "    Please provide the time for the "
                + this.task
                + " by adding it after \""
                + this.time + "\".\n"
                + "    ------------------------------------------------\n";
    }
}
