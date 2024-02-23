package demon;

import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;

/**
 * This class serves as a central point for managing and executing a variety of user commands.
 * It encapsulates the logic for each command as individual methods, allowing for easy execution
 * based on user input. Command such as 'list', 'unmark', 'mark', 'delete', and others are supported, each
 * corresponding to a specific method within this class.
 */
public class Command {
    Storage storage;
    private StringBuilder storeString = new StringBuilder();
    public Command(String filePath) {
        this.storage = new Storage(filePath);
    }

    /**
     * Prints out all tasks saved numbered in sequence.
     * No tasks will be printed if there is no saved tasks.
     *
     * @param tasks an array containing all tasks.
     */
    public void list(TaskList tasks) {
        String str = "List of things to do :\n";
        this.storeString.append(str);
        for (int i = 1; i <= tasks.getTaskList().size(); i++) {
            Task item = tasks.getTaskList().get(i - 1);
            String strToAppend = "\t" + i + ". " + item.toString() + "\n";
            this.storeString.append(strToAppend);
        }
    }

    /**
     * Marks the task in the list as 'not done' if the task was previously marked as done.
     * If the task was marked as 'not done', then executing this method will not change anything.
     *
     * @param tasks an array containing all tasks.
     * @param num array index of the task list
     */
    public void unmark(TaskList tasks, int num) {
        if (num < 1 | num > tasks.getTaskList().size()) {
            throw new IndexOutOfBoundsException("Integer provided out of range!");
        } else {
            Task item = tasks.getTaskList().get(num-1);
            if (item.getStatusIcon().equals("X")) {
                item.markNotDone();
                String markStr = "Sure Master, I've marked this task as not done : \n";
                String itemStr = item + "\n";
                storage.reWriteFile(num);
                this.storeString.append(markStr);
                this.storeString.append(itemStr);
            } else {
                this.storeString.append("Oops! Task already NOT done!");
            }
        }
    }

    /**
     * Marks the task in the list as 'done' if the task was previously marked as not done.
     * If the task was marked as 'done', then executing this method will not change anything.
     *
     * @param tasks an array containing all tasks.
     * @param num array index of the task list
     */
    public void mark(TaskList tasks, int num) {
        assert tasks != null : "TaskList reference cannot be null";
        assert tasks.getTaskList() != null : "getTaskList() returned null";
        if (num < 1 | num > tasks.getTaskList().size()) {
            throw new IndexOutOfBoundsException("Integer provided out of range!");
        } else {
            Task item = tasks.getTaskList().get(num-1);
            if (item.getStatusIcon().equals(" ")) {
                item.markDone();
                String markStr = "Sure Master, I've marked this task as done : \n";
                String itemStr = item + "\n";
                storage.reWriteFile(num);
                this.storeString.append(markStr);
                this.storeString.append(itemStr);
            } else {
                this.storeString.append("Oops! Task already done!");
            }
        }
    }

    /**
     * Creates a Deadline object as a task and stores into the taskList array.
     * Method takes in the tasks arraylist and a string.
     *
     * @param tasks an array containing all tasks.
     * @param input String input by the user containing the description, date and time.
     * @throws IOException if fails to write new task into file.
     * @throws DateTimeParseException if date and time given is invalid.
     */
    public void addDeadline(TaskList tasks, String input)
            throws IOException,
            DateTimeParseException,
            DuplicateException,
            NoTimingException,
            EmptyDescriptionException {
        Parser parser = new Parser();
        ArrayList<String> parts = parser.parseInput(input);
        String description = parts.get(0);
        String by = parts.get(1);
        by = reformatTime(by);
        checkAndThrowDuplicateException(tasks, description, by);
        // Format the date, time, and create Deadline object, add to list
        LocalDateTime dateTimeBy = stringToDateTime(by);
        createDeadline(tasks, description, dateTimeBy);
    }

