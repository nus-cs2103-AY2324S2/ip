package damon.exceptions;

public class NoDescriptionException extends DamonExceptions{
    public NoDescriptionException() {
        this.message = "Pls add description of this task, thank you! :)";
    }
}
