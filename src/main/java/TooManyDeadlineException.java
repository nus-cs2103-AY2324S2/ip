public class TooManyDeadlineException extends Exception{
    public TooManyDeadlineException() {
        super("Pls only add one deadline for this task! :)");
    }
}
