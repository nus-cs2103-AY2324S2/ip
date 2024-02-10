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

    @Override
    public String execute(Storage s, TaskList t, Ui u) {
        int index = 1;
        String printStatement = "--------------------------" + "\n"
                + "Here are the matching tasks in your list:" + "\n";
        for (int i = 0; i < t.getSize(); i++) {
            Task currTask = t.getTask(i);
            if (!currTask.getName().contains(this.keyword)) {
                continue;
            }
            printStatement += String.valueOf(index) + "." + t.getTask(i).toString() + "\n";
            index += 1;
        }
        printStatement += "--------------------------";
        return printStatement;

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
