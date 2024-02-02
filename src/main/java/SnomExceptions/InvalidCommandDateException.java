package SnomExceptions;
class InvalidCommandDateFormatException extends InvalidCommandException{
    public InvalidCommandDateFormatException() {
        super("Please ensure that both your date is of the format yyyy-mm-dd");
    }
}
