package duck;

public class HelpCommand implements Command {
    public HelpCommand() {

    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder helpMessage = new StringBuilder();

        // Add introductory message
        helpMessage.append("Here are the available commands:\n\n");

        // Add each command along with its description
        helpMessage.append("1. list - List all tasks\n");
        helpMessage.append("2. mark <task_index> - Mark a task as done\n");
        helpMessage.append("3. unmark <task_index> - Mark a task as undone\n");
        helpMessage.append("4. delete <task_index> - Delete a task\n");
        helpMessage.append("5. tag <task_index> <tag> - Tag a task\n");
        helpMessage.append("6. find <keyword> - Find a task\n");
        helpMessage.append("7a. todo <task> - Create a todo task\n");
        helpMessage.append("7b. deadline <task> /by <yyyy-mm-dd> - Create a deadline task\n");
        helpMessage.append("7c. event <task> /from <yyyy-mm-dd> /to <yyyy-mm-dd>\n");
        helpMessage.append(" - Create a deadline task\n");

        return helpMessage.toString();

    }

}
