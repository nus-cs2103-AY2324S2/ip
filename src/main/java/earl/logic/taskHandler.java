package earl.logic;

import earl.exceptions.EarlException;
import earl.tasks.Deadline;
import earl.tasks.Event;
import earl.tasks.Todo;
import earl.util.Parser;
import earl.util.TaskList;
import earl.util.Ui;

public final class taskHandler extends Handler {

    private final String[] COMMAND;

    public taskHandler(String[] command) {
        COMMAND = command;
    }

    public void handle(TaskList tasks, Ui ui) throws EarlException {
        switch (COMMAND[0]) {
        case "todo":
            try {
                tasks.add(new Todo(COMMAND[1]));
                ui.makeResponse("Added new todo.",
                        "\t" + tasks.get(tasks.getSize() - 1),
                        "There are " + tasks.getSize()
                                + " task(s) tracked.");
            } catch (IndexOutOfBoundsException e) {
                throw new EarlException("Error, missing task name.\n"
                        + "\tExample use:\n\ttodo <task_name>");
            } catch (Exception e) {
                throw new EarlException("Error, unknown use of todo.\n"
                        + e.getMessage());
            }
            break;
        case "deadline":
            try {
                String[] args = COMMAND[1].split("\\s/by\\s");
                tasks.add(new Deadline(args[0],
                        Parser.parseDateTime(args[1])));
                ui.makeResponse("Added new deadline.",
                        "\t" + tasks.get(tasks.getSize() - 1),
                        "There are " + tasks.getSize()
                                + " earl.tasks tracked.");
            } catch (IndexOutOfBoundsException e) {
                throw new EarlException(
                        "Error, invalid deadline format.\n"
                                + "\tExample use:\n\t"
                                + "\tdeadline <task_name> /by <end>");
            } catch (Exception e) {
                throw new EarlException(
                        "Error, unknown use of deadline.\n"
                                + e.getMessage());
            }
            break;
        case "event":
            try {
                String[] args = COMMAND[1].split("\\s/(from|to)\\s");
                tasks.add(new Event(args[0],
                        Parser.parseDateTime(args[1]),
                        Parser.parseDateTime(args[2])));
                ui.makeResponse("Added new event.",
                        "\t" + tasks.get(tasks.getSize() - 1),
                        "There are " + tasks.getSize()
                                + " earl.tasks tracked.");
            } catch (IndexOutOfBoundsException e) {
                throw new EarlException("Error, invalid event format.\n"
                        + "\tExample use:\n\t"
                        + "\tevent <task_name> /from <start> /to <end>");
            } catch (Exception e) {
                throw new EarlException("Error, unknown use of event.\n"
                        + e.getMessage());
            }
            break;
        default:
            throw new EarlException("Impossible error! Task handler invoked"
                    + " without valid task command.");
        }
    }
}
