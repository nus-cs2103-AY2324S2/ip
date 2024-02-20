package duke.command;

import duke.Storage;
import duke.TaskList;

public class HelpCommand extends Command {

    /**
     * Generates a list of commands that the user can use.
     * @return A string that lists all the commands
     */
    public String execute(TaskList tasks, Storage storage) {
        return "Here are the commands you can use:\n"
                + "1. todo <description>\n"
                + "2. deadline <description> /by <date>\n"
                + "3. event <description> /at <date>\n"
                + "4. list\n"
                + "5. find <keyword>\n"
                + "6. mark <task number>\n"
                + "7. unmark <task number>\n"
                + "8. delete <task number>\n"
                + "9. help\n"
                + "10. bye\n";
    }
}
