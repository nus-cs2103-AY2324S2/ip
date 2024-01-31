package botbot.exception;

public class InvalidDateException extends BotBotException {
    public InvalidDateException() {
        super("Please use the correct date format: yyyy-MM-dd HH:mm");
    }
}
