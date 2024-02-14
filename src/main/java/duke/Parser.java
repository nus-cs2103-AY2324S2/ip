package duke;

import duke.command.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

/**
 * Parses user input.
 */
public class Parser {
    /**
     * Parses user input into command for execution.
     *
     * @param input full user input string
     * @return the command based on the user input
     */
    public static Command parseCommand(String input) throws DukeException {
        String[] inputContent = input.split(" ", 2);
        String mainCommand = inputContent[0];
        int taskNum; //for mark or unmark
        switch (mainCommand) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "mark":
            try {
                taskNum = Integer.parseInt(inputContent[1]) - 1;
                return new MarkCommand(taskNum, true);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("OOPS!! Please tell me mark which one!");
            }
        case "unmark":
            try {
                taskNum = Integer.parseInt(inputContent[1]) - 1;
                return new MarkCommand(taskNum, false);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("OOPS!! Please tell me mark which one!");
            }
        case "todo":
            ToDo toDoTask;
            try {
                toDoTask = new ToDo(inputContent[1]);
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
                return new EventCommand(evtTask);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("What is the event?");
            }
        case "delete":
            int deleteTaskNum;
            try {
                deleteTaskNum = Integer.parseInt(inputContent[1]) - 1;
                return new DeleteCommand(deleteTaskNum);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("I haven't record this detective.task!");
            }
        case "find":
            return new FindCommand(inputContent[1]);

        default:
            throw new DukeException("OOPS!! Sorry, but I don't know what that means. qwq");
        }
    }
}
