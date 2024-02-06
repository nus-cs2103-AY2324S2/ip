package exceptions;

public class WrongFormatException extends DukeExceptions {
    public String msg = "Oops! This is given in an input that I cannot understand!";

    public WrongFormatException() {
        super();
    }

    public String output() {
        return String.format("%s%s\n", super.output(), this.msg);
    }
}
