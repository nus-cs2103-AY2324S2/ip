package duke.command;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
        List<String> helpMessages = Arrays.asList(
                "Here's all the things I can do for you! ~(^o.o^)",
                "1. todo <description> - Add a reminder to do something!",
                "2. deadline <description> /by <deadline> - Add a reminder.. with a deadline!",
                "3. event <description> /from <start> /to <end> - Add an event to your calendar",
                "4. list - List all your tasks and events",
                "5. viewbydate <date> - List all your tasks and events on a specific date",
                "6. find <keyword> - List all your tasks and events with <keyword>",
                "7. mark <index> - Mark a task as done",
                "8. unmark <index> - Mark a task as undone",
                "9. delete <index> - Delete a task. Warning! I can't restore deleted tasks",
                "10. help - Show this list of commands",
                "11. bye - Leave :("
        );

        // Join the help messages with newline characters
        String helpMessage = helpMessages.stream().collect(Collectors.joining("\n"));

        // Append the formatted help message to UI
        ui.appendResponse(helpMessage);

        return tasks;
    }
}
