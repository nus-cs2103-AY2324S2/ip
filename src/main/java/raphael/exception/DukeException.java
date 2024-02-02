package raphael.exception;
public class DukeException extends Exception {
    public static final String INVALID_TASK_INDEX = "Invalid task index!";
    public static final String EXECUTE_EXIT_COMMAND = "Exit command can't be executed!";
    public static final String CONNECT_FILE_EXCEPTION = "Error occurred when connecting the bot with the file!";
    public static final String READ_IO_EXCEPTION = "Error occurred when reading from the file!";
    public static final String WRITE_IO_EXCEPTION = "Error occurred when writing to the file!";
    public DukeException(String message) {
        super(message);
    }
    public static String invalidFormat(String s) {
        final String TODO_FORMAT = "todo [task]";
        final String DEADLINE_FORMAT = "deadline [task] /by [datetime]";
        final String EVENT_FORMAT = "event [task] /from [datetime] /to [datetime]";
        final String CHECK_TASK_FORMAT = "mark [index]";
        final String UNCHECK_TASK_FORMAT = "unmark [index]";
        return String.format("Invalid format!\n"
                    + "Please follow the format: %s"
                    , s.equals("TODO") ? TODO_FORMAT
                                          : s.equals("EVENT")
                                          ? EVENT_FORMAT
                                          : s.equals("DEADLINE")
                                          ? DEADLINE_FORMAT
                                          : s.equals("CHECK_TASK")
                                          ? CHECK_TASK_FORMAT
                                          : s.equals("UNCHECK_TASK")
                                          ? UNCHECK_TASK_FORMAT
                                          : ""
                    );
    }
}
