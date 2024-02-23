package shon.command;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import shon.exception.ParameterException;

/**
 * Represents the command that displays the list of available commands.
 */
public class HelpCommand extends Command {
    @Override
    public String execute() throws ParameterException, DateTimeParseException {
        List<String> outputs = new ArrayList<>();
        outputs.add("The following command are available:");
        outputs.add("help: displays this current list of commands available");
        outputs.add("tlist: displays your current task list");
        outputs.add("todo <desc>: adds a Todo task into your task list with the description");
        outputs.add("deadline <desc> /by <datetime>: adds a Deadline task into your task list with the"
                + "description and datetime. Datetime must be given in dd/mm/yyyy hhmm");
        outputs.add("event <desc> /from <datetime> /to <datetime>: adds an Event task into your task list"
                + "with the description and duration. Datetime must be given in dd/mm/yyyy hhmm");
        outputs.add("mark <idx>: marks the task at given index as done");
        outputs.add("unmark <idx>: unmarks the task at given index as done");
        outputs.add("deltask <idx>: deletes the task at given index");
        outputs.add("find <keyword>: finds all tasks with matching keyword");
        outputs.add("nlist: displays your current note list");
        outputs.add("note <text>: adds a note with the given text to your note list");
        outputs.add("delnote <idx>: deletes the note at the given index");
        outputs.add("bye: exits the app");
        return String.join("\n", outputs);
    }
}
