package javassist.command;

import javassist.exception.JavAssistException;
import javassist.util.JavAssistList;
import javassist.util.Storage;
import javassist.util.Ui;

/**
 * Represents a basic Command.
 */
public interface Command {
    public String execute(JavAssistList javAssistList, Ui ui, Storage storage) throws JavAssistException;

    public default boolean isExit() {
        return false;
    };

    public default boolean isExpenseCommand() {
        return false;
    }
}
