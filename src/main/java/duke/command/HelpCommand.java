package duke.command;

import java.util.List;

import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A command class representing the action of displaying a list of available commands.
 *
 * <p>The {@code HelpCommand} class encapsulates the information and actions
 * required to display a list of available commands to the user. It inherits from the {@code Command}
 * class and implements the behavior specific to displaying the help message.</p>
 */
public class HelpCommand extends Command {
    public HelpCommand() {
        super("help", List.of());
    }

    @Override
    public TaskList execute(TaskList tasks, Ui ui) {
        ui.appendResponse("\nHere's all the things I can do for you! ~(^o.o^)\n");
        ui.appendResponse("1. todo <description> - Add a reminder to do something!");
        ui.appendResponse("2. deadline <description> /by <deadline> - Add a reminder.. with a deadline!");
        ui.appendResponse("3. event <description> /from <start> /to <end> - Add an event to your calendar");
        ui.appendResponse("4. list - List all your tasks and events");
        ui.appendResponse("5. viewbydate <date> - List all your tasks and events on a specific date");
        ui.appendResponse("6. find <keyword> - List all your tasks and events with <keyword>");
        ui.appendResponse("7. mark <index> - Mark a task as done");
        ui.appendResponse("8. unmark <index> - Mark a task as undone");
        ui.appendResponse("9. delete <index> - Delete a task. Warning! I can't restore deleted tasks");
        ui.appendResponse("10. help - Show this list of commands");
        ui.appendResponse("11. bye - Leave :(");
        return tasks;
    }
}
