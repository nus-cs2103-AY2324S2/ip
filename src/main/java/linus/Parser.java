package linus;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Parses String.
 */
public class Parser {
    private final TaskList taskList;
    private final Ui ui;
    private final Storage storage;

    public Parser(TaskList taskList, Ui ui, Storage storage) {
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage;
    }

    public String parseInputCommand(String command) {
        String returnedString;

        // adapted with help of AI

        try {
            // Check if the user wants to exit.
            // When comparing strings for equality, you should use the equals() method, not the == operator.
            if (command.equals("bye")) { // exit chat
                returnedString = parseByeCommand();
            } else if (command.equals("list")) { // list tasks
                returnedString = parseListCommand();
            } else if (command.startsWith("find")) {
                returnedString = parseFindCommand(command);
            } else if (command.startsWith("mark")) {
                returnedString = parseMarkCommand(command);
            } else if (command.startsWith("unmark")) {
                returnedString = parseUnmarkCommand(command);
            } else if (command.startsWith("todo")) {
                returnedString = parseTodoCommand(command);
            } else if (command.startsWith("deadline")) {
                returnedString = parseDeadlineCommand(command);
            } else if (command.startsWith("event")) {
                returnedString = parseEventCommand(command);
            } else if (command.startsWith("delete")) {
                returnedString = parseDeleteCommand(command);
            } else {
                throw new LinusException("Please give commands that start with any of the following:" +
                        " [todo, deadline, event, mark, unmark, list, bye, delete]");
            }
        } catch (LinusException e) {
            return e.getMessage();
        }

        return returnedString;
    }

    private String parseDeleteCommand(String command) throws LinusException {
        String returnedString;
        if (command.length() <= 7) {
            throw new LinusException("Please state the index of the task you want to delete with correct spacing" +
                    " (e.g. delete 3)");
        }

        int indexOfTask = Integer.parseInt(command.substring(7).trim());

        if (indexOfTask < 0 || indexOfTask >= taskList.getSize()) {
            throw new LinusException("The task index is out of range.");
        }

        taskList.removeTask(indexOfTask);
        returnedString = "Now you have " + taskList.getSize() + " tasks in the list.";
        return returnedString;
    }

    private String parseEventCommand(String command) throws LinusException {
        String returnedString;
        if (!command.contains(" /from ") || !command.contains(" /to ")) {
            throw new LinusException("Please state the event period by using " +
                        " /from and /to with correct spacing (eg. event team meeting /from 2019-10-15 /to 2019-10-16)");
        }

        String[] substrings = command.split(" /from ");
        String description = substrings[0].substring(6);
        String[] substrings2 = substrings[1].split(" /to ");

        if (substrings2.length < 2 || description.isEmpty()) {
            throw new LinusException("Please specify the description of the deadline task " +
                    "(eg. event team meeting /from 2019-10-15 /to 2019-10-16)");
        }

        try {
            LocalDate from = LocalDate.parse(substrings2[0]);
            LocalDate to = LocalDate.parse(substrings2[1]);
            Task event = new Event(description, from, to, false);
            taskList.addTask(event);
            returnedString = "Got it. I've added this task: \n" + event + "\nNow you have " + taskList.getSize() + " tasks in the list.";
        } catch (DateTimeParseException e) {
            throw new LinusException("Invalid date format. Please use the format yyyy-MM-dd (e.g. 2019-10-15)");
        }
        return returnedString;
    }

