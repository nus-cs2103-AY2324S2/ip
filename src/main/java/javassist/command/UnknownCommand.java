package javassist.command;

import javassist.exception.JavAssistException;
import javassist.util.JavAssistList;
import javassist.util.Storage;
import javassist.util.Ui;

/**
 * Represents a command that does not match any valid command.
 */
public class UnknownCommand implements Command {
    /**
     * Throws a DukeException to indicate invalid command and suggests possible working commands.
     *
     * @param javAssistList Not used.
     * @param ui Not used.
     * @param storage Not used.
     * @return String of response of chatbot.
     * @throws JavAssistException Indicates invalid command.
     */
    @Override
    public String execute(JavAssistList javAssistList, Ui ui, Storage storage) throws JavAssistException {
        throw new JavAssistException("Sorry I don't know what that means.\n"
                + "Try keywords: todo, deadline, event, list, mark, unmark, delete, find, add, list_e, total, deduct.");
    }

}
