package exceptions;

public class EmptyStringException extends DukeExceptions {
    public String msg = "Search query cannot be empty!";

    public EmptyStringException() {
        super();
    }

    public String output() {
        return String.format("%s%s\n", super.output(), this.msg);
    }

}

