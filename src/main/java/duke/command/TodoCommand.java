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
    String[] commandList;

    public TodoCommand(String[] commandList) {
        this.commandList = commandList;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws MissingTodoException {
        if (this.commandList.length <= 1) {
            throw new MissingTodoException();
        }
        Todo currentTodo = new Todo(commandList[1]);
        taskList.add(currentTodo);
        System.out.println("Got it. I've added this task:\n  " + currentTodo);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");

        try {
            storage.save(taskList);
        } catch (SaveStorageException e) {
            ui.showError(e.getMessage());
        }
    }

    public boolean isExit() {
        return false;
    }
}