    private void createDeadline(TaskList tasks, String description, LocalDateTime dateTimeBy) throws IOException {
        Deadline itemDeadline = new Deadline(description, dateTimeBy);
        String by = dateTimeToString(dateTimeBy);
        tasks.getTaskList().add(itemDeadline);
        String itemStatus = itemDeadline.getStatusIcon().equals("X") ? "1" : "0";
        String stringToSave = "D | " + itemStatus
                + " | " + description + " | " + by + "\n";

        // May produce IOException
        storage.writeToFile(stringToSave);
        int taskListSize = tasks.getTaskList().size();
        this.storeString.append(Ui.showTaskAdded());
        this.storeString.append(itemDeadline).append("\n");
        this.storeString.append(Ui.showNumberOfTasks(taskListSize));
    }

    /**
     * Creates a To-do object as a task and stores into the taskList array.
     * Method takes in the tasks arraylist and a string.
     *
     * @param tasks an array containing all tasks.
     * @param input String input by the user containing the description.
     * @throws IOException if fails to write new task into file.
     */
    public void addToDo(TaskList tasks, String input)
            throws IOException,
            DuplicateException,
            NoTimingException,
            EmptyDescriptionException {
        Parser parser = new Parser();
        ArrayList<String> parts = parser.parseInput(input);
        String description = parts.get(0);
        checkAndThrowDuplicateException(tasks, description);
        createToDo(tasks, description);
    }
    private void createToDo(TaskList tasks, String description) throws IOException {
        Todo item_toDo = new Todo(description);
        tasks.getTaskList().add(item_toDo);
        String itemStatus = item_toDo.getStatusIcon().equals("X") ? "1" : "0";
        String stringToSave = "T | " + itemStatus + " | " + description + "\n";
        // May produce IOException
        storage.writeToFile(stringToSave);
        int taskListSize = tasks.getTaskList().size();
        this.storeString.append(Ui.showTaskAdded());
        this.storeString.append(item_toDo).append("\n");
        this.storeString.append(Ui.showNumberOfTasks(taskListSize));
    }

    /**
     * Creates an Event object as a task and stores into the taskList array.
     * Method takes in the tasks arraylist and a string.
     *
     * @param tasks An array containing all tasks.
     * @param input String input by the user containing the description, date and time from, date and time to.
     * @throws IOException if fails to write new task into file.
     * @throws DateTimeParseException if date and time given is invalid.
     */
    public void addEvent(TaskList tasks, String input)
            throws IOException,
            DateTimeParseException,
            DuplicateException,
            NoTimingException,
            EmptyDescriptionException {
        Parser parser = new Parser();
        ArrayList<String> parts = parser.parseInput(input);
        String description = parts.get(0);
        String from = parts.get(1);
        String to = parts.get(2);
        checkAndThrowDuplicateException(tasks, description, from, to);
        LocalDateTime dateTimeFrom = stringToDateTime(from);
        LocalDateTime dateTimeTo = stringToDateTime(to);
        createEvent(tasks, description, dateTimeFrom, dateTimeTo);
    }

    private void createEvent(TaskList tasks, String description, LocalDateTime dateTimeFrom, LocalDateTime dateTimeTo) throws
            IOException {
        Event item_event = new Event(description, dateTimeFrom, dateTimeTo);
        tasks.getTaskList().add(item_event);
        String from = dateTimeToString(dateTimeFrom);
        String to = dateTimeToString(dateTimeTo);
        String stringToSave = "E | "
                + (item_event.getStatusIcon().equals("X") ? "1" : "0")
                + " | " + description + " | " + from + " | " + to + "\n";
        // May produce IOException
        storage.writeToFile(stringToSave);
        this.storeString.append("Yes Master, I've added this task: \n");
        this.storeString.append(item_event).append("\n");
        this.storeString.append(Ui.showNumberOfTasks(tasks.getTaskList().size()));
    }

