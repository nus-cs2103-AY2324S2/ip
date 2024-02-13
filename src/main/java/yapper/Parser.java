package yapper;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import exception.YapperException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

/**
 * Handles interpreting strings for commands and task list data.
 */
public class Parser {
    private final TaskList mainTasks;
    private final Ui ui;
    private FileManager fm;

    /**
     * Initialises a Parser that corresponds to a main {@link TaskList} and {@link Ui}.
     *
     * @param mainTasks Corresponding main {@link TaskList}.
     * @param ui Corresponding {@link Ui}.
     */
    public Parser(TaskList mainTasks, Ui ui) {
        this.mainTasks = mainTasks;
        this.ui = ui;
    }

    public void setFileManager(FileManager fileManager) {
        fm = fileManager;
    }

    /**
     * Takes parsed command and calls the related functions.
     * Further parses argument String as required by the identified command.
     *
     * @throws YapperException Thrown when invalid command, arguments, index or formatting detected.
     */
    public String parseCommand(String input) throws YapperException {
        String[] cmdArg = input.trim().split(" ", 2); // [command, arguments]
        Yapper.Command cmd = Yapper.Command.valueOfCommandName(cmdArg[0]);
        String response = "";

        // invalid command
        if (cmd == null) {
            throw (new YapperException("What is blud yappin'? Here's the legit commands:\n"
                    + "list, todo, deadline, event, mark, unmark, delete, find, bye"));
        }

        // Commands create a Command class in the future
        switch (cmd) {
        case BYE:
            try {
                fm.saveTasks();
            } catch (YapperException y) {
                response = y.getMessage();
            } finally {
                response += ui.byeMessage();
            }
            break;
        case LIST:
            response = mainTasks.listTasks();
            break;
        case FIND:
            // empty find
            if (cmdArg.length != 2) {
                throw (new YapperException("What is lil bro looking for?"));
            }

            response = mainTasks.find(cmdArg[1]); // can be expanded to change current TaskList to look at found Task List
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
                    if (i > mainTasks.listSize()) {
                        throw (new YapperException("You ain't got that many tasks bruh!"));
                    } else if (i < 1) { // incorrect index
                        throw (new YapperException("Start from task 1 lil bro!"));
                    }

                    // Execute MARK/UNMARK/DELETE
                    if (cmdArg[0].equals("mark")) {
                        response = mainTasks.markTask(i);
                    } else if (cmdArg[0].equals("unmark")) {
                        response = mainTasks.unmarkTask(i);
                    } else {
                        response = mainTasks.deleteTask(i);
                    }
                } catch (java.lang.NumberFormatException e) { // non number typed
                    throw (new YapperException("Ain't no way! We lackin' just numbers after mark/unmark/delete.\n"
                            + "e.g. unmark 2"));
                } catch (YapperException e) {
                    throw (e);
                }
            } else { // no arguments
                throw (new YapperException("Ain't no way! Which task in the list we vibin' with?\n"
                        + "e.g. mark/unmark/delete 1"));
            }
            break;
        case TODO:
            if (cmdArg.length != 2) { // no arguments
                throw (new YapperException("Ain't no way! You got caught lackin' the format!\n"
                        + "e.g. todo <task>"));
            }
            response = parseTask(cmdArg[1], Task.ID.TODO);
            break;
        case DEADLINE:
            if (cmdArg.length != 2) { // no arguments
                throw (new YapperException("Ain't no way! You got caught lackin' the format!\n"
                        + "e.g. deadline <task> /by <date/time>"));
            }
            response = parseTask(cmdArg[1], Task.ID.DEADLINE);
            break;
        case EVENT:
            if (cmdArg.length != 2) { // no arguments
                throw (new YapperException("Ain't no way! You got caught lackin' the format!\n"
                        + "e.g. event <task> /from <start date/time> /to <start date/time>"));
            }
            response = parseTask(cmdArg[1], Task.ID.EVENT);
            break;
        default: // Shouldn't reach here, invalid commands should be null
            throw (new YapperException("What is blud yappin'? Here's the legit commands:"
                    + "list, todo, deadline, event, mark, unmark, delete, find, bye"));
        }
        return response;
    }

    /**
     * Parses arguments for their corresponding {@link Task} type for initialising and adding to {@link TaskList}.
     *
     * @param arg Parsed arguments to be split based on identified {@link Task} type.
     * @param id Type of {@link Task} identified.
     * @throws YapperException Incorrect types of formatting detected.
     */
    public String parseTask(String arg, Task.ID id) throws YapperException {
        String response;
        switch (id) {
        case TODO:
            Todo todo = new Todo(arg);
            response = mainTasks.addTask(todo);
            break;
        case DEADLINE: {
            String[] descDate = arg.split(" /by ", 2); // [description, by]
            if (descDate.length != 2) { // incorrect formatting for /by
                throw (new YapperException("When you wanna do this task by lil bro?\n"
                        + "type deadline <task> /by <yyyy-mm-dd>\n"
                        + "e.g. deadline hit the griddy by 2024-12-31"));
            }

            try {
                LocalDate deadlineBy = LocalDate.parse(descDate[1]);
                Deadline deadline = new Deadline(descDate[0], deadlineBy);
                response = mainTasks.addTask(deadline);
            } catch (DateTimeParseException e) { // incorrect formatting for date
                throw (new YapperException("When you wanna do this task by lil bro?\n"
                        + "type deadline <task> /by <yyyy-mm-dd>\n"
                        + "e.g. deadline hit the griddy by 2024-12-31"));
            }
            break;
        }
        case EVENT: {
            String[] descDate = arg.split(" /from ", 2); // [description, fromTo]

            // incorrect formatting for /from
            if (descDate.length != 2) {
                throw (new YapperException("When does this event start lil bro?\n"
                        + "type event <task> /from <yyyy-mm-dd> /to <yyyy-mm-dd>\n"
                        + "e.g. event party rock /from <yyyy-mm-dd> /to <yyyy-mm-dd>"));
            }

            String[] fromTo = descDate[1].split(" /to ", 2); // [from , to]

            // incorrect formatting for /to
            if (fromTo.length != 2) {
                throw (new YapperException("When does this event end lil bro?\n"
                        + "type event <task> /from <yyyy-mm-dd> /to <yyyy-mm-dd>\n"
                        + "e.g. event party rock /from <yyyy-mm-dd> /to <yyyy-mm-dd>"));
            }

            try {
                LocalDate eventFrom = LocalDate.parse(fromTo[0]);
                LocalDate eventTo = LocalDate.parse(fromTo[1]);
                Event event = new Event(descDate[0], eventFrom, eventTo);
                response = mainTasks.addTask(event);
            } catch (DateTimeParseException e) {
                throw (new YapperException("When does this event start/end lil bro?\n"
                        + "type event <task> /from <yyyy-mm-dd> /to <yyyy-mm-dd>\n"
                        + "e.g. event party rock /from <yyyy-mm-dd> /to <yyyy-mm-dd>"));
            }
            break;
        }
        default: // Invalid Task ID
            throw (new YapperException("Invalid Task ID, user shouldn't reach here"));
        }
        return response;
    }

    /**
     * Parses task list data to create and add tasks to the {@link TaskList}.
     *
     * @param data Task list data saved in the local file represented as a String.
     * @throws YapperException Data found in local file is detected to be incorrectly formatted.
     */
    public void parseData(String data) throws YapperException {
        String[] taskData = data.split("" + " / ");
        boolean isDone; // used for creating new Task
        switch (taskData[0]) {
        case "T":
            if (taskData.length != 3) {
                throw new YapperException("Error in the save files 1");
            }

            if (taskData[1].equals("0")) {
                isDone = false;
            } else if (taskData[1].equals("1")) {
                isDone = true;
            } else {
                throw new YapperException("Error in the save files 2");
            }

            mainTasks.addTaskNoMessage(new Todo(isDone, taskData[2]));
            break;
        case "D":
            if (taskData.length != 4) {
                throw new YapperException("Error in the save files 3");
            }

            if (taskData[1].equals("0")) {
                isDone = false;
            } else if (taskData[1].equals("1")) {
                isDone = true;
            } else {
                throw new YapperException("Error in the save files 4");
            }

            try {
                LocalDate by = LocalDate.parse(taskData[3]);
                mainTasks.addTaskNoMessage(new Deadline(isDone, taskData[2], by));
            } catch (DateTimeParseException e) {
                throw new YapperException("Error in the save files 5");
            }

            break;
        case "E":
            if (taskData.length != 5) {
                throw new YapperException("Error in the save files 6");
            }

            if (taskData[1].equals("0")) {
                isDone = false;
            } else if (taskData[1].equals("1")) {
                isDone = true;
            } else {
                throw new YapperException("Error in the save files 7");
            }
            try {
                LocalDate from = LocalDate.parse(taskData[3]);
                LocalDate to = LocalDate.parse(taskData[3]);
                mainTasks.addTaskNoMessage(new Event(isDone, taskData[2], from, to));
            } catch (DateTimeParseException e) {
                throw new YapperException("Error in the save files 8");
            }
            break;
        default:
            throw new YapperException("Error in the save files 9");
        }
    }

    /**
     * Parses all tasks in the {@link TaskList} into format to be saved in the local file.
     *
     * @return String representation of task list data to be saved to the local file.
     */
    public String parseToData() {
        String data = "";
        for (int i = 0; i < mainTasks.listSize(); i++) {
            Task task = mainTasks.getTask(i);

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
            } else if (task instanceof Event) {
                Event event = (Event) task;
                data += String.format("E / %d / %s / %s / %s",
                        task.getIsDoneInt(),
                        task.getDescription(),
                        event.getFrom(),
                        event.getTo());
            }

            // add new line after each task except for the last line
            if (i != mainTasks.listSize() - 1) {
                data += System.lineSeparator();
            }
        }
        return data;
    }
}
