package linus.commands;

import linus.Ui;
import linus.tasks.TaskList;


/**
 * The parent class of command classes
 */
public abstract class Command {
    protected TaskList taskList;
    protected Ui ui;

    /**
     * constructor for command
     * @param taskList Task List contains all the tasks
     * @param ui Ui processes the outputs of the program
     */
    public Command(TaskList taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    /**
     * manipulate the task list and ui according to the command
     * @param description string that is specific to the command
     */
    public void execute(String description) {}
    
}
