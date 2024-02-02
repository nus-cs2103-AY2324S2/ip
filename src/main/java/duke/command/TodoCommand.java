package duke.command;

import duke.task.TaskList;
import duke.task.Todo;

import duke.util.Ui;
import duke.util.Storage;

import duke.exception.MissingTodoException;
import duke.exception.SaveStorageException;

/**
 * The class representing the creation of deadline task command.
 * */
public class TodoCommand extends Command {
    /* The separated list of constituent words in the user-entered command. */
    String[] commandList;

    public TodoCommand(String[] commandList) {
        this.commandList = commandList;
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) throws MissingTodoException {
        String response = "";

        if (this.commandList.length <= 1) {
            throw new MissingTodoException();
        }
        Todo currentTodo = new Todo(commandList[1]);
        taskList.add(currentTodo);

        response += "Got it. I've added this task:\n  " + currentTodo;
        response += "\nNow you have " + taskList.size() + " tasks in the list.";

        try {
            storage.save(taskList);
        } catch (SaveStorageException e) {
            response = ui.showError(e.getMessage());
        }
        return response;
    }

    public boolean isExit() {
        return false;
    }
}