    private String parseDeadlineCommand(String command) throws LinusException {
        String returnedString;
        String[] substrings = command.split(" /by ");

        if (substrings[0].length() <= 9) {
            throw new LinusException("Please specify the description of the deadline task " +
                    "(e.g. deadline return book /by 2019-10-15)");
        }

        String description = substrings[0].substring(9);

        if (description.isEmpty()) {
            throw new LinusException("Please specify the description of the deadline task " +
                    "(e.g. deadline return book /by 2019-10-15)");
        }

        if (!command.contains(" /by ") || substrings[1].isEmpty()) {
            throw new LinusException("Please state the deadline period " +
                    "(e.g. deadline return book /by 2019-10-15)");
        }

        try {
            LocalDate by = LocalDate.parse(substrings[1]);
            Task deadline = new Deadline(description, by, false);
            taskList.addTask(deadline);
            returnedString = "Got it. I've added this task: \n" + deadline + "\nNow you have " + taskList.getSize() + " tasks in the list.";
        } catch (DateTimeParseException e) {
            throw new LinusException("Invalid date format. Please use the format yyyy-MM-dd (e.g. 2019-10-15)");
        }
        return returnedString;
    }

    private String parseTodoCommand(String command) throws LinusException {
        String returnedString;
        // Check if the input string is long enough
        if (command.length() <= 5) {
            throw new LinusException("Please specify the description of the todo task, " +
                    "starting from one whitespace away from the keyword 'todo'" +
                    " (e.g. todo borrow book)");
        }

        String description = command.substring(5);

        if (description.isEmpty()) {
            throw new LinusException("Please specify the description of the todo task, " +
                    "starting from one whitespace away from the keyword 'todo'" +
                    " (e.g. todo borrow book)");
        }

        Task todo = new Todo(description, false);
        taskList.addTask(todo);
        returnedString = "Got it. I've added this task: \n" + todo + "\nNow you have " + taskList.getSize() + " tasks in the list.";
        return returnedString;
    }

    private String parseUnmarkCommand(String command) {
        String returnedString;
        int indexOfTask = Integer.parseInt(command.substring(7));
        Task currTask = taskList.getTask(indexOfTask);
        currTask.markAsNotDone();
        returnedString = "OK, I've marked this task as not done yet:\n" + currTask.toString();
        return returnedString;
    }

    private String parseMarkCommand(String command) {
        String returnedString;
        int indexOfTask = Integer.parseInt(command.substring(5));
        Task currTask = taskList.getTask(indexOfTask);
        currTask.markAsDone();
        returnedString = "Nice! I've marked this task as done: \n" + currTask.toString();
        return returnedString;
    }

    private String parseFindCommand(String command) {
        String returnedString;
        String keyword = command.substring(5).trim();
        returnedString = ui.findTasks(taskList, keyword);
        return returnedString;
    }

    private String parseListCommand() {
        String returnedString;
        returnedString = "Here are the tasks in your list:\n";

        for (int i = 0; i < taskList.getSize(); i++) {
            String iterString = i + ". " + taskList.getTask(i) + "\n";
            returnedString = returnedString + iterString;
        }
        return returnedString;
    }

    private String parseByeCommand() {
        String returnedString;
        ArrayList<Task> currUpdatedTaskList = taskList.getAllTasks();
        storage.saveTasksToFile(currUpdatedTaskList); // Save tasks to file before exiting
        returnedString = "Bye. It's been a pleasure chatting with you!";
        return returnedString;
    }

    /**
     * Parses each input String line from stored data and returns relevant tasks.
     *
     * @param line String line to be parsed into a Task.
     * @return Task object taken from stored data.
     */
    public static Task parseTask(String line) {
        String[] splitParts = line.split(" \\| ");
        String taskType = splitParts[0];
        boolean isDone = splitParts[1].equals("1");
        String description = splitParts[2];
        Task task = null;

        switch (taskType) {
        case "T":
            task = new Todo(description, isDone);
            break;
        case "D":
            LocalDate byDate = LocalDate.parse(splitParts[3]);
            task = new Deadline(description, byDate, isDone);
            break;
        case "E": // Event format in File is E | (isDone) | (Name) | (From) | (To)
            LocalDate fromDate = LocalDate.parse(splitParts[3]);
            LocalDate toDate = LocalDate.parse(splitParts[4]);
            task = new Event(description, fromDate, toDate, isDone);
            break;
        }

        return task;
    }
}
