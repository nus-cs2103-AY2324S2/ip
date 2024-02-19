package commands;

import destiny.DukeException;
import destiny.TaskList;
import destiny.ToDo;

/**
 * Command that creates new ToDo task and adds it to task list.
 */
public class ToDoCmd extends Command {

    private String details;

    /**
     * Constructor for the ToDoCmd class.
     *
     * @param details Information for the ToDo task.
     */
    public ToDoCmd(String details) {
        this.details = details;
    }
    @Override
    public String execute(TaskList tasks) throws DukeException {
        if (details == "" || details == null) {
            throw new DukeException("Please enter a description for the ToDo command");
        }

        ToDo newToDo = new ToDo(details);
        return tasks.addTask(newToDo);
    }
}
