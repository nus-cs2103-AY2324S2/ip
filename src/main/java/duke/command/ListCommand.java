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
     * @param s Storage containing data of
     *          previous program.
     * @param t Tasklist of program.
     * @param u Ui that handles user interactions.
     */
    @Override
    public String execute(Storage s, TaskList t, Ui u) {
        int index = 1;
        String printStatement = "--------------------------" + "\n"
                + "Here are the tasks in your list:" + "\n";
        for (int i = 0; i < t.getSize(); i++) {
            printStatement += String.valueOf(index) + "." + t.getTask(i).toString() + "\n";
            index += 1;
        }
        printStatement += "--------------------------";
        return printStatement;

    }
}
