package exceptions;

import exceptions.DukeExceptions;

public class EmptyTaskException extends DukeExceptions {
    public String msg = "You can't do nothing!";
    public EmptyTaskException() {
        super();
    }

    @Override
    public String output() {
        return String.format("%s%s\n", super.output(), this.msg);
    }




}
