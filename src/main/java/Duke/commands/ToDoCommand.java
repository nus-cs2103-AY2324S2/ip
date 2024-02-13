package duke.commands;

import duke.exceptions.DukeException;
import duke.exceptions.EmptyDescriptionException;
import duke.tasks.Task;
import duke.tasks.ToDo;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.UI;

/**
 * Class to execute ToDoCommand
 */
public class ToDoCommand extends Command {
    private String[] words;

    /**
     * Constructor for toDo command
     * @param words
     */
    public ToDoCommand(String[] words) {
        super();
        this.words = words;
    }
    /**
     * Executes the find command, searching for tasks containing the specified keyword.
     *
     * @param tasks The TaskList containing the list of tasks.
     * @param ui The UI object for displaying messages.
     * @param storage The Storage object for saving data to a file.
     * @return False indicating that the program should continue running.
     */
    @Override
    public boolean execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        if (words.length == 1) {
            throw new EmptyDescriptionException("todo");
        }
        Task newTask = new ToDo(words[1]);
        ui.displayAdd(tasks.addTask(newTask), tasks.getItems().size());
        storage.addToWriteFile(newTask);
        return false;
    }

    @Override
    public String executeForString(TaskList tasks, UI ui, Storage storage) throws DukeException {
        if (words.length == 1) {
            throw new EmptyDescriptionException("todo");
        }
        Task newTask = new ToDo(words[1]);
        storage.addToWriteFile(newTask);
        return ui.addTaskMessage(tasks.addTask(newTask), tasks.getItems().size());
    }
}
