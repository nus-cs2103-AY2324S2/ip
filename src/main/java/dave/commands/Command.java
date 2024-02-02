package dave.commands;

import dave.TaskList;
import dave.Ui;
import dave.exceptions.ChatbotException;
import dave.Storage;

public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws ChatbotException;

    public abstract boolean isExit();
}
