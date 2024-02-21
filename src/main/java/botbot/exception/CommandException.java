package botbot.exception;

public class CommandException extends BotBotException {
    public CommandException() {
        super("Your command is unrecognized!");
    }
}
