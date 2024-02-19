package duke.command;

import java.util.List;

import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A command class representing the action of ending the chatbot session.
 *
 * <p>The {@code ByeCommand} class encapsulates the information and actions
 * required to end the chatbot session gracefully. It inherits from the {@code Command}
 * class and implements the behavior specific to ending the session.</p>
 */
public class ByeCommand extends Command {
    protected String command;
    protected List<String> arguments;

    public ByeCommand() {
        super("bye", List.of());
    }

    /**
     * Prints out bye.
     */
    public TaskList execute(TaskList tasks, Ui ui) {
        ui.appendResponse("Bye! BYEMESSAGE@ByeCommand.java");
        return tasks;
    }
}
