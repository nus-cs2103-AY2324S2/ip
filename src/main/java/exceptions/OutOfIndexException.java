package exceptions;

public class OutOfIntexException extends DukeExceptions{
    public String msg = "The index you indicated is out of range!";
    public OutOfIntexException() {
        super();
    }

    @Override
    public String output() {return String.format("%s%s\n", super.output(), this.msg);}
}
