package Tim.commands;

import Tim.gui.GUI;
import Tim.storage.TaskList;

public class ListCommand extends Command{

    public static final String COMMAND_WORD = "list";

    /**
     * Executes list command.
     * @param taskList TaskList object
     * @return String that contains all tasks in taskList
     */
    @Override
    public String execute(TaskList taskList) {
         String contents = taskList.showALlTask();
         return GUI.listMsg(contents);
    }
}
