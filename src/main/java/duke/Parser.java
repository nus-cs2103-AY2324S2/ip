package duke;

import duke.command.*;
import duke.task.*;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Parses user input.
 */
public class Parser {
    private static final Set<String> VALID_COMMANDS = new HashSet<>(Arrays.asList("bye", "list", "mark", "unmark", "todo", "deadline", "event", "delete", "find"));
    public static Stack<HistoryTask> historyCmds = new Stack<>();

    /**
     * Parses user input into command for execution.
     *
     * @param input full user input string
     * @return the command based on the user input
     */
    public static Command parseCommand(String input) throws DukeException {
        String[] inputContent = input.split(" ", 2);
        String mainCommand = inputContent[0];
        int taskNum; //for mark, unmark and delete
        switch (mainCommand) {
        case "bye":
            return new ByeCommand();
        case "help":
            return new HelpCommand();
        case "list":
            return new ListCommand();
        case "undo":
            try {
                HistoryTask latestCmd = historyCmds.pop();
                return new UndoCommand(latestCmd);
            } catch (EmptyStackException e) {
                throw new DukeException("OOPS!! You didn't do any thing!");
            }
        case "mark":
            try {
                taskNum = Integer.parseInt(inputContent[1]) - 1;
                return new MarkCommand(input, taskNum, true);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("OOPS!! Please tell me mark which one!");
            }
        case "unmark":
            try {
                taskNum = Integer.parseInt(inputContent[1]) - 1;
                return new MarkCommand(input, taskNum, false);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("OOPS!! Please tell me mark which one!");
            }
        case "todo":
            ToDo toDoTask;
            try {
                toDoTask = new ToDo(inputContent[1]);
                pushHistoryCmd(new HistoryTask(input, null));
                return new ToDoCommand(toDoTask);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("What do you want to do?");
            }
        case "deadline":
            Deadline ddlTask;
            try {
                String[] ddlInfo = inputContent[1].split(" /by ", 2);
                String ddlName = ddlInfo[0];
                String ddlTime = ddlInfo[1];
                ddlTask = new Deadline(ddlName, ddlTime);
                pushHistoryCmd(new HistoryTask(input, null));
                return new DeadlineCommand(ddlTask);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("What is the deadline?");
            }
        case "event":
            Event evtTask;
            try {
                String[] evtInfo = inputContent[1].split(" /");
                String evtName = evtInfo[0];
                String evtFrom = evtInfo[1].split("from ")[1];
                String evtTo = evtInfo[2].split("to ")[1];
                evtTask = new Event(evtName, evtFrom, evtTo);
                pushHistoryCmd(new HistoryTask(input, null));
                return new EventCommand(evtTask);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("What is the event?");
            }
        case "delete":
            try {
                taskNum = Integer.parseInt(inputContent[1]) - 1;
                return new DeleteCommand(input, taskNum);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("I haven't record this detective.task!");
            }
        case "find":
            return new FindCommand(inputContent[1]);
        default:
            throw new DukeException("OOPS!! Sorry, but I don't know what that means. qwq");
        }
    }

    public static void pushHistoryCmd(HistoryTask historyTask) {
        String[] inputContent = historyTask.getCommand().split(" ", 2);
        String mainCommand = inputContent[0];
        if (VALID_COMMANDS.contains(mainCommand)) {
            historyCmds.push(historyTask);
        }
    }
}
