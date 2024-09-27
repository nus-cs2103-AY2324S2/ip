package lamball.command;

import lamball.TaskList;

/**
 * Command that creates a todo task.
 */
public class ToDoCommand extends Command {
    private String toDo;
    private TaskList taskList;
    private boolean isInit;

    /**
     * Constructor for the command.
     *
     * @param toDo Name of task.
     * @param tasks TaskList to add task to.
     * @param isInit Whether this is run during initialization.
     */
    public ToDoCommand(String toDo, TaskList tasks, boolean isInit) {
        this.taskList = tasks;
        this.toDo = toDo;
        this.isInit = isInit;
    }

    @Override
    public boolean run() {
        taskList.toDo(toDo, isInit);
        return true;
    }
}
