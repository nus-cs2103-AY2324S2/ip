package anxi.handlers;

import java.io.IOException;

import anxi.command.AnxiException;
import anxi.command.Storage;
import anxi.command.TaskList;
import anxi.command.Ui;
import anxi.tasks.Task;

/**
 * Handles inputs related to ToDo tasks.
 */
public class ToDoHandler {

    /**
     * ToDoHandler constructor.
     */
    public ToDoHandler() {
    }

    /**
     * Adds new todo task.
     *
     * @param input         Input command string.
     * @param storage       Instance of Storage class.
     * @param taskList      Instance of TaskList class.
     * @param ui            Instance of Ui class.
     * @return String   Indicates if task was successfully completed.
     */
    public String addToDo(String input, Storage storage, TaskList taskList, Ui ui) {
        try {
            return toDo(input, storage, taskList, ui);
        } catch (AnxiException de) {
            return ui.printErrorMessage(de.getErrorMessage());
        }
    }

    /**
     * Parses and calls relevant methods to add new todo and update storage.
     *
     * @param input         Input command string.
     * @param storage       Instance of Storage class.
     * @param taskList      Instance of TaskList class.
     * @param ui            Instance of Ui class.
     * @return String           Indicates if task was successfully completed.
     * @throws AnxiException    Thrown if there are missing inputs or inputs are out of bounds.
     */
    private String toDo(String input, Storage storage, TaskList taskList, Ui ui) throws AnxiException {
        if (input.matches("")) {
            throw new AnxiException("Need to check my eyesight, nothing to do.");
        }

        Task task = taskList.addTodo(input.strip());
        try {
            storage.addNewTask(task);
        } catch (IOException e) {
            return ui.printErrorMessage(e.getMessage());
        }
        return ui.printAddTask(task.toString(), taskList.getNumOfTasks());
    }
}
