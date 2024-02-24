package zhen.command;

import zhen.Storage;
import zhen.TaskList;
import zhen.Ui;

/**
 * Represents the command of finding a task from the task list using keyword.
 */
public class FindCommand extends Command {
    private final String stringToFind;

    /**
     * Constructs a command to find tasks containing certain keywords.
     *
     * @param stringToFind The keyword that the user wants to find.
     */
    public FindCommand(String stringToFind) {
        this.stringToFind = stringToFind;
    }
    /**
     * Executes the command of finding tasks containing certain keywords.
     *
     * @param taskList Task list to search tasks from.
     * @param ui UI to manage the interaction with users.
     * @param storage Store the product of execution locally.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String stringToPrint = "Here are the matching tasks in your list:\n";
        int counter = 0;
        for (int i = 0; i < taskList.getTasks().size(); i++) {
            if (taskList.getTasks().get(i).getMessage().contains(stringToFind)) {
                counter++;
                stringToPrint = stringToPrint + counter + ". " + taskList.getTasks().get(i) + "\n";
            }
        }
        stringToPrint = stringToPrint.substring(0, stringToPrint.length() - 1);
        Ui.print_message(stringToPrint);
        return stringToPrint;
    }
    /**
     * Determines whether this command is an exit command.
     *
     * @return False, because this is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
