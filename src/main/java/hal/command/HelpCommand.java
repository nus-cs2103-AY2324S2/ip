package hal.command;

import hal.task.TaskList;

public class HelpCommand extends Command {

    public HelpCommand() {
    }

    @Override
    public String execute(TaskList taskList) {
        return "todo <task description> \n" +
                "deadline <task description> /by YYYY-MM-DD\n" +
                "event <task description> /from YYYY-MM-DD /to YYYY-MM-DD\n" +
                "list\n" +
                "mark <task number>\n" +
                "unmark <task number>\n" +
                "delete <task number>\n" +
                "find <keyword>\n" +
                "bye";
    }
}