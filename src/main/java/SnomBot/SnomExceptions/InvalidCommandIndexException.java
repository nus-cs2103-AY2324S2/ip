package SnomBot.SnomExceptions;

class InvalidCommandIndexException extends InvalidCommandException{
    public InvalidCommandIndexException() {
        super("Please ensure that the index you entered is valid");
    }
}
