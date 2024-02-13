package duke.command;

import duke.exception.DukeException;
import duke.util.DukeList;
import duke.util.Storage;
import duke.util.Ui;

/**
 * Represents a command that does not match any valid command.
 */
public class UnknownCommand implements Command {
    /**
     * Throws a DukeException to indicate invalid command and suggests possible working commands.
     *
     * @param dukeList Not used.
     * @param ui Not used.
     * @param storage Not used.
     * @return String of response of chatbot.
     * @throws DukeException Indicates invalid command.
     */
    @Override
    public String execute(DukeList dukeList, Ui ui, Storage storage) throws DukeException {
        throw new DukeException("Sorry I don't know what that means.\n"
                + "Try keywords: todo, deadline, event, list, mark, unmark, delete, find, add, list_e, total, deduct.");
    }

}
