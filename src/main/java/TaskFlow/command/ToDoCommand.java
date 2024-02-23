package TaskFlow.command;

import TaskFlow.exception.TaskFlowException;
import TaskFlow.storage.Storage;
import TaskFlow.task.TaskList;
import TaskFlow.task.ToDo;
import TaskFlow.ui.Ui;

/**
 * A class that inherits from Command class.
 * Represents a command to add a ToDo task.
 */
public class ToDoCommand extends Command {

    private ToDo toDo;

    /**
     * Constructs a ToDoCommand with the specified description of the ToDo task.
     *
     * @param description The description of the ToDo task.
     */
    public ToDoCommand(String description) {
        this.toDo = new ToDo(description);
    }

    /**
     * Executes the ToDoCommand by adding the specified ToDo task to the TaskList.
     * Shows a message indicating the task has been added.
     * Saves the changes into the file.
     *
     * @param tasks         The TaskList that holds the list of tasks.
     * @param archiveTasks  The list of archived tasks.
     * @param ui            The Ui to interact with the user.
     * @param storage       The Storage to save the tasks to a file.
     * @param archived      The storage to save the archived tasks to a file.
     * @throws TaskFlowException If there is an error while executing the command.
     */
    @Override
    public String execute(TaskList tasks, TaskList archiveTasks, Ui ui,
                          Storage storage, Storage archived) throws TaskFlowException {
        tasks.add(toDo);
        System.out.println(tasks.list());
        storage.saveTask(tasks);
        return ui.showAddMsg(toDo, tasks.getTaskSize());
    }
}
