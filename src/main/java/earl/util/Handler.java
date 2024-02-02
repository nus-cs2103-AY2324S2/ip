package earl.util;

import earl.exceptions.EarlException;
import earl.tasks.Deadline;
import earl.tasks.Event;
import earl.tasks.Todo;

public abstract class Handler {

    public abstract void handle(TaskList tasks, Ui ui) throws EarlException;

    public static Handler dispatch(String[] command) {
        switch (command[0]) {
        case "list":
            return new listHandler();
        case "mark":
            return new markHandler(command);
        case "unmark":
            return new unmarkHandler(command);
        case "todo":
            // fallthrough
        case "deadline":
            // fallthrough
        case "event":
            // fallthrough
            return new taskHandler(command);
        case "delete":
            return new deleteHandler(command);
        case "bye":
            return null;
        default:
            return new unknownHandler();
        }
    }

    private static final class listHandler extends Handler {
        public void handle(TaskList tasks, Ui ui) {
            if (!tasks.isEmpty()) {
                int n = tasks.getSize();
                String[] temp = new String[n];
                for (int i = 0; i < n; ++i) {
                    temp[i] = i + 1 + "." + tasks.get(i);
                }
                ui.makeResponse(temp);
            } else {
                ui.makeResponse("There is nothing to list.");
            }
        }
    }

    private static final class markHandler extends Handler {

        public final String[] COMMAND;

        public markHandler(String[] command) {
            COMMAND = command;
        }
        public void handle(TaskList tasks, Ui ui) throws EarlException {
            try {
                int idx = Parser.parseIndex(COMMAND[1]);
                if (tasks.mark(idx)) {
                    ui.makeResponse("Item marked as done.");
                } else {
                    ui.makeResponse("Item already marked as done.");
                }
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                throw new EarlException(
                        "Error, not a valid item number within range.\n"
                                + "\tExample use:\n\tmark 3");
            } catch (Exception e) {
                throw new EarlException("Error, unknown use of mark.\n"
                        + e.getMessage());
            }
        }
    }

    public static final class unmarkHandler extends Handler {
        private final String[] COMMAND;

        public unmarkHandler(String[] command) {
            COMMAND = command;
        }

        public void handle(TaskList tasks, Ui ui) throws EarlException {
            try {
                int idx = Parser.parseIndex(COMMAND[1]);
                if (tasks.unmark(idx)) {
                    ui.makeResponse("Item marked as not done.");
                } else {
                    ui.makeResponse("Item already marked as not done.");
                }
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                throw new EarlException(
                        "Error, not a valid item number within range.\n"
                                + "\tExample use:\n\tunmark 3");
            } catch (Exception e) {
                throw new EarlException("Error, unknown use of unmark.\n"
                        + e.getMessage());
            }
        }
    }

    public static final class taskHandler extends Handler {
        
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
                // impossible due to previous switch case
            }
        }
    }

    public static final class deleteHandler extends Handler {
        private final String[] COMMAND;

        public deleteHandler(String[] command) {
            COMMAND = command;
        }

        public void handle(TaskList tasks, Ui ui) throws EarlException {
            try {
                int idx = Parser.parseIndex(COMMAND[1]);
                ui.makeResponse("Item deleted.",
                        "\t" + tasks.delete(idx));
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                throw new EarlException(
                        "Error, not a valid item number within range.\n"
                                + "\tExample use:\n\tdelete 3");
            } catch (Exception e) {
                throw new EarlException("Error, unknown use of delete.\n"
                        + e.getMessage());
            }
        }
    }

    public static final class unknownHandler extends Handler {
        public void handle(TaskList task, Ui ui) {
            ui.makeResponse("Error, unknown command.",
                    "Valid commands:",
                    "\tlist, mark, unmark, todo, deadline, event, delete");
        }
    }
}
