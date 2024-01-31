package duke.command;

import duke.tasks.Task;

import duke.run.Storage;
import duke.run.TaskList;
import duke.run.Ui;

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
    public void execute(Storage s, TaskList t, Ui u)  {
        int index = 1;
        System.out.println("--------------------------");
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < t.getSize(); i++) {
            Task currTask = t.getTask(i);
            if (currTask.getName().contains(this.keyword)) {
                System.out.println(String.valueOf(index) + "." + t.getTask(i).toString());
                index +=1;
            }
        }
        System.out.println("--------------------------");

    }

    @Override
    public boolean isExit() {
        return false;
    }
}