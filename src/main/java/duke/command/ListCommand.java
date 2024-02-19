package duke.command;

import duke.run.Storage;
import duke.run.TaskList;
import duke.run.Ui;

/**
 * Lists out all items in the tasklist.
 */
public class ListCommand extends Command {

    /**
     * Constructs ListCommand.
     */
    public ListCommand() {
    }

    /**
     * Runs the command to list out all tasks
     * in the tasklist.
     *
     * @param storage Storage containing data of
     *          previous program.
     * @param taskList Tasklist of program.
     * @param ui Ui that handles user interactions.
     * @return Print statement for list command.
     */
    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) {
        int index = 1;
        String printStatement = "--------------------------" + "\n"
                + "Here are the tasks in your list:" + "\n";
        for (int i = 0; i < taskList.getSize(); i++) {
            printStatement += index + "." + taskList.getTask(i).toString() + "\n";
            index += 1;
        }
        printStatement += "--------------------------";
        return printStatement;

    }
}
