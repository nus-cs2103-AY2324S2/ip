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
     * @param storage Storage containing data of
     *          previous program.
     * @param taskList Tasklist of program.
     * @param ui Ui that handles user interactions.
     * @return Print statement for find command.
     */
    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) {
        int index = 1;
        String printStatement = "--------------------------" + "\n"
                + "Here are the matching tasks in your list:" + "\n";
        for (int i = 0; i < taskList.getSize(); i++) {
            Task currTask = taskList.getTask(i);
            String currName = currTask.getName();
            if (!currName.contains(this.keyword)) {
                continue;
            }
            printStatement += index + "." + taskList.getTask(i).toString() + "\n";
            index += 1;
        }
        printStatement += "--------------------------";
        return printStatement;

    }

}
