package goldbot.commands;

import goldbot.ChatSession;
import goldbot.exceptions.InvalidParametersException;

public interface Command {
    public void execute(ChatSession chatSession, String commandArgs) throws InvalidParametersException;
}
