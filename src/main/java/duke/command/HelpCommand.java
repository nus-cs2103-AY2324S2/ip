package duke.command;

import java.util.List;

import duke.task.TaskList;

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
    public TaskList execute(TaskList tasks) {
        System.out.printf("\nHere's all the things I can do for you! ~(^o.o^)\n");
        System.out.println("1. todo <description> - Add a reminder to do something!");
        System.out.println("2. deadline <description> /by <deadline> - Add a reminder.. with a deadline!");
        System.out.println("3. event <description> /from <start> /to <end> - Add an event to your calendar");
        System.out.println("4. list - List all your tasks and events");
        System.out.println("5. viewbydate - List all your tasks and events on a specific date");
        System.out.println("6. mark <index> - Mark a task as done");
        System.out.println("7. unmark <index> - Mark a task as undone");
        System.out.println("8. delete <index> - Delete a task. Warning! I can't restore deleted tasks");
        System.out.println("9. help - Show this list of commands");
        System.out.println("10. bye - Leave :(");
        return tasks;
    }
}
