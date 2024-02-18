package exceptions;

public class WrongDeadlineFormatException extends DukeExceptions {
    public String msg = "Oops! This is given in an input that I cannot understand! \n" +
            "Your format should be deadline [name of task] /by [deadline]";

    public WrongDeadlineFormatException() {
        super();
    }

    public String output() {
        return String.format("%s%s\n", super.output(), this.msg);
    }
}
