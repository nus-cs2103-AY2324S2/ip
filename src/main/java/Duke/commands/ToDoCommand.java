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
    @Override
    public String executeForString(TaskList tasks, UI ui, Storage storage) throws DukeException {
        boolean hasEmptyDescription = words.length == 1;
        if (hasEmptyDescription) {
            throw new EmptyDescriptionException("todo");
        }
        String toDoDescription = words[1].trim();
        Task newTask = new ToDo(toDoDescription);
        storage.addToWriteFile(newTask);
        tasks.addTask(newTask);
        int currentNumberOfTasks = tasks.getNumberOfTasks();
        return ui.addTaskMessage(newTask, currentNumberOfTasks);
    }
}
