package exception;

public abstract class NarutoException extends Exception {

    public enum ErrorType {
        EMPTY_TODO,
        EMPTY_DEADLINE,
        EMPTY_EVENT,
        INVALID_DEADLINE,
        INVALID_EVENT,
        INVALID_COMMAND,
        INVALID_INDEX,
        FILE_CORRUPTED,
        INVALID_ACTION
    }
    
    public abstract ErrorType getErrorType();
    
    public NarutoException(String message) {
        super(message);
    }

    public static NarutoException createEmptyTodoException() {
        return new EmptyTodoException("Looks like your todo is missing a description! \nEnter an " +
                "input after the 'todo' command to enter a todo.");
    }
    
    public static NarutoException createEmptyDeadlineException() {
        return new EmptyDeadlineException("Looks like your deadline is missing a description! " +
                "\nEnter an input after the 'deadline' command to add a deadline.");
    }

    public static NarutoException createEmptyEventException() {
        return new EmptyEventException("Looks like your event is missing a description! \nEnter " +
                "an input after the 'event' command to add an event.");
    }

    public static NarutoException createInvalidDeadlineException() {
        return new InvalidDeadlineException("Looks like you forgot to enter a deadline! \nEnter a" +
                " deadline after the command '/by' to indicate when your deadline " +
                "is.");
    }

    public static NarutoException createInvalidEventException() {
        return new InvalidEventException("Looks like your event doesn't have a valid start or " +
                "end time! \nMake sure your input uses the '/from' and '/to' commands to indicate" +
                "\nthe start and end times of your event.");
    }
    
    public static NarutoException createInvalidCommandException() {
        return new InvalidCommandException("Sorry, I didn't catch that. ヾ(_ _。）");
    }

    public static NarutoException createInvalidIndexException() {
        return new InvalidIndexException("Sorry, I couldn't find the task you indicated... ｡･ﾟ" +
                "ﾟ･(>д<)･ﾟﾟ･｡");
    }

    public static NarutoException createFileCorruptedException() {
        return new FileCorruptedException("Oh no! Something seems to have happened to the " +
            "tasklist... I'll reset the tasks! °՞(ᗒᗣᗕ)՞°");
    }

    public static NarutoException createInvalidActionException() {
        return new InvalidActionException("I couldn't seem to understand that... Try again!");
    }
}
