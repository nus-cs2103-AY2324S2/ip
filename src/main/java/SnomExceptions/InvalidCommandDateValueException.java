package SnomExceptions;

class InvalidCommandDateValueException extends InvalidCommandException{

    public InvalidCommandDateValueException() {
        super("Please make sure that your end date is after the end date");
    }
}
