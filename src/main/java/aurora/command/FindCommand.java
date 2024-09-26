package aurora.command;

import java.util.ArrayList;

import aurora.objects.AuroraException;
import aurora.objects.Task;
import aurora.tasklist.TaskList;
import aurora.ui.Ui;

/** The FindCommand class represents the "find" command. */
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
     * Constructs a FindCommand object.
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
        validateCommand();
        String keyword = this.splitCommands[1].toLowerCase();
        searchForTasks(keyword);
        String message = this.ui.getFoundListString(this.foundList);
        assert !message.equals("Command not executed.") : "Find command not executed.";
        return message;
    }

    /**
     * Validates if the find command input is valid by checking if it has the correct number of components.
     *
     * @throws AuroraException If the input format is incorrect.
     */
    private void validateCommand() throws AuroraException {
        if (this.splitCommands.length != 2) {
            throw new AuroraException(AuroraException.INVALID_FIND_FORMAT);
        }
    }

    /**
     * Searches for the tasks within the task list that contain the keyword in their descriptions and
     * places them into a separate task list.
     *
     * @param keyword Keyword to use for searching the task list.
     */
    private void searchForTasks(String keyword) {
        ArrayList<Task> tasks = this.taskList.getTaskList();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword)) {
                this.foundList.add(task);
            }
        }
    }

}
