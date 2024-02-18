package exceptions;

public class WrongEventFormatException extends DukeExceptions {
    public String msg = "Oops! This is given in an input that I cannot understand! \n" +
            "Your format should be event [name of task] /from [date] /to [date]";

    public WrongEventFormatException() {
        super();
    }

    public String output() {
        return String.format("%s%s\n", super.output(), this.msg);
    }
}
