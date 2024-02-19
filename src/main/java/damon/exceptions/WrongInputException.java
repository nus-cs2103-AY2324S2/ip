package damon.exceptions;

public class WrongInputException extends DamonExceptions{

    public WrongInputException() {
        this.message = "Sorry, I cannot understand what you mean.";
    }
}
