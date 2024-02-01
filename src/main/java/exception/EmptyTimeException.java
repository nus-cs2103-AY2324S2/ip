package exception;

public class EmptyTimeException extends Exception {
    private String task;
    private String time;

    public EmptyTimeException(String task, String time) {
        this.task = task;
        this.time = time;
    }

    @Override
    public String getMessage() {
        return "    Please provide the "
                + time + " time for the "
                + task + ".\n"
                + "    ------------------------------------------------\n";
    }
}
