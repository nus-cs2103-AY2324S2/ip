package grizzly.commands;

import grizzly.utils.Database;
import grizzly.utils.Storage;

/**
 * This class implements the commmand for listing all available commands
 */
public class HelpCommand extends Command {
    /**
     * Creates a HelpCommand, sets isExit to false.
     */
    public HelpCommand() {
        super(false);
    }

    /**
     * Executes help command, returns all available commands.
     *
     * @param db the current database of records.
     * @param storage Storage object with save file.
     */
    @Override
    public String execute(Database db, Storage storage) {
        return "Here are the commands you can use:\n"
                + "1. Add a todo task: todo [description]\n\n"
                + "2. Add a deadline task: \n"
                + "deadline [description] /by [date, time]\n\n"
                + "3. Add an event task: \n"
                + "event [description] /from [date, time] \n/to [date, time]\n\n"
                + "4. Add a contact: \n"
                + "contact [name] \n/email [email] /number [number]\n\n"
                + "5. List all tasks: list tasks\n\n"
                + "6. List all contacts: list contacts\n\n"
                + "7. Find tasks and contacts: \nfind [keyword]\n\n"
                + "8. Mark a task as done: \nmark [task index]\n\n"
                + "9. Unmark a task as done:\nunmark [task index]\n\n"
                + "10. Delete a task:\ndelete task [task index]\n\n"
                + "11. Delete a contact:\ndelete contact [contact index]\n\n"
                + "12. Exit the program: bye\n\n"
                + "View a comprehensive user guide at: \n"
                + "https://delishad21.github.io/ip/";
    }

}