    /**
     * Deletes a task in the given taskList array by specifying the index.
     *
     * @param tasks An array containing all tasks.
     * @param input String input by the user containing the index to delete.
     * @throws EmptyDescriptionException if description not given.
     * @throws IOException if fails to write new task into file.
     */
    public void delete(TaskList tasks, String input)
            throws EmptyDescriptionException,
                    IOException,
                    ExcessArgumentException {
        String description = splitInput(input);
        int num = checkExcessLen(description);
        checkOutOfRange(tasks, num);
        String str = "Noted Master. I've removed this task: \n";
        String itemRemoveStr = tasks.getTaskList().get(num - 1).toString() + "\n";
        this.storeString.append(str);
        this.storeString.append(itemRemoveStr);
        tasks.getTaskList().remove(num - 1);
        storage.removeFromFile(num - 1);
        int taskListSize = tasks.getTaskList().size();
        this.storeString.append(Ui.showNumberOfTasks(taskListSize));
    }

    /**
     * Prints all tasks with matching keywords.
     * Prints "NONE" if not found.
     *
     * @param tasks An array containing all tasks.
     * @param input String input by the user as the keyword to find.
     * @throws EmptyDescriptionException if description (keyword) not given.
     */
    public void findTask(TaskList tasks, String input) throws EmptyDescriptionException {
        String keyword = splitInput(input).toLowerCase();
        findMatchingTasks(this.storeString, tasks, keyword);
    }

