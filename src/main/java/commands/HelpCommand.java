package commands;
public class HelpCommand extends Command {
    public HelpCommand() {}
    @Override
    public String execute() {
        return "Below are the available commands and formats to follow!\n"
                + "1. To view all your current task: list\n"
                + "2. To add new task:\n"
                + "    a. todos: todo <task>\n"
                + "    b. deadlines: deadline <task> /by <deadline>\n"
                + "    c. event: Event <event> /from <startDate, startTiming> /to <endDate, endTiming>\n"
                + "3. To delete a task: delete <task number>\n"
                + "4. To mark task as completed: mark <task number>\n"
                + "5. To unmark completed task: unmark <task number>\n"
                + "6. To exit program: bye\n";
    }
}
