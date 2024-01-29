public class Parser {
    private static final String INDENT = "  ";

    public static void parseCommand(String[] cmdArg) throws DukeException {
        Duke.Command cmd = Duke.Command.valueOfCommandName(cmdArg[0]);

        // invalid command
        if (cmd == null) {
            throw (new DukeException(INDENT + "What is blud yappin'? Here's the legit commands:\n"
                    + INDENT + "list, todo, deadline, event, mark, unmark, delete, bye"));
        }

        // Commands create a Command class in the future
        switch (cmd) {
        case BYE:
            Duke.bye();
            break;
        case LIST:
            Duke.listTasks();
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
                    if (i > Duke.tasks.size()) {
                        throw (new DukeException(INDENT + "You ain't got that many tasks bruh!"));
                    } else if (i < 1) { // incorrect index
                        throw (new DukeException(INDENT + "Start from task 1 lil bro!"));
                    }

                    // Execute MARK/UNMARK/DELETE
                    if (cmdArg[0].equals("mark")) {
                        Duke.markTask(i);
                    } else if (cmdArg[0].equals("unmark")) {
                        Duke.unmarkTask(i);
                    } else {
                        Duke.deleteTask(i);
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
            addTask(cmdArg[1], Task.ID.TODO);
            break;
        case DEADLINE:
            if (cmdArg.length != 2) { // no arguments
                throw (new DukeException(INDENT + "Ain't no way! You got caught lackin' the format!\n"
                        + INDENT + "e.g. deadline <task> /by <date/time>"));
            }
            addTask(cmdArg[1], Task.ID.DEADLINE);
            break;
        case EVENT:
            if (cmdArg.length != 2) { // no arguments
                throw (new DukeException(INDENT + "Ain't no way! You got caught lackin' the format!\n"
                        + INDENT + "e.g. event <task> /from <start date/time> /to <start date/time>"));
            }
            addTask(cmdArg[1], Task.ID.EVENT);
            break;
        default: // Shouldn't reach here, invalid commands should be null
            throw (new DukeException(INDENT + "What is blud yappin'? Here's the legit commands:"
                    + INDENT + "list, todo, deadline, event, mark, unmark, delete, bye"));
        }
    }

    // add task according to what type they are
    public static void addTask(String arg, Task.ID id) throws DukeException {
        switch (id) {
        case TODO:
            Todo todo = new Todo(arg);
            Duke.tasks.add(todo);
            System.out.println(INDENT + "Ayo new task just dropped:\n  " + INDENT + todo);
            System.out.println(INDENT + "Yo, we're " + Duke.tasks.size()
                    + " task(s) deep! Let's keep this SIGMA GRINDSET!");
            break;
        case DEADLINE: {
            String[] descTime = arg.split(" /by "); // [description, by]
            if (descTime.length == 2) {
                Deadline deadline = new Deadline(descTime[0], descTime[1]);
                Duke.tasks.add(deadline);
                System.out.println(INDENT + "Ayo new task just dropped:\n  " + INDENT + deadline);
                System.out.println(INDENT + "Yo, we're " + Duke.tasks.size()
                        + " task(s) deep! Let's keep this SIGMA GRINDSET!");
            } else { // incorrect formatting for /by
                throw (new DukeException(INDENT + "When you wanna do this task by lil bro?\n"
                        + INDENT + "e.g. deadline <task> /by <date/time>"));
            }
            break;
        }
        case EVENT: {
            String[] descTime = arg.split(" /from "); // [description, fromTo]
            if (descTime.length == 2) {
                String[] fromTo = descTime[1].split(" /to "); // [from , to]
                if (fromTo.length == 2) {
                    Event event = new Event(descTime[0], fromTo[0], fromTo[1]);
                    Duke.tasks.add(event);
                    System.out.println(INDENT + "Ayo new task just dropped:\n  " + INDENT + event);
                    System.out.print(INDENT + "Yo, we're " + Duke.tasks.size()
                            + " task(s) deep! Let's keep this SIGMA GRINDSET!\n");
                } else { // incorrect formatting for /to
                    throw (new DukeException(INDENT + "When does this event end lil bro?\n"
                            + INDENT + "e.g. event <task> /from <start date/time> /to <start date/time>"));
                }
            } else { // incorrect formatting for /from
                throw (new DukeException(INDENT + "When does this event start lil bro?\n"
                        + INDENT + "e.g. event <task> /from <start date/time> /to <start date/time>"));
            }
            break;
        }
        default: // Invalid Task ID
            throw (new DukeException("Invalid Task ID, user shouldn't reach here"));
        }
    }

    public static void addTask(Task task) {
        Duke.tasks.add(task);
    }

    public static void parseData(String data) throws DukeException {
        String[] taskData = data.split("|");
        boolean isDone; // used for creating new Task
        switch (taskData[0]) {
        case "T":
            if (taskData.length != 3) {
                throw new DukeException("Error in the save files");
            }

            if (taskData[1].equals("0")) {
                isDone = false;
            } else if (taskData[1].equals("1")) {
                isDone = true;
            } else {
                throw new DukeException("Error in the save files");
            }

            addTask(new Todo(isDone, taskData[2]));
            break;
        case "D":
            if (taskData.length != 4) {
                throw new DukeException("Error in the save files");
            }

            if (taskData[1].equals("0")) {
                isDone = false;
            } else if (taskData[1].equals("1")) {
                isDone = true;
            } else {
                throw new DukeException("Error in the save files");
            }

            addTask(new Deadline(isDone, taskData[2], taskData[3]));
            break;
        case "E":
            if (taskData.length != 5) {
                throw new DukeException("Error in the save files");
            }

            if (taskData[1].equals("0")) {
                isDone = false;
            } else if (taskData[1].equals("1")) {
                isDone = true;
            } else {
                throw new DukeException("Error in the save files");
            }

            addTask(new Event(isDone, taskData[2], taskData[3], taskData[4]));
            break;
        default:
            throw new DukeException("Error in the save files");
        }
    }
}
