package duke.command;

import duke.run.Storage;
import duke.run.TaskList;
import duke.run.Ui;
import duke.tasks.Task;

/**
 * Finds specific task from list based on keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs FindCommand.
     *
     * @param keyword Specific word that user is searching for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Runs the command to find a specific task using
     * a keyword.
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
                + "Here are the matching tasks in your list:" + "\n";
        for (int i = 0; i < t.getSize(); i++) {
            Task currTask = t.getTask(i);
            String currName = currTask.getName();
            if (!currName.contains(this.keyword)) {
                continue;
            }
            printStatement += index + "." + t.getTask(i).toString() + "\n";
            index += 1;
        }
        printStatement += "--------------------------";
        return printStatement;

    }

}
