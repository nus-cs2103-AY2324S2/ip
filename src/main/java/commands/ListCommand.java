package commands;

import storage.Storage;
import task.TaskList;
import ui.Ui;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        if (tasks.isEmpty()) {
            System.out.println("\t Congrats, you have no more tasks! Uncle is proud of you!");
        }
        for (int i = 0; i < tasks.numTasks(); i++){
            System.out.println("\t " + (i+1) + ". " + tasks.get(i));
        }
    }
}
