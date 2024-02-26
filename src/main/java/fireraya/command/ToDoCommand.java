package fireraya.command;

import fireraya.exception.FirerayaException;
import fireraya.main.Storage;
import fireraya.main.TaskList;
import fireraya.main.Ui;
import fireraya.task.Task;
import fireraya.task.Todo;

/**
 * Class to handle a ToDo Command in the program.
 *
 * This class is extended from the Command superclass.
 */
public class ToDoCommand extends Command {
    private String description;

    /**
     * Constructor for the Todo Command.
     *
     * @param description String of the description of the todo Task.
     */
    public ToDoCommand(String description) {
        this.description = description;
    }

    /**
     * Overrides the format to execute the command.
     *
     * @param tasks the Tasklist of program.
     * @param ui the Ui of the program.
     * @param storage the storage of the program.
     * @return String representing the message to be displayed to the user.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws FirerayaException {
        Todo todo = new Todo(description);
        tasks.add(todo);
        String a = ui.taskAddedMsg(todo, tasks.size());
        storage.saveToFile(tasks.getTasks());
        return a;
    }

}

