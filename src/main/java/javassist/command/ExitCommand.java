package javassist.command;

import javassist.exception.JavAssistException;
import javassist.util.JavAssistList;
import javassist.util.Storage;
import javassist.util.TaskList;
import javassist.util.Ui;

/**
 * Represents a command to exit the program.
 */
public class ExitCommand implements Command {
    /**
     * Displays exit message.
     *
     * @param javAssistList Holds the tasks added.
     * @param ui Display messages about executed operation.
     * @param storage Handles IO storage operation.
     * @return String of response of chatbot.
     * @throws JavAssistException Not thrown.
     */
    @Override
    public String execute(JavAssistList javAssistList, Ui ui, Storage storage) throws JavAssistException {
        TaskList list = (TaskList) javAssistList;
        return ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
