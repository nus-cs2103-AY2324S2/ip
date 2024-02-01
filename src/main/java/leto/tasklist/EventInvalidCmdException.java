package leto.tasklist;

public class EventInvalidCmdException extends InvalidTaskException {
    public EventInvalidCmdException() {
        super("Task need to follow\n   `event _task_ /from _start_time_ /to _end_time_` format\n"
                + "Note time in YYYY-MM-DD HH:mm format");
    }
}