    private void findMatchingTasks(StringBuilder storeString, TaskList tasks, String keyword) {
        String str = "Here are your matching tasks in your list: \n ";
        storeString.append(str);
        boolean hasMatched = false;
        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.getTask(i);
            String taskString = task.toString();
            if (taskString.toLowerCase().contains(keyword)) {
                String matchedTask = i + 1 + ". " + taskString + "\n ";
                storeString.append(matchedTask);
                hasMatched = true;
            }
        }
        if (!hasMatched) {
            storeString.append("NONE!");
        }
    }
    private String outPutString() {
        String outPut = this.storeString.toString();
        this.storeString.setLength(0);
        return outPut;
    }

    private boolean checkDuplicate(TaskList tasks, String... args) {
        String descriptionToCheck = args[0];
        boolean isDuplicate = false;
        if (args.length == 1) {
            isDuplicate = checkDuplicateTodo(tasks, descriptionToCheck);
        } else if (args.length == 2) {
            LocalDateTime dateTimeToCheck = stringToDateTime(args[1]);
            isDuplicate = checkDuplicateDeadline(tasks, descriptionToCheck, dateTimeToCheck);
        } else if (args.length == 3) {
            LocalDateTime dateTimeFrom = stringToDateTime(args[1]);
            LocalDateTime dateTimeTo = stringToDateTime(args[2]);
            isDuplicate = checkDuplicateEvent(tasks, descriptionToCheck, dateTimeFrom, dateTimeTo);
        }
        return isDuplicate;
    }

    private boolean checkDuplicateTodo(TaskList tasks, String description) {
        return tasks.getTaskList().stream()
                .anyMatch(task -> task instanceof Todo && task.description.equals(description));
    }

    private boolean checkDuplicateDeadline(TaskList tasks, String description, LocalDateTime dateTime) {
        return tasks.getTaskList().stream()
                .anyMatch(task -> task instanceof Deadline && task.description.equals(description)
                        && task.getDateTime().equals(dateTime));
    }

    private boolean checkDuplicateEvent(TaskList tasks, String description, LocalDateTime dateTimeFrom, LocalDateTime dateTimeTo) {
        return tasks.getTaskList().stream()
                .anyMatch(task -> task instanceof Event && task.description.equals(description)
                        && task.getDateTimeFrom().equals(dateTimeFrom) && task.getDateTimeTo().equals(dateTimeTo));
    }

    private String dateTimeToString(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        // Format LocalDateTime to String
        return dateTime.format(formatter);
    }
    private LocalDateTime stringToDateTime(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return LocalDateTime.parse(dateTime, formatter);
    }

    private void checkAndThrowDuplicateException(TaskList tasks, String... args) throws DuplicateException {
        if (checkDuplicate(tasks, args)) {
            throw new DuplicateException();
        }
    }
    public static String splitInput(String input) throws EmptyDescriptionException {
        String[] parts = input.split(" ",2);
        if (parts.length < 2) {
            throw new EmptyDescriptionException("Description is EMPTY!");
        }
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }
        return parts[1];
    }

    public static String[] arrayTrim(String[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = array[i].trim();
        }
        return array;
    }

    /**
     * Returns a list of strings after splitting a string by "/by".
     * @param description_date the string should consist of description, date and time to be split by "/by".
     * @return array of strings split by "/by"
     * @throws NoTimingException when we cannot split by "/by" means no timing provided.
     */
    public static String[] splitBy(String description_date) throws NoTimingException {
        String[] parts2 = description_date.split("/by ");
        if (parts2.length < 2) {
            throw new NoTimingException("WOI! Please include time!");
        }
        return arrayTrim(parts2);
    }

    public static String[] splitDescriptionDate(String description_date) throws NoTimingException {
        String[] parts2 = description_date.split("/from ");
        if (parts2.length < 2) {
            throw new NoTimingException("WOI! Please include time!");
        }
        return arrayTrim(parts2);
    }

    /**
     * Returns a list of strings after splitting a string by "/to".
     *
     * @param details the string to be split by "/to".
     * @return array of strings split by "/to".
     * @throws NoTimingException when we cannot split by "/to" means no timing provided.
     */
    public static String[] splitByTo(String details) throws NoTimingException {
        String[] detailParts = details.split("/to ");
        if (detailParts.length < 2) {
            throw new NoTimingException("Time range is incomplete, missing '/to'.");
        }
        arrayTrim(detailParts);
        detailParts[0] = reformatTime(detailParts[0]); // Reformat the from dateTime
        detailParts[1] = reformatTime(detailParts[1]); // Reformat the to dateTime
        return detailParts;
    }

    private static String reformatTime(String dateTime) {
        String outPut = dateTime;
        int dateTimeSize = dateTime.split(" ").length;
        if (dateTimeSize < 2) {
            outPut += " 0000";
        }
        return outPut;
    }

    private int checkExcessLen(String description) throws ExcessArgumentException {
        String[] parts = description.split(" ");
        if (parts.length > 1) {
            throw new ExcessArgumentException("TOO MANY ARGUMENTS PROVIDED!");
        }
        return Integer.parseInt(parts[0]);
    }

    private void checkOutOfRange(TaskList tasks, int num) {
        if (num > tasks.getTaskList().size() | num < 1) {
            throw new IndexOutOfBoundsException("Integer provided out of range!");
        }
    }
    public String callCommand(String input, TaskList tasks)
            throws NoTimingException,
            EmptyDescriptionException,
            IOException,
            ExcessArgumentException,
            DuplicateException {
        String inputCommand = input.split(" ",2)[0];
        if (inputCommand.equalsIgnoreCase("list")) {
            list(tasks);
        } else if (inputCommand.equalsIgnoreCase("unmark")) {
            int num = Integer.parseInt(input.split(" ")[1]);
            unmark(tasks, num);
        } else if (inputCommand.equalsIgnoreCase("mark")) {
            int num = Integer.parseInt(input.split(" ")[1]);
            mark(tasks, num);
        } else if (inputCommand.equalsIgnoreCase("deadline")) {
            addDeadline(tasks, input);
        } else if (inputCommand.equalsIgnoreCase("todo")) {
            addToDo(tasks, input);
        } else if (inputCommand.equalsIgnoreCase("event")) {
            addEvent(tasks, input);
        } else if (inputCommand.equalsIgnoreCase("delete")) {
            delete(tasks, input);
        } else if (inputCommand.equalsIgnoreCase("find")) {
            findTask(tasks, input);
        } else {
            return Ui.inValidCommand();
        }
        return outPutString();
    }
}
