package aurora.command;

import java.util.ArrayList;

import aurora.objects.AuroraException;
import aurora.objects.Task;
import aurora.tasklist.TaskList;
import aurora.ui.Ui;

/**
 * The FindCommand class handles the "find" command.
 */
public class FindCommand extends Command {
    /** TaskList to interact with. */
    private TaskList taskList;

    /** Ui to interact with. */
    private Ui ui;

    /** Full command. */
    private String[] splitCommands;

    /** List of commands found */
    private ArrayList<Task> foundList;

    /**
     * Constructor for the FindCommand class.
     *
     * @param taskList      TaskList to interact with.
     * @param ui            Ui to interact with.
     * @param splitCommands Full command from the input.
     */
    public FindCommand(TaskList taskList, Ui ui, String[] splitCommands) {
        this.taskList = taskList;
        this.ui = ui;
        this.splitCommands = splitCommands;
        this.foundList = new ArrayList<>();
    }

    @Override
    public String handle() throws AuroraException {
        String message = "Command not executed.";
        if (this.splitCommands.length != 2) {
            throw new AuroraException("Invalid number of arguments!\n" +
                    "Make sure to enter find, then the keyword you wish to search for in the task list.");
        } else {
            String keyword = this.splitCommands[1].toLowerCase();
            ArrayList<Task> tasks = this.taskList.getTaskList();
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                String description = task.getDescription().toLowerCase();
                if (description.contains(keyword)) {
                    this.foundList.add(task);
                }
            }
            message = this.ui.getFoundListString(this.foundList);
        }
        return message;
    }

    public boolean isBye() {
        return false;
    }
}
