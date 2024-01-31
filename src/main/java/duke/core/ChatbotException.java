package duke.core;

public class ChatbotException {

    public enum ErrorType {
        TODO_EMPTY("OOPS!!! The description of a todo cannot be empty."),
        DEADLINE_EMPTY("OOPS!!! The deadline time cannot be empty."),
        EVENT_EMPTY("OOPS!!! The event times cannot be empty."),
        UNKNOWN_COMMAND("OOPS!!! I'm sorry, but I don't know what that means :-("),
        TASK_CORRUPT("duke.task.Task data is corrupted."),
        TODO_CORRUPT("TODO data is corrupted."),
        EVENT_CORRUPT("duke.task.Event time data is corrupted."),
        UNKNOWN_TASK("Unknown task type.");


        private final String message;

        ErrorType(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

    }

    public static void getError(ErrorType errorType) {
        System.out.println(errorType.getMessage());
    }

}
