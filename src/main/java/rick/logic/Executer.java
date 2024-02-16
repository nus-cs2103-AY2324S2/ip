package rick.logic;

import rick.logic.command.*;
import rick.tasks.Task;
import rick.util.Storage;
import rick.util.TaskList;

/**
 * An executer for parsed commands regarding a designated task list.
 */
public class Executer {
    private TaskList taskList;
    private Storage storage;
    public Executer(TaskList taskList, Storage storage) {
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * Executes bye commands.
     * @param byeCommand the bye command.
     * @return a string that says bye to user.
     */
    public String execute(ByeCommand byeCommand) {
        return "Bye! See you next time.";
    }

    /**
     * Executes list commands.
     * @param listCommand the list command.
     * @return a list of all tasks in the task list.
     */
    public String execute(ListCommand listCommand) {
        return this.taskList.list();
    }

    /**
     * Executes mark commands.
     * @param markCommand the mark or unmark command.
     * @return a string that tells the user that the task is marked / unmarked.
     */
    public String execute(MarkCommand markCommand) throws RickException {
        int index = Integer.parseInt(markCommand.respond()[0]);
        try {
            Task task = this.taskList.mark(index);
            storage.update();
            return "Nice! I've marked this task as done:\n" + task;
        } catch (RickException e) {
            throw e;
        }
    }

    /**
     * Executes unmark commands.
     * @param unmarkCommand the mark or unmark command.
     * @return a string that tells the user that the task is marked / unmarked.
     */
    public String execute(UnmarkCommand unmarkCommand) throws RickException {
        int index = Integer.parseInt(unmarkCommand.respond()[0]);
        try {
            Task task = this.taskList.unmark(index);
            storage.update();
            return "OK, I've marked this task as not done yet:\n" + task;
        } catch (RickException e) {
            throw e;
        }
    }

    /**
     * Executes delete commands.
     * @param deleteCommand the delete command.
     * @return a string that tells the user that the task is
     */
    public String execute(DeleteCommand deleteCommand) throws RickException {
        int index = Integer.parseInt(deleteCommand.respond()[0]);
        try {
            Task task = this.taskList.delete(index);
            storage.update();
            return "Noted. I've removed this task:\n"
                    + task
                    + "\nNow you have " + this.taskList.getSize() + " tasks in the list.";
        } catch (RickException e) {
            throw e;
        }
    }

    /**
     * Executes find commands.
     * @param findCommand the find command
     * @return a list of tasks found.
     */
    public String execute(FindCommand findCommand) throws RickException {
        String substring = findCommand.respond()[0];
        return this.taskList.find(substring);
    }


}
