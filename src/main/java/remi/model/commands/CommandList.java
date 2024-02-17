package remi.model.commands;

import java.util.HashMap;

import remi.io.Message;
import remi.model.TaskList;
import remi.model.Ui;
import remi.utils.RemiError;

/**
 * A list of all the commands. This class's responsibility is to parse commands as strings and run them.
 */
public class CommandList {
    private HashMap<String, Command> commandLookup;
    private TaskList taskList;
    private Ui chatbot;

    /**
     * Constructor that requires a TaskList object and Ui object.
     * Both of these are expected to be given by the Ui class.
     *
     * @param taskList the TaskList object
     * @param chatbot the Ui object, mostly used for exiting
     */

    public CommandList(TaskList taskList, Ui chatbot) {
        commandLookup = new HashMap<>();
        this.taskList = taskList;
        this.chatbot = chatbot;
        loadCommands();
    }

    private void loadCommands() {
        commandLookup.put("exit", new ExitCommand());
        commandLookup.put("list", new ListCommand());
        commandLookup.put("listsort", new ListSortCommand());
        commandLookup.put("mark", new MarkCommand());
        commandLookup.put("unmark", new UnmarkCommand());
        commandLookup.put("todo", new TodoCommand());
        commandLookup.put("deadline", new DeadlineCommand());
        commandLookup.put("event", new EventCommand());
        commandLookup.put("delete", new DeleteCommand());
        commandLookup.put("find", new FindCommand());
    }

    /**
     * This runs a keyword with the given args.
     * Updates all required state and returns the output message.
     *
     * @param keyword the keyword, first word in the inputted string
     * @param args the rest of the args, expected to be space seperated
     * @return the String response of the keyword and args
     * @throws RemiError when the keyword isn't recognized
     */
    public Message runKeyword(String keyword, String args) throws RemiError {
        if (this.commandLookup.containsKey(keyword)) {
            return commandLookup.get(keyword).run(args, taskList, chatbot);
        } else {
            throw new RemiError(keyword + " is not a real command.");
        }
    }
}
