package gulie;

import gulie.task.Deadline;
import gulie.task.Event;
import gulie.task.Task;
import gulie.task.Todo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * A parser for Gulie.
 */
public class GulieParser {

    /**
     * Parses a Command from an input String.
     * @param input
     * @param silent If nothing should be outputed on the ui.
     * @return
     * @throws GulieException
     */
    public Command parse(String input, boolean silent) throws GulieException {
        if (input.contains("\t")) {
            throw new GulieException("Invalid input. The use of tabs are not allowed.");
        }
        switch (input.split(" ")[0]) {
        case "list": {
            return (ui, storage, tasklist) -> {
                if (!silent) {
                    ui.printList(tasklist);
                }
            };
        }
        case "mark": {
            try {
                final int index = Integer.parseInt(getArgument(input, "mark", "index")) - 1;
                return (ui, storage, tasklist) -> {
                    tasklist.mark(index);
                    if (!silent) {
                        ui.printMark(tasklist.get(index));
                    }
                };
            } catch (NumberFormatException e) {
                throw new GulieException("Index provided must be an integer.");
            }
        }
        case "unmark": {
            try {
                final int index = Integer.parseInt(getArgument(input, "unmark", "index")) - 1;
                return (ui, storage, tasklist) -> {
                    tasklist.unmark(index);
                    if (!silent) {
                        ui.printUnmark(tasklist.get(index));
                    }
                };
            } catch (NumberFormatException e) {
                throw new GulieException("Index provided must be an integer.");
            }
        }
        case "todo": {
            final String name = getArgument(input, "todo", "name");
            return (ui, storage, tasklist) -> {
                Todo todo = new Todo(name);
                tasklist.add(todo);
                if (!silent) {
                    ui.printStore(todo, tasklist.size());
                }
            };
        }
        case "deadline": {
            try {
                final String name = getArgument(input, "deadline", "name");
                final LocalDateTime by = LocalDateTime.parse(getArgument(input, "/by", "deadline"));
                return (ui, storage, tasklist) -> {
                    Deadline deadline = new Deadline(name, by);
                    tasklist.add(deadline);
                    if (!silent) {
                        ui.printStore(deadline, tasklist.size());
                    }
                };
            } catch (DateTimeParseException e) {
                throw new GulieException("The datetime that you have given is invalid.");
            }
        }
        case "event": {
            try {
                final String name = getArgument(input, "event", "name");
                final LocalDateTime from = LocalDateTime.parse(getArgument(input, "/from", "start"));
                final LocalDateTime to = LocalDateTime.parse(getArgument(input, "/to", "end"));
                return (ui, storage, tasklist) -> {
                    Event event = new Event(name, from, to);
                    tasklist.add(event);
                    if (!silent) {
                        ui.printStore(event, tasklist.size());
                    }
                };
            } catch (DateTimeParseException e) {
                throw new GulieException("The datetime that you have given is invalid.");
            }
        }
        case "delete": {
            try {
                final int index = Integer.parseInt(getArgument(input, "delete", "index")) - 1;
                return (ui, storage, tasklist) -> {
                    Task task = tasklist.delete(index);
                    if (!silent) {
                        ui.printDelete(task, tasklist.size());
                    }
                };
            } catch (NumberFormatException e) {
                throw new GulieException("Index provided must be an integer.");
            }
        }
        case "find": {
            final String keyword = getArgument(input, "find", "keyword");
            return (ui, storage, tasklist) -> {
                ui.printFind(tasklist, tasklist.find(keyword));
            };
        }
        case "schedule": {
            try {
                final LocalDate date = LocalDate.parse(getArgument(input, "schedule", "date"));
                return (ui, storage, tasklist) -> {
                    ui.printSchedule(date, tasklist.getSchedule(date));
                };
            } catch (DateTimeParseException e) {
                throw new GulieException("The date that you have given is invalid.");
            }
        }
        default:
            throw new GulieException("Apologies. I do not understand.");
        }
    }

    /**
     * Parses a Command from an input String.
     * @param input
     * @return
     * @throws GulieException
     */
    public Command parse(String input) throws GulieException {
        return parse(input, false);
    }

    private static String getArgument(String inp, String arg) throws GulieException {
        return getArgument(inp, arg, arg.substring(1));
    }

    private static String getArgument(String inp, String arg, String argname) throws GulieException {
        arg = " " + arg + " ";
        inp = " " + inp;
        int i = inp.indexOf(arg);
        if (i == -1)
            throw new GulieException("You must provide the argument '" + argname + "'");
        String str = inp.substring(i + arg.length());
        if (str.indexOf(" /") == -1) {
            return str;
        } else {
            return str.substring(0, str.indexOf(" /"));
        }
    }
}
