package duke.commands;

import duke.core.Ui;
import duke.tasks.TaskList;

/**
 * This class represents a Command that displays the given TaskList.
 */
public class ListCommand extends Command {
    private final TaskList taskList;

    /**
     * Constructs a ListCommand that displays the given TaskList in text form.
     *
     * @param taskList TaskList to be displayed.
     */
    public ListCommand(TaskList taskList) {
        super();
        this.taskList = taskList;
    }

    @Override
    public void execute() {
        Ui.printMessage(this.taskList.toString());
    }
}
