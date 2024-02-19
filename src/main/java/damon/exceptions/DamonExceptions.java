package damon.exceptions;

public class DamonExceptions extends Exception{

    protected String message;

    public DamonExceptions() {
        this.message = " ";
    }

    public String getMessage() {
        return this.message;
    }
}