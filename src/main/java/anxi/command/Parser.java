package anxi.command;

import anxi.handlers.DeadlineHandler;
import anxi.handlers.DeleteHandler;
import anxi.handlers.EventHandler;
import anxi.handlers.FindHandler;
import anxi.handlers.MarkHandler;
import anxi.handlers.ToDoHandler;
import anxi.handlers.UnmarkHandler;
import anxi.handlers.ViewHandler;

/**
 * Handles reading user input and calling the relevant commands.
 */
public class Parser {

    /**
     * Parser constructor.
     */
    public Parser() {
    }

    /**
     * Parses user input and calls the relevant commands.
     *
     * @param input             User command input.
     * @param storage           Storage instance.
     * @param taskList          TaskList instance.
     * @param ui                Ui instance.
     * @return uiString         Result of parsing user input.
     */
    public String parseInput(String input, Storage storage, TaskList taskList, Ui ui) {
        input = input + " ";
        String[] command = input.split(" ", 2);

        switch (command[0].toLowerCase()) {
        case "bye":
            return ui.printExitMessage();

        case "list":
            return ui.printTaskList(taskList.printTaskList());

        case "mark":
            MarkHandler mh = new MarkHandler();
            return mh.markTask(command[1], storage, taskList, ui);

        case "unmark":
            UnmarkHandler uh = new UnmarkHandler();
            return uh.unmarkTask(command[1], storage, taskList, ui);

        case "todo":
            ToDoHandler th = new ToDoHandler();
            return th.addToDo(command[1], storage, taskList, ui);

        case "event":
            EventHandler eh = new EventHandler();
            return eh.addEvent(command[1], storage, taskList, ui);

        case "deadline":
            DeadlineHandler dh = new DeadlineHandler();
            return dh.addDeadline(command[1], storage, taskList, ui);

        case "delete":
            DeleteHandler deleteHandler = new DeleteHandler();
            return deleteHandler.deleteTask(command[1], storage, taskList, ui);

        case "find":
            FindHandler fh = new FindHandler();
            return fh.findTask(command[1], taskList, ui);

        case "view":
            ViewHandler vh = new ViewHandler();
            return vh.viewSchedule(command[1], taskList, ui);

        default:
            return ui.printUnknownCommandError();
        }
    }
}
