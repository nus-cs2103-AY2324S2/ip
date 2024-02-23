package dino.command;

import java.io.IOException;

import dino.DinoException;
import dino.Storage;
import dino.TaskList;

/**
 * Represents a command to help user to use the application.
 */
public class HelpCommand extends Command {
    @Override
    public String execute(TaskList taskList, Storage storage) throws IOException, DinoException {
        return "In case you need help:\n"
               + "list: list\n"
               + "add todo: todo <description>\n"
               + "add deadline: deadline <description> /by dd-mm-yyyy\n"
               + "add event: event <description> /from dd-mm-yyyy /to dd-mm-yyyy\n"
               + "filter: filter dd-mm-yyyy\n"
               + "find: find <keyword>\n"
               + "delete: delete <index>\n"
               + "mark: mark <index>\n"
               + "unmark: unmark <index>\n";
    }
}
