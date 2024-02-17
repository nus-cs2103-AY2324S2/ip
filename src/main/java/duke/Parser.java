package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Parser {
    public Parser() {

    }

    public enum Command {
        BYE, LIST, UNMARK, MARK, DELETE, TODO, DEADLINE, EVENT, UNKNOWN, FIND;
        public static Command getCategory(String input) {
            try {
                return Command.valueOf(input.toUpperCase());
            } catch (IllegalArgumentException e) {
                return UNKNOWN;
            }
        }
    }

    /**
     * Parses the command and executes command related tasks.
     *
     * @param input Command.
     * @param taskList The list.
     * @param ui The ui.
     * @throws DukeException if invalid command or format has been parsed.
     */
    public static String parseCommand(String input, TaskList taskList, Ui ui) throws DukeException {
        Command category = Command.getCategory(input);
        int listSize = taskList.getSize();
        switch (category) {
            case BYE:
                return ui.printGoodByeMessage();
            case LIST:
                return ui.listTaskMessage(taskList);
            case UNMARK:
                int unmarkId = ui.readInt() - 1;
                if (unmarkId < 0 || unmarkId >= listSize) {
                    try {
                        throw new DukeException("Sorry, " +
                                "please select a valid task for me to unmark!");
                    } catch (DukeException e) {
                        return "  " + e.getMessage();
                    }
                }
                return taskList.unmarkTask(unmarkId, ui);
            case MARK:
                int markId = ui.readInt() - 1;
                if (markId < 0 || markId >= listSize) {
                    try {
                        throw new DukeException("Sorry, " +
                                "please select a valid task for me to mark!");
                    } catch (DukeException e) {
                        return "  " + e.getMessage();
                    }
                }
                return taskList.markTask(markId, ui);
            case DELETE:
                int deleteId = ui.readInt() - 1;
                if (deleteId < 0 || deleteId >= listSize) {
                    try {
                        throw new DukeException("Sorry, " +
                                "please select a valid task for me to delete!");
                    } catch (DukeException e) {
                        return "  " + e.getMessage();
                    }
                }
                StringBuilder output = new StringBuilder();
                output.append(taskList.deleteTask(deleteId, ui)).append("\n");
                output.append(ui.listSizeMessage(taskList));
                return output.toString();
            case TODO:
                return parseToDo(taskList, ui);
            case DEADLINE:
                return parseDeadline(taskList, ui);
            case EVENT:
                return parseEvent(taskList, ui);
            case FIND:
                String findDescription = ui.readCommandLine();
                ArrayList<Task> matchedTasks = taskList.find(findDescription);
                return taskList.listMatchedTasks(matchedTasks);
            default:
                try {
                    throw new DukeException("Sorry, I cannot understand what this is!");
                } catch (DukeException e) {
                    return "  " + e.getMessage();
                }
        }
    }

    /**
     * Parses toDo task.
     * @param taskList The list.
     * @param ui The Ui.
     */
    public static String parseToDo(TaskList taskList, Ui ui) {
        StringBuilder output = new StringBuilder();
        String toDoDescription = ui.readCommandLine();
        if (toDoDescription.isEmpty()) {
            try {
                throw new DukeException("Sorry, " +
                        "please give me a description of the todo as well! >.<\n" +
                        "  " + "Format should be todo (description)!");
            } catch (DukeException e) {
                return "  " + e.getMessage();
            }
        }
        ToDo toDo = new ToDo(toDoDescription);
        taskList.addTask(toDo);

        output.append("  ").append(toDo).append("\n");
        output.append(ui.listSizeMessage(taskList));
        return output.toString();
    }

    /**
     * Parses deadline task.
     * @param taskList The list.
     * @param ui The ui.
     */
    public static String parseDeadline(TaskList taskList, Ui ui) {
        StringBuilder output = new StringBuilder();
        String deadlineDescription = ui.readCommandLine();
        if (!deadlineDescription.contains(" /by ")) {
            try {
                throw new DukeException("Sorry, " +
                        "please give me a description of the deadline as well! >.<\n" +
                        "  " +
                        "Format should be deadline (description) /by (yyyy-MM-dd HHmm!)");
            } catch (DukeException e) {
                return "  " + e.getMessage();
            }
        }
        String[] deadlineArguments = deadlineDescription.split(" /by ");
        String DLDescription = deadlineArguments[0];
        String dateTime = deadlineArguments[1];

        try {
            Deadline deadline = new Deadline(DLDescription, dateTime);
            taskList.addTask(deadline);

            output.append("  ").append(deadline).append("\n");
            output.append(ui.listSizeMessage(taskList));
            return output.toString();
        } catch (DateTimeParseException e) {
            return "  Sorry! " +
                    "Format should be deadline (description) /by (yyyy-MM-dd HHmm!)";
        }
    }

    /**
     * Parses event task.
     * @param taskList The list.
     * @param ui The ui.
     */
    public static String parseEvent(TaskList taskList, Ui ui) {
        StringBuilder output = new StringBuilder();
        String eventDescription = ui.readCommandLine();
        if (!eventDescription.contains(" /from ") || !eventDescription.contains(" /to ")) {
            try {
                throw new DukeException("Sorry, " +
                        "please give me a description of the event as well! >.<\n" +
                        "  " +
                        "Format should be event " +
                        "(description) /from (yyyy-MM-dd HHmm) /to (yyyy-MM-dd HHmm)!");
            } catch (DukeException e) {
                return "  " + e.getMessage();
            }
        }
        String[] eventArguments = eventDescription.split(" /from ");
        String[] eventDuration = eventArguments[1].split(" /to ");
        String EvDescription = eventArguments[0];
        String startTime = eventDuration[0];
        String endTime = eventDuration[1];

        try {
            Event event = new Event(EvDescription, startTime, endTime);
            taskList.addTask(event);

            output.append("  ").append(event).append("\n");
            output.append(ui.listSizeMessage(taskList));
            return output.toString();
        } catch (DateTimeParseException e) {
            return "  " +
                    "Sorry! " +
                    "Format should be event " +
                    "(description) /from (yyyy-MM-dd HHmm) /to (yyyy-MM-dd HHmm)!";
        }
    }

    /**
     * Parses the category from the file and creates new tasks and adds them to the tasklist.
     *
     * @param taskDescription Description of the task taken from the file.
     * @return The correct category of task.
     * @throws IOException if the category does not belong to any of the expected outcomes.
     */
    public static Task parseCategoryFromFile(String taskDescription) throws IOException {
        String[] argument = taskDescription.split(" \\| ");
        String category = argument[0];
        String status = argument[1];
        String description = argument[2];
        Task task;

        switch (category) {
            case "T":
                task = new ToDo(status, description);
                break;
            case "D":
                String by = argument[3];
                task = new Deadline(status, description, by);
                break;
            case "E":
                String[] duration = argument[3].split(" - ");
                String start = duration[0];
                String end = duration[1];
                task = new Event(status, description, start, end);
                break;
            default:
                throw new IOException("Error, unable to load task from file.");
        }
        return task;
    }
}