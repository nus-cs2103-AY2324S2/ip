package kervyn.Commands;

import kervyn.Tasks.TaskList;

public class FindCommand extends Command {

    private String[] userInput;
    /**
     * Constructs a DeleteCommand with the specified TaskList and user input.
     *
     * @param taskList The TaskList associated with this command.
     * @param userInput The user input array containing details for the deletion command.
     */
    public FindCommand(TaskList taskList, String[] userInput) {
        super("Find", taskList);
        this.userInput = userInput;
    }
}
