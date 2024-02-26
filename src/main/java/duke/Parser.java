package duke;

import duke.command.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * The type Parser.
 * This class parses the commands entered by user.
 */
public class Parser {
    /**
     * Parse command.
     *
     * @param commandText the command text entered by user
     * @return the command object of type Command containing parsed details of tasjs
     * @throws DukeException when the parser cannot parse user input into appropriate command objects
     *
     */
    public static Command parse(String commandText) throws DukeException{
        Command command;
        if (commandText.equals("bye")) {
            command = new ExitCommand();
        } else if (commandText.contains("mark")) {
            int index = commandText.indexOf(" ");
            boolean isMarked = false;
            String argVal = commandText.substring(index+1);
            int itemNo = Integer.parseInt(argVal);
            if (commandText.startsWith("mark")) {
                isMarked = true;
            } else if (commandText.startsWith("unmark")) {
                isMarked = false;
            }
            command = new UpdateCommand(itemNo, isMarked);
        } else if (commandText.equals("list")) {
            command = new ListCommand();
        } else if (commandText.startsWith("todo") || commandText.startsWith("event") || commandText.startsWith("deadline")) {
            Task todo = null;
            todo = handleTodoEventDeadline(commandText);
            command = new AddCommand(todo);
        } else if (commandText.startsWith("delete")) {
            int index = commandText.indexOf(" ");
            String argVal = commandText.substring(index+1);
            int itemNo = Integer.parseInt(argVal);
            command = new DeleteCommand(itemNo);
        } else {
            throw new DukeException("Oh dear! I do not understand this command! Try again!");
        }
        return command;
    }

    private static Task handleTodoEventDeadline(String info) throws DukeException {
        int index = info.indexOf(" ");
        if (index <= 0) {
            throw new DukeException("OMG! Description is empty. Cannot accept.");
        }
        String argVal = info.substring(index+1);
        Task todo = null;
        if (info.startsWith("todo")) {
            todo = new Todo(argVal, false, -1, "T");
        } else if (info.startsWith("event")) {
            int indfrom = argVal.indexOf("/from");
            String eventname = argVal.substring(0,indfrom);
            eventname = eventname.trim();
            String eventFromStr = argVal.substring(indfrom+6, argVal.indexOf("/to"));
            eventFromStr = eventFromStr.trim();
            int indto = argVal.indexOf("/to");
            String eventToStr = argVal.substring(indto+4);
            eventToStr = eventToStr.trim();
            todo = new Event(eventname, false, -1, "E", eventFromStr, eventToStr);
        } else if (info.startsWith("deadline")) {
            int indfrom = argVal.indexOf("/by");
            String eventname = argVal.substring(0,indfrom);
            eventname = eventname.trim();
            String deadlineStr = argVal.substring(indfrom+4);
            todo = new Deadline(eventname, false, -1, "D", deadlineStr);
        }
        return todo;
    }
}
