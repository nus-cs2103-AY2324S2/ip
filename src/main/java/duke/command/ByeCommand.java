package duke.command;

import duke.run.Storage;
import duke.run.TaskList;
import duke.run.Ui;

/**
 * Exits the program.
 */
public class ByeCommand extends Command {

    /**
     * Constructs ByeCommand.
     */
    public ByeCommand() {
    }

    /**
     * Runs the command to say bye to user and exit
     * the program.
     *
     * @param storage Storage containing data of
     *          previous program.
     * @param taskList Tasklist of program.
     * @param ui Ui that handles user interactions.
     * @return Print statement for bye command.
     */
    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) {
        return ui.bye();
    }

}
