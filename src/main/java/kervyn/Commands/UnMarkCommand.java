package kervyn.Commands;

import kervyn.Tasks.TaskList;

/**
 * Represents the "UnMark" command in the application, used to mark a task as not completed in the TaskList.
 */
public class UnMarkCommand extends Command {
    private String[] userInput;

    /**
     * Constructs an UnMarkCommand with the specified TaskList and user input.
     *
     * @param taskList The TaskList associated with this command.
     * @param userInput The user input array containing the index of the task to be unmarked.
     */
    public UnMarkCommand(TaskList taskList, String[] userInput) {
        super("UnMark", taskList);
        this.userInput = userInput;
    }

    /**
     * Executes the "UnMark" command.
     * This method marks a task as not completed in the task list based on the index provided in the user input.
     */
    @Override
    public void executeCommand() {
        taskList.unMarkTask(taskList.getTaskList(), this.userInput);
    }
}
