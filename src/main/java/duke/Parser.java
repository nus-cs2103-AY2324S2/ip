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
        String[] parts = input.split(" ", 2);
        Command category = Command.getCategory(parts[0]);
        int listSize = taskList.getSize();
        switch (category) {
            case BYE -> {
            }
            case LIST -> {
                return ui.listTaskMessage(taskList);
            }
            case UNMARK -> {
                int unmarkId = Integer.parseInt(parts[1]) - 1;
                if (unmarkId < 0 || unmarkId >= listSize) {
                    try {
                        throw new DukeException("Sorry, " +
                                "please select a valid task for me to unmark!");
                    } catch (DukeException e) {
                        return "  " + e.getMessage();
                    }
                }
                return taskList.unmarkTask(unmarkId, ui);
            }
            case MARK -> {
                int markId = Integer.parseInt(parts[1]) - 1;
                if (markId < 0 || markId >= listSize) {
                    try {
                        throw new DukeException("Sorry, " +
                                "please select a valid task for me to mark!");
                    } catch (DukeException e) {
                        return "  " + e.getMessage();
                    }
                }
                return taskList.markTask(markId, ui);
            }
            case DELETE -> {
                int deleteId = Integer.parseInt(parts[1]) - 1;
                if (deleteId < 0 || deleteId >= listSize) {
                    try {
                        throw new DukeException("Sorry, " +
                                "please select a valid task for me to delete!");
                    } catch (DukeException e) {
                        return "  " + e.getMessage();
                    }
                }
                return taskList.deleteTask(deleteId, ui) + "\n" +
                        ui.listSizeMessage(taskList);
            }
            case TODO -> {
                return parseToDo(parts[1], taskList, ui);
            }
            case DEADLINE -> {
                return parseDeadline(parts[1], taskList, ui);
            }
            case EVENT -> {
                return parseEvent(parts[1], taskList, ui);
            }
            case FIND -> {
                String findDescription = ui.readCommandLine();
                ArrayList<Task> matchedTasks = taskList.find(findDescription);
                return taskList.listMatchedTasks(matchedTasks);
            }
            default -> {
                try {
                    throw new DukeException("Sorry, I cannot understand what this is!");
                } catch (DukeException e) {
                    return "  " + e.getMessage();
                }
            }
        }
        return ui.printGoodByeMessage();
    }

    /**
     * Parses toDo task.
     * @param description Task description.
     * @param taskList The list.
     * @param ui The Ui.
     */
    public static String parseToDo(String description, TaskList taskList, Ui ui) {
        StringBuilder output = new StringBuilder();
        if (description.isEmpty()) {
            try {
                throw new DukeException("Sorry, " +
                        "please give me a description of the todo as well! >.<\n" +
                        "  " + "Format should be todo (description)!");
            } catch (DukeException e) {
                return "  " + e.getMessage();
            }
        }
        ToDo toDo = new ToDo(description);
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
    public static String parseDeadline(String description, TaskList taskList, Ui ui) {
        StringBuilder output = new StringBuilder();
        if (!description.contains(" /by ")) {
            try {
                throw new DukeException("Sorry, " +
                        "please give me a description of the deadline as well! >.<\n" +
                        "  " +
                        "Format should be deadline (description) /by (yyyy-MM-dd HHmm!)");
            } catch (DukeException e) {
                return "  " + e.getMessage();
            }
        }
        String[] deadlineArguments = description.split(" /by ");
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
    public static String parseEvent(String description, TaskList taskList, Ui ui) {
        StringBuilder output = new StringBuilder();
        if (!description.contains(" /from ") || !description.contains(" /to ")) {
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
        String[] eventArguments = description.split(" /from ");
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
            case "T" -> {
                task = new ToDo(status, description);
            }
            case "D" -> {
                String by = argument[3];
                task = new Deadline(status, description, by);
            }
            case "E" -> {
                String[] duration = argument[3].split(" - ");
                String start = duration[0];
                String end = duration[1];
                task = new Event(status, description, start, end);
            }
            default -> {
                throw new IOException("Error, unable to load task from file.");
            }
        }
        return task;
    }
}