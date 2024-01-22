// DukeExceptions.java
public class DukeException extends Exception{
    public DukeException(String message) {
        super(message);
    }
    public static class EmptyTodoDescriptionException extends DukeException {
        public EmptyTodoDescriptionException() {
            super("OOPS!!! The description of a todo cannot be empty.");
        }
    }

    public static class UnknownCommandException extends DukeException {
        public UnknownCommandException() {
            super("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }



}


