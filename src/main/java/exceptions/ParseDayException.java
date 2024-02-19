package exceptions;

public class ParseDayException extends DukeExceptions {
    private String msg = "Please ensure that your date follows this format [D/M/YYYY] \n"
            + "Eg:  2/12/2019";

    public ParseDayException() {
        super();
    }

    public String output() {
        return String.format("%s%s\n", super.output(), this.msg);
    }
}
