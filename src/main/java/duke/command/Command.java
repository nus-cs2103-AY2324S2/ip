package duke.command;

import duke.exception.ChatBotParameterException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public abstract class Command {
    protected final String keyword;
    protected final String parameters;

    protected Command(String keyword, String parameters) {
        this.keyword = keyword;
        this.parameters = parameters;
    }

    public boolean isExit() {
        return false;
    }

    abstract public void execute(Storage storage, Ui ui, TaskList taskList) throws ChatBotParameterException;
}
