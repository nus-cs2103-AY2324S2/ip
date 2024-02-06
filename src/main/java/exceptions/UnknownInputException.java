package exceptions;

import exceptions.DukeExceptions;

public class UnknownInputException extends DukeExceptions {
    final String msg = "WHAT DO YOU MEANNNNN";

    public UnknownInputException() {
        super();
    }

    @Override
    public String output() {
        return String.format("%s%s\n", super.output(), this.msg);
    }
}
