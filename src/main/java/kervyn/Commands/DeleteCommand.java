package kervyn.Commands;

import kervyn.Tasks.TaskList;

/**
 * Represents the "Delete" command in the application, used to delete a task from the TaskList.
 */
public class DeleteCommand extends Command {
    private String[] userInput;

    /**
     * Constructs a DeleteCommand with the specified TaskList and user input.
     *
     * @param taskList The TaskList associated with this command.
     * @param userInput The user input array containing details for the deletion command.
     */
    public DeleteCommand(TaskList taskList, String[] userInput) {
        super("Delete", taskList);
        this.userInput = userInput;
    }

    /**
     * Executes the "Delete" command.
     * This method removes a task from the task list based on the index provided in the user input.
     */
    @Override
    public void executeCommand() {
        taskList.removeTask(taskList.getTaskList(), this.userInput);
    }
}
