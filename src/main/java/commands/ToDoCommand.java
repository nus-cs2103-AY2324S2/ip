package commands;

import irwyn.tasks.TaskList;
import irwyn.tasks.ToDo;
import misc.StorageManager;
import misc.Ui;

/**
 * This class encapsulates the class ToDoCommand.
 * It executes the creation of a ToDo object.
 *
 * @author Irwyn Liong
 * @version Week-3
 */
public class ToDoCommand extends Command {
    private final String todoDescription;

    /**
     * Constructor for a ToDoCommand object.
     * @param input The input by the user to parse into a command.
     */
    ToDoCommand(String input) {
        super(false);
        this.todoDescription = input.replaceFirst("todo ", "");
    }

    /**
     * Executes the todo command.
     * This method creates a ToDo object.
     *
     * @param taskList TaskList handles the tasks list.
     * @param ui Ui handles output.
     * @param storageManager Storage manager handles storing & deleting of tasks.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, StorageManager storageManager) {
        ToDo todo = new ToDo(todoDescription);
        if (!isDuplicate(todo, taskList)) {
            taskList.addTask(todo);
        } else {
            ui.reply("This task already exists in the task list.");
        }
        storageManager.save(taskList.getTasks());
        ui.reply(todo.replyString(taskList.getTasksSize()));
    }

    /**
     * Executes the todo command.
     * This method creates a ToDo object.
     *
     * @param storageManager Storage manager handles storing & deleting of tasks.
     * @param ui Ui handles output.
     * @param taskList TaskList handles the tasks list.
     * @return Response String.
     */
    @Override
    public String execute(StorageManager storageManager, Ui ui, TaskList taskList) {
        ToDo todo = new ToDo(todoDescription);
        if (!isDuplicate(todo, taskList)) {
            taskList.addTask(todo);
        } else {
            return ui.getReply("This task already exists in the task list.");
        }
        storageManager.save(taskList.getTasks());
        return ui.getReply(todo.replyString(taskList.getTasksSize()));
    }
}
