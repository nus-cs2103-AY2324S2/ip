package mike.command;

import mike.TaskList;

/**
 * Exits the program.
 * @author ningc
 */
public class ExitCommand extends Command {

    @Override
    public String execute(TaskList taskList) {
        return response();
    }

    /**
     * Returns a farewell message with Wazowski pizazz.
     */
    private String response() {
        return " Where are you going? We'll talk.\n"
                + " We'll have a latte.";
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String toString() {
        return "EXIT";
    }
}

