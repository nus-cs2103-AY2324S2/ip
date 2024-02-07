import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class GulieParser {

    public Command parse(String input, boolean silent) throws GulieException {
        if (input.indexOf("\t") != -1) {
            throw new GulieException("Invalid input. The use of tabs are not allowed.");
        }
        switch (input.split(" ")[0]) {
        case "list": {
            return new Command() {
                @Override
                public void run(GulieUi ui, GulieStorage storage, GulieTasklist tasklist) throws GulieException {
                    if (!silent) {
                        ui.list(tasklist);
                    }
                }
            };
        }
        case "mark": {
            try {
                final int index = Integer.parseInt(getArgument(input, "mark", "index")) - 1;
                return new Command() {
                    @Override
                    public void run(GulieUi ui, GulieStorage storage, GulieTasklist tasklist) throws GulieException {
                        tasklist.mark(index);
                        if (!silent) {
                            ui.mark(tasklist.get(index));
                        }
                    }
                };
            } catch (NumberFormatException e) {
                throw new GulieException("Index provided must be an integer.");
            }
        }
        case "unmark": {
            try {
                final int index = Integer.parseInt(getArgument(input, "unmark", "index")) - 1;
                return new Command() {
                    @Override
                    public void run(GulieUi ui, GulieStorage storage, GulieTasklist tasklist) throws GulieException {
                        tasklist.unmark(index);
                        if (!silent) {
                            ui.unmark(tasklist.get(index));
                        }
                    }
                };
            } catch (NumberFormatException e) {
                throw new GulieException("Index provided must be an integer.");
            }
        }
        case "todo": {
            final String name = getArgument(input, "todo", "name");
            return new Command() {
                @Override
                public void run(GulieUi ui, GulieStorage storage, GulieTasklist tasklist) throws GulieException {
                    Todo todo = new Todo(name);
                    tasklist.add(todo);
                    if (!silent) {
                        ui.store(todo, tasklist.size());
                    }
                }
            };
        }
        case "deadline": {
            try {
                final String name = getArgument(input, "deadline", "name");
                final LocalDateTime by = LocalDateTime.parse(getArgument(input, "/by"));
                return new Command() {
                    @Override
                    public void run(GulieUi ui, GulieStorage storage, GulieTasklist tasklist) throws GulieException {
                        Deadline deadline = new Deadline(name, by);
                        tasklist.add(deadline);
                        if (!silent) {
                            ui.store(deadline, tasklist.size());
                        }
                    }
                };
            } catch (DateTimeParseException e) {
                throw new GulieException("The datetime that you have given is invalid.");
            }
        }
        case "event": {
            try {
                final String name = getArgument(input, "event", "name");
                final LocalDateTime from = LocalDateTime.parse(getArgument(input, "/from"));
                final LocalDateTime to = LocalDateTime.parse(getArgument(input, "/to"));
                return new Command() {
                    @Override
                    public void run(GulieUi ui, GulieStorage storage, GulieTasklist tasklist) throws GulieException {
                        Event event = new Event(name, from, to);
                        tasklist.add(event);
                        if (!silent) {
                            ui.store(event, tasklist.size());
                        }
                    }
                };
            } catch (DateTimeParseException e) {
                throw new GulieException("The datetime that you have given is invalid.");
            }
        }
        case "delete": {
            try {
                final int index = Integer.parseInt(getArgument(input, "delete", "index")) - 1;
                return new Command() {
                    @Override
                    public void run(GulieUi ui, GulieStorage storage, GulieTasklist tasklist) throws GulieException {
                        Task task = tasklist.delete(index);
                        if (!silent) {
                            ui.delete(task, tasklist.size());
                        }
                    }
                };
            } catch (NumberFormatException e) {
                throw new GulieException("Index provided must be an integer.");
            }
        }
        default:
            throw new GulieException("Apologies. I do not understand.");
        }
    }

    public Command parse(String input) throws GulieException {
        return this.parse(input, false);
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
