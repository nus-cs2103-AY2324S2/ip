package exceptions;

public class EmptyDateException extends DukeExceptions {
    private String msg = "Your command should only have a date field!";
    public EmptyDateException() {
        super();
    }

    public String output() {
        return String.format("%s%s\n", super.output(), this.msg);
    }
}
