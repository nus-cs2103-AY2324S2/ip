public class HelpCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String message = "Available commands:\n"
                + "list \t\t- Lists all your tasks\n"
                + "todo \t\t- Create a new todo task.\n"
                + "deadline \t- Create a new task with a deadline.\n"
                + "event \t\t- Create a new task with a starting and ending time.\n"
                + "mark \t\t- Mark a task as completed.\n"
                + "unmark \t\t- Mark a task as not completed.\n"
                + "delete \t\t- Delete a task.";

        ui.sendMessage(message);
    }
}
