package SnomBot.SnomExceptions;

class InvalidCommandTaskDescException extends InvalidCommandException{
    public InvalidCommandTaskDescException() {
        super("Please dont leave your task description or date blank");
    }
}
