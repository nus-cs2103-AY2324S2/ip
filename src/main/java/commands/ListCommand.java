package commands;

import irwyn.tasks.TaskList;
import misc.StorageManager;
import misc.Ui;
import irwyn.tasks.Task;
import java.util.ArrayList;

/**
 * This class encapsulates the class ListCommand.
 * It lists the different Tasks on the list.
 *
 * @author Irwyn Liong
 * @version Week-3
 */
public class ListCommand extends Command {

    /**
     * Constructor for a ListCommand object.
     */
    ListCommand () {
        super(false);
    }

    /**
     * Executes the list command.
     *
     * @param taskList TaskList handles the tasks list.
     * @param ui Ui handles output.
     * @param storageManager Storage manager handles storing & deleting of tasks.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, StorageManager storageManager) {
        String reply = "Here are the tasks in your list:\n";
        ArrayList<Task> tasks = taskList.getTasks();
        for (int i = 0; i < tasks.size(); i++) {
            String num = i + 1 + ". ";
            Task task = tasks.get(i);
            reply += num + task + "\n";
        }

        ui.reply(reply);
    }

    /**
     * Executes the list command.
     *
     * @param storageManager Storage manager handles storing & deleting of tasks.
     * @param ui Ui handles output.
     * @param taskList TaskList handles the tasks list.
     * @return Response String.
     */
    @Override
    public String execute(StorageManager storageManager, Ui ui, TaskList taskList) {
        String reply = "Here are the tasks in your list:\n";
        ArrayList<Task> tasks = taskList.getTasks();
        for (int i = 0; i < tasks.size(); i++) {
            String num = i + 1 + ". ";
            Task task = tasks.get(i);
            reply += num + task + "\n";
        }

        return ui.getReply(reply);
    }
}