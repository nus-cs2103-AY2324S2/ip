package duke.commands;

import duke.ChatSession;
import duke.exceptions.InvalidParametersException;

public interface Command {
    public void execute(ChatSession chatSession, String commandArgs) throws InvalidParametersException;
}
