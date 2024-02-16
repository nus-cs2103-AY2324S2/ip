package demon;

import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
     * @throws NoTimingException if date and time not given.
     * @throws EmptyDescriptionException if description not given.
     * @throws IOException if fails to write new task into file.
     * @throws DateTimeParseException if date and time given is invalid.
     */
    public void addDeadline(TaskList tasks, String input)
            throws NoTimingException,
            EmptyDescriptionException,
            IOException,
            DateTimeParseException, DuplicateException {
        String[] parts2 = getStrings(tasks, input);
        String description = parts2[0].trim();
        String by = parts2[1].trim();

        by = reformatTime(by);

        if (checkDuplicate(tasks, description, by)) {
            throw new DuplicateException();
        } else {
            // Format the date, time, and create Deadline object, add to list
            LocalDateTime dateTime = formatDateTime(by);
            Deadline itemDeadline = new Deadline(description, dateTime);
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
    }

    private LocalDateTime formatDateTime(String by) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return LocalDateTime.parse(by, formatter);
    }
    private static String[] getStrings(TaskList tasks, String input) throws EmptyDescriptionException,
            NoTimingException {
        assert tasks != null : "TaskList reference cannot be null";
        assert input != null && !input.isEmpty() : "Input string cannot be null or empty";
        String[] parts = input.split(" ",2);
        if (parts.length < 2) {
            throw new EmptyDescriptionException("Description is EMPTY!");
        }
        String deadline = parts[1];
        String[] parts2 = deadline.split("/by");
        if (parts2.length < 2) {
            throw new NoTimingException("WOI! Please include deadline!");
        }
        return parts2;
    }

    /**
     * Creates a To-do object as a task and stores into the taskList array.
     * Method takes in the tasks arraylist and a string.
     *
     * @param tasks an array containing all tasks.
     * @param input String input by the user containing the description.
     * @throws EmptyDescriptionException if description not given.
     * @throws IOException if fails to write new task into file.
     */
    public void addToDo(TaskList tasks, String input) throws EmptyDescriptionException, IOException, DuplicateException {
        String[] parts = input.split(" ",2);
        if (parts.length < 2) {
            throw new EmptyDescriptionException("Description is EMPTY!");
        }
        String toDo = parts[1];
        if (checkDuplicate(tasks, toDo)) {
            throw new DuplicateException();
        } else {
            Todo item_toDo = new Todo(toDo);
            tasks.getTaskList().add(item_toDo);
            String itemStatus = item_toDo.getStatusIcon().equals("X") ? "1" : "0";
            String stringToSave = "T | " + itemStatus + " | " + toDo + "\n";
            // May produce IOException
            storage.writeToFile(stringToSave);
            int taskListSize = tasks.getTaskList().size();
            this.storeString.append(Ui.showTaskAdded());
            this.storeString.append(item_toDo).append("\n");
            this.storeString.append(Ui.showNumberOfTasks(taskListSize));
        }
    }

    /**
     * Creates an Event object as a task and stores into the taskList array.
     * Method takes in the tasks arraylist and a string.
     *
     * @param tasks An array containing all tasks.
     * @param input String input by the user containing the description, date and time from, date and time to.
     * @throws EmptyDescriptionException if description not given.
     * @throws NoTimingException if date and time not given.
     * @throws IOException if fails to write new task into file.
     * @throws DateTimeParseException if date and time given is invalid.
     */
    public void addEvent(TaskList tasks, String input)
            throws EmptyDescriptionException,
            NoTimingException,
            IOException,
            DateTimeParseException, DuplicateException {
        String[] parts = input.split(" ",2);
        if (parts.length < 2) {
            throw new EmptyDescriptionException("Description is EMPTY!");
        }
        String description_date = parts[1];
        String[] parts2 = splitDescriptionDate(description_date);
        String description = parts2[0].trim();
        String[] details = splitDetails(parts2[1]);
        String from = details[0].trim();
        String to = details[1].trim();

        // Reformat dateTime if from and to is missing time
        from = reformatTime(from);
        to = reformatTime(to);
        if (checkDuplicate(tasks, description, from, to)) {
            throw new DuplicateException();
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            LocalDateTime dateTimeFrom = LocalDateTime.parse(from, formatter);
            LocalDateTime dateTimeTo = LocalDateTime.parse(to, formatter);
            Event item_event = new Event(description, dateTimeFrom, dateTimeTo);
            tasks.getTaskList().add(item_event);
            String stringToSave = "E | "
                    + (item_event.getStatusIcon().equals("X") ? "1" : "0")
                    + " | " + description + " | " + from + " | " + to + "\n";
            // May produce IOException
            storage.writeToFile(stringToSave);
            this.storeString.append("Yes Master, I've added this task: \n");
            this.storeString.append(item_event).append("\n");
            this.storeString.append(Ui.showNumberOfTasks(tasks.getTaskList().size()));
        }
    }
    private String[] splitInput(String input) throws EmptyDescriptionException {
        String[] parts = input.split(" ",2);
        if (parts.length < 2) {
            throw new EmptyDescriptionException("Description is EMPTY!");
        }
        return parts;
    }

    private String[] splitDescriptionDate(String description_date) throws NoTimingException {
        String[] parts2 = description_date.split("/from ");
        if (parts2.length < 2) {
            throw new NoTimingException("WOI! Please include time!");
        }
        return parts2;
    }

    private String[] splitDetails(String details) throws NoTimingException {
        String[] detailParts = details.split("/to ");
        if (detailParts.length < 2) {
            throw new NoTimingException("Time range is incomplete, missing '/to'.");
        }
        return detailParts;
    }
    /**
     * Returns the formatted dateTime String given in the parameter.
     * If given string does not include the time, we add 0000hrs by default to the date.
     * @param dateTime A string consisting date and time.
     * @return a String consisting of date and time.
     */
    private String reformatTime(String dateTime) {
        String outPut = dateTime;
        int dateTimeSize = dateTime.split(" ").length;
        if (dateTimeSize < 2) {
            outPut += " 0000";
        }
        return outPut;
    }

    private void checkExcessLen(String[] parts, int num) throws ExcessArgumentException {
        if (parts.length > num) {
            throw new ExcessArgumentException("TOO MANY ARGUMENTS PROVIDED!");
        }
    }

    private void checkOutOfRange(TaskList tasks, String[] parts) {
        if (Integer.parseInt(parts[1]) > tasks.getTaskList().size() | Integer.parseInt(parts[1]) < 1) {
            throw new IndexOutOfBoundsException("Integer provided out of range!");
        }
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
        String[] parts = splitInput(input);
        checkExcessLen(parts, 2);
        checkOutOfRange(tasks, parts);
        String str = "Noted Master. I've removed this task: \n";
        int indexToDelete = Integer.parseInt(parts[1]) - 1;
        String itemRemoveStr = tasks.getTaskList().get(indexToDelete).toString() + "\n";
        this.storeString.append(str);
        this.storeString.append(itemRemoveStr);
        tasks.getTaskList().remove(Integer.parseInt(parts[1]) - 1);
        storage.removeFromFile(Integer.parseInt(parts[1]) - 1);
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
        String[] parts = splitInput(input);
        String keyword = parts[1].toLowerCase();
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

    /**
     * Returns a boolean to check whether the task to be added has a duplicate.
     *
     * @param tasks An array containing all tasks.
     * @param args Variable arguments representing task attributes, where:
     *             - args[0] is always the task description.
     *             - For a To-do task, only args[0] is provided.
     *             - For a Deadline task, args[1] contains the deadline date/time as a String.
     *             - For an Event task, args[1] and args[2] contain the event's start and end date/time as Strings.
     * @return boolean true if a duplicate is found, false otherwise.
     */
    private boolean checkDuplicate(TaskList tasks, String... args) {
        String descriptionToCheck = args[0];
        if (args.length == 1) {
            return checkDuplicateTodo(tasks, descriptionToCheck);
        } else if (args.length == 2) {
            LocalDateTime dateTimeToCheck = formatDateTime(args[1]);
            return checkDuplicateDeadline(tasks, descriptionToCheck, dateTimeToCheck);
        } else if (args.length == 3) {
            LocalDateTime dateTimeFrom = formatDateTime(args[1]);
            LocalDateTime dateTimeTo = formatDateTime(args[2]);
            return checkDuplicateEvent(tasks, descriptionToCheck, dateTimeFrom, dateTimeTo);
        }
        return false;
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

    public String callCommand(String input, TaskList tasks)
            throws NoTimingException,
            EmptyDescriptionException,
            IOException,
            ExcessArgumentException,
            DuplicateException {
        if (input.equalsIgnoreCase("list")) {
            list(tasks);
        } else if (input.split(" ",2)[0].equalsIgnoreCase("unmark")) {
            int num = Integer.parseInt(input.split(" ")[1]);
            unmark(tasks, num);
        } else if (input.split(" ",2)[0].equalsIgnoreCase("mark")) {
            int num = Integer.parseInt(input.split(" ")[1]);
            mark(tasks, num);
        } else if (input.split(" ",2)[0].equalsIgnoreCase("deadline")) {
            addDeadline(tasks, input);
        } else if (input.split(" ",2)[0].equalsIgnoreCase("todo")) {
            addToDo(tasks, input);
        } else if (input.split(" ",2)[0].equalsIgnoreCase("event")) {
            addEvent(tasks, input);
        } else if (input.split(" ",2)[0].equalsIgnoreCase("delete")) {
            delete(tasks, input);
        } else if (input.split(" ",2)[0].equalsIgnoreCase("find")) {
            findTask(tasks, input);
        } else {
            return Ui.inValidCommand();
        }
        return outPutString();
    }
}
