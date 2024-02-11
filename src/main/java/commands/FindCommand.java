package commands;

import irwyn.tasks.Task;
import irwyn.tasks.TaskList;
import misc.StorageManager;
import misc.Ui;

import java.util.ArrayList;

/**
 * This class encapsulates the class FindCommand.
 * It is meant to find a task in the list of tasks.
 *
 * @author Irwyn Liong
 * @version Week-3
 */
public class FindCommand extends Command {

    private final String filter;

    /**
     * Constructor for a FindCommand object.
     * @param input The string input by the user to parse into a command.
     */
    FindCommand(String input) {
        super(false);
        this.filter = input.replaceFirst("find ", "");
    }

    /**
     * Executes the find command.
     * Goes through the list and finds the tasks with description matching the user input.
     *
     * @param taskList Task List handles tasks operations.
     * @param ui Ui handles output to users.
     * @param storageManager Storage manager handles storing and deletion of tasks.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, StorageManager storageManager) {
        String reply = "Here are the tasks in your search list:\n";
        ArrayList<Task> tasks = taskList.getTasks();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.toString().contains(this.filter)) {
                String num = i + 1 + ". ";
                reply += num + task + "\n";
            }
        }
        ui.reply(reply);
    }

    /**
     * Executes the find command.
     * Goes through the list and finds the tasks with description matching the user input.
     *
     * @param storageManager Storage manager handles storing & deleting of tasks.
     * @param ui Ui handles output.
     * @param taskList TaskList handles the tasks list.
     * @return Response String.
     */
    @Override
    public String execute(StorageManager storageManager, Ui ui, TaskList taskList) {
        String reply = "Here are the tasks in your search list:\n";
        ArrayList<Task> tasks = taskList.getTasks();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.toString().contains(this.filter)) {
                String num = i + 1 + ". ";
                reply += num + task + "\n";
            }
        }
        return ui.getReply(reply);
    }
}
