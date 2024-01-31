import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {
    private static final String INDENT = "  ";

    public static void parseCommand(String[] cmdArg) throws DukeException {
        Yapper.Command cmd = Yapper.Command.valueOfCommandName(cmdArg[0]);

        // invalid command
        if (cmd == null) {
            throw (new DukeException(INDENT + "What is blud yappin'? Here's the legit commands:\n"
                    + INDENT + "list, todo, deadline, event, mark, unmark, delete, bye"));
        }

        // Commands create a Command class in the future
        switch (cmd) {
        case BYE:
            Yapper.bye();
            break;
        case LIST:
            Yapper.listTasks();
            break;
        case MARK:
            // Fallthrough
        case UNMARK:
            // Fallthrough
        case DELETE:
            if (cmdArg.length == 2) {
                try {
                    int i = Integer.parseInt(cmdArg[1]);

                    // incorrect index
                    if (i > Yapper.tasks.size()) {
                        throw (new DukeException(INDENT + "You ain't got that many tasks bruh!"));
                    } else if (i < 1) { // incorrect index
                        throw (new DukeException(INDENT + "Start from task 1 lil bro!"));
                    }

                    // Execute MARK/UNMARK/DELETE
                    if (cmdArg[0].equals("mark")) {
                        Yapper.markTask(i);
                    } else if (cmdArg[0].equals("unmark")) {
                        Yapper.unmarkTask(i);
                    } else {
                        Yapper.deleteTask(i);
                    }
                } catch (java.lang.NumberFormatException e) { // non number typed
                    throw (new DukeException(INDENT
                            + "Ain't no way! We lackin' just numbers after mark/unmark/delete.\n"
                            + INDENT + "e.g. unmark 2"));
                } catch (DukeException e) {
                    throw (e);
                }
            } else { // no arguments
                throw (new DukeException(INDENT + "Ain't no way! Which task in the list we vibin' with?\n"
                        + INDENT + "e.g. mark/unmark/delete 1"));
            }
            break;
        case TODO:
            if (cmdArg.length != 2) { // no arguments
                throw (new DukeException(INDENT + "Ain't no way! You got caught lackin' the format!\n"
                        + INDENT + "e.g. todo <task>"));
            }
            parseTask(cmdArg[1], Task.ID.TODO);
            break;
        case DEADLINE:
            if (cmdArg.length != 2) { // no arguments
                throw (new DukeException(INDENT + "Ain't no way! You got caught lackin' the format!\n"
                        + INDENT + "e.g. deadline <task> /by <date/time>"));
            }
            parseTask(cmdArg[1], Task.ID.DEADLINE);
            break;
        case EVENT:
            if (cmdArg.length != 2) { // no arguments
                throw (new DukeException(INDENT + "Ain't no way! You got caught lackin' the format!\n"
                        + INDENT + "e.g. event <task> /from <start date/time> /to <start date/time>"));
            }
            parseTask(cmdArg[1], Task.ID.EVENT);
            break;
        default: // Shouldn't reach here, invalid commands should be null
            throw (new DukeException(INDENT + "What is blud yappin'? Here's the legit commands:"
                    + INDENT + "list, todo, deadline, event, mark, unmark, delete, bye"));
        }
    }

    // add task according to what type they are
    public static void parseTask(String arg, Task.ID id) throws DukeException {
        switch (id) {
        case TODO:
            Todo todo = new Todo(arg);
            Yapper.tasks.add(todo);
            System.out.println(INDENT + "Ayo new task just dropped:\n  " + INDENT + todo);
            System.out.println(INDENT + "Yo, we're " + Yapper.tasks.size()
                    + " task(s) deep! Let's keep this SIGMA GRINDSET!");
            break;
        case DEADLINE: {
            String[] descDate = arg.split(" /by ", 2); // [description, by]
            if (descDate.length != 2) { // incorrect formatting for /by
                throw (new DukeException(INDENT + "When you wanna do this task by lil bro?\n"
                        + INDENT + "type deadline <task> /by <yyyy-mm-dd>\n"
                        + INDENT + "e.g. deadline hit the griddy by 2024-12-31"));
            }

            try {
                LocalDate deadlineBy = LocalDate.parse(descDate[1]);
                Deadline deadline = new Deadline(descDate[0], deadlineBy);
                Yapper.tasks.add(deadline);
                System.out.println(INDENT + "Ayo new task just dropped:\n  " + INDENT + deadline);
                System.out.println(INDENT + "Yo, we're " + Yapper.tasks.size()
                        + " task(s) deep! Let's keep this SIGMA GRINDSET!");
            } catch (DateTimeParseException e) { // incorrect formatting for date
                throw (new DukeException(INDENT + "When you wanna do this task by lil bro?\n"
                        + INDENT + "type deadline <task> /by <yyyy-mm-dd>\n"
                        + INDENT + "e.g. deadline hit the griddy by 2024-12-31"));
            }
            break;
        }
        case EVENT: {
            String[] descDate = arg.split(" /from ", 2); // [description, fromTo]

            // incorrect formatting for /from
            if (descDate.length != 2) {
                throw (new DukeException(INDENT + "When does this event start lil bro?\n"
                        + INDENT + "type event <task> /from <yyyy-mm-dd> /to <yyyy-mm-dd>\n"
                        + INDENT + "e.g. event party rock /from <yyyy-mm-dd> /to <yyyy-mm-dd>"));
            }

            String[] fromTo = descDate[1].split(" /to ", 2); // [from , to]

            // incorrect formatting for /to
            if (fromTo.length != 2) {
                throw (new DukeException(INDENT + "When does this event end lil bro?\n"
                        + INDENT + "type event <task> /from <yyyy-mm-dd> /to <yyyy-mm-dd>\n"
                        + INDENT + "e.g. event party rock /from <yyyy-mm-dd> /to <yyyy-mm-dd>"));
            }

            try {
                LocalDate eventFrom = LocalDate.parse(fromTo[0]);
                LocalDate eventTo = LocalDate.parse(fromTo[1]);
                Event event = new Event(descDate[0], eventFrom, eventTo);
                Yapper.tasks.add(event);
                System.out.println(INDENT + "Ayo new task just dropped:\n  " + INDENT + event);
                System.out.print(INDENT + "Yo, we're " + Yapper.tasks.size()
                        + " task(s) deep! Let's keep this SIGMA GRINDSET!\n");
            } catch (DateTimeParseException e) {
                throw (new DukeException(INDENT + "When does this event start/end lil bro?\n"
                        + INDENT + "type event <task> /from <yyyy-mm-dd> /to <yyyy-mm-dd>\n"
                        + INDENT + "e.g. event party rock /from <yyyy-mm-dd> /to <yyyy-mm-dd>"));
            }
            break;
        }
        default: // Invalid Task ID
            throw (new DukeException("Invalid Task ID, user shouldn't reach here"));
        }
    }

    // for Storage class later on
    public static void parseTask(Task task) {
        Yapper.tasks.add(task);
    }

    public static void parseData(String data) throws DukeException {
        String[] taskData = data.split("" + " / ");
        boolean isDone; // used for creating new Task
        switch (taskData[0]) {
        case "T":
            if (taskData.length != 3) {
                for (String s : taskData) {
                    System.out.println(s);
                }
                throw new DukeException("Error in the save files 1");
            }

            if (taskData[1].equals("0")) {
                isDone = false;
            } else if (taskData[1].equals("1")) {
                isDone = true;
            } else {
                throw new DukeException("Error in the save files 2");
            }

            parseTask(new Todo(isDone, taskData[2]));
            break;
        case "D":
            if (taskData.length != 4) {
                throw new DukeException("Error in the save files 3");
            }

            if (taskData[1].equals("0")) {
                isDone = false;
            } else if (taskData[1].equals("1")) {
                isDone = true;
            } else {
                throw new DukeException("Error in the save files 4");
            }

            try {
                LocalDate by = LocalDate.parse(taskData[3]);
                parseTask(new Deadline(isDone, taskData[2], by));
            } catch (DateTimeParseException e) {
                throw new DukeException("Error in the save files 5");
            }

            break;
        case "E":
            if (taskData.length != 5) {
                throw new DukeException("Error in the save files 6");
            }

            if (taskData[1].equals("0")) {
                isDone = false;
            } else if (taskData[1].equals("1")) {
                isDone = true;
            } else {
                throw new DukeException("Error in the save files 7");
            }
            try {
                LocalDate from = LocalDate.parse(taskData[3]);
                LocalDate to = LocalDate.parse(taskData[3]);
                parseTask(new Event(isDone, taskData[2], from, to));
            } catch (DateTimeParseException e) {
                throw new DukeException("Error in the save files 8");
            }
            break;
        default:
            throw new DukeException("Error in the save files 9");
        }
    }

    public static String parseToData() {
        String data = "";
        for (int i = 0; i < Yapper.tasks.size(); i++) {
            Task task = Yapper.tasks.get(i);

            if (task instanceof Todo) {
                data += String.format("T / %d / %s",
                        task.getIsDoneInt(),
                        task.getDescription());
            } else if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                data += String.format("D / %d / %s / %s",
                        task.getIsDoneInt(),
                        task.getDescription(),
                        deadline.getBy());
            } else { // instanceof Event
                Event event = (Event) task;
                data += String.format("E / %d / %s / %s / %s",
                        task.getIsDoneInt(),
                        task.getDescription(),
                        event.getFrom(),
                        event.getTo());
            }

            // add new line after each task except for the last line
            if (i != Yapper.tasks.size() - 1) {
                data += System.lineSeparator();
            }
        }
        return data;
    }
}
