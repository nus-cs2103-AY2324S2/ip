package duke.command;

import duke.task.TaskList;
import duke.task.Task;

import duke.util.Ui;
import duke.util.Storage;

/**
 * The class representing a list command.
 * */
public class ListCommand extends Command {
    /* The chatbot default response to the user. */
    public static final String RESPONSE = "Here are the tasks in your list:";

    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String response = RESPONSE;
        for (int i = 0; i < taskList.size(); i++) {
            Task currentTask = taskList.get(i);
            int index = i + 1;
            response += "\n" + index + "." + currentTask;
        }
        return response;
    }

    public boolean isExit() {
        return false;
    }
}
