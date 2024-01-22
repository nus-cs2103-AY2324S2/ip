package LetoTasks;

public class LetoEventInvalidCmdException extends LetoInvalidTaskException {
    public LetoEventInvalidCmdException() {
        super("Task need to follow\n   `event _task_ /from _start_time_ /to _end_time_` format");
    }
}
