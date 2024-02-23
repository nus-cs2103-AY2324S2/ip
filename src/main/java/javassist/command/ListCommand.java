package javassist.command;

import javassist.exception.JavAssistException;
import javassist.util.JavAssistList;
import javassist.util.Storage;
import javassist.util.TaskList;
import javassist.util.Ui;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand implements Command {

    /**
     * Displays all tasks in TaskList.
     *
     * @param javAssistList Holds the tasks added.
     * @param ui Displays the tasks listed.
     * @param storage Not used.
     * @return String of response of chatbot.
     * @throws JavAssistException If no task in TaskList.
     */
    @Override
    public String execute(JavAssistList javAssistList, Ui ui, Storage storage) throws JavAssistException {
        TaskList list = (TaskList) javAssistList;
        if (list.isEmpty()) {
            throw new JavAssistException("No task in list.\n"
                    + "You may add task with keywords: todo, deadline, event.");
        }
        return ui.showTasks(list);
    }

}
