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
    private final String[] commandList;
    /* The first line of response to the user. */
    public static final String RESPONSE_ONE = "Got it. I've added this task:\n ";
    /* The second line of response to the user. */
    public static final String RESPONSE_TWO = "\nNow you have ";
    /* The third line of response to the user. */
    public static final String RESPONSE_THREE = " tasks in the list.";

    public TodoCommand(String[] commandList) {
        this.commandList = commandList;
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) throws MissingTodoException {
        if (this.commandList.length <= 1) {
            throw new MissingTodoException();
        }

        Todo currentTodo = new Todo(commandList[1]);
        taskList.add(currentTodo);

        String response = RESPONSE_ONE
                + currentTodo
                + RESPONSE_TWO
                + taskList.size()
                + RESPONSE_THREE;

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
