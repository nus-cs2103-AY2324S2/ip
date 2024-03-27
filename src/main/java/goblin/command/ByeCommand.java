package goblin.command;

import goblin.Storage;
import goblin.TaskList;
import goblin.Ui;

public class ByeCommand extends Command {
    /**
     * create a new ByeCommand object
     */
    public ByeCommand() {
        super();
    }

    /**
     * print bye message and close scanner
     * @param tasks list of tasks
     * @param ui handle ui
     * @param storage handle storage
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        System.exit(0);
        return "bye";
    }

    /**
     * change the state of app to not working
     * @return false
     */
    public boolean isWorking() {
        return false;
    }
}
