package duke.command;

import duke.exception.FindInvalidException;
import duke.task.TaskList;
import duke.task.Task;
import duke.util.Storage;
import duke.util.Ui;

/**
 * The class representing the find command.
 * */
public class FindCommand extends Command {
    /* The separated list of constituent words in the user-entered command. */
    private final String[] commandList;
    /* The chatbot default response to the user. */
    public static final String RESPONSE = "Here are the matching tasks in your list:";

    public FindCommand(String[] commandList) {
        this.commandList = commandList;
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) throws FindInvalidException {
        if (this.commandList.length <= 1) {
            throw new FindInvalidException();
        }

        String response = RESPONSE;

        for (int i = 0; i < taskList.size(); i++) {
            Task currentTask = taskList.get(i);
            int index = i + 1;
            if (currentTask.getDescription().contains(commandList[1])) {
                response += "\n" + index + "." + currentTask;
            }
        }

        return response;
    }

    public boolean isExit() {
        return false;
    }
}
