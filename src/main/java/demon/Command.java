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
        this.storeString.append("List of things to do :\n");
        for (int i = 1; i <= tasks.getTaskList().size(); i++) {
            Task item = tasks.getTaskList().get(i - 1);
            this.storeString.append("\t").append(i).append(".").append(item.toString()).append("\n");
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
                storage.reWriteFile(num);
                this.storeString.append("Sure Master, I've marked this task as not done : \n");
                this.storeString.append(item).append("\n");
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
        if (num < 1 | num > tasks.getTaskList().size()) {
            throw new IndexOutOfBoundsException("Integer provided out of range!");
        } else {
            Task item = tasks.getTaskList().get(num-1);
            if (item.getStatusIcon().equals(" ")) {
                item.markDone();
                storage.reWriteFile(num);
                this.storeString.append("Sure Master, I've marked this task as done : \n");
                this.storeString.append(item).append("\n");
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
                   DateTimeParseException {
        String[] parts = input.split(" ",2);
        if (parts.length < 2) {
            throw new EmptyDescriptionException("Description is EMPTY!");
        }
        String deadline = parts[1];
        String[] parts2 = deadline.split("/by");
        if (parts2.length < 2) {
            throw new NoTimingException("WOI! Please include deadline!");
        }
        String description = parts2[0].trim();
        String by = parts2[1].trim();

        // Automatically assume that if time is not given, then time is 0000hrs
        int sizeOfBy = by.split(" ").length;
        if (sizeOfBy < 2) {
            by += " 0000";
        }
        // Format the date, time, and create Deadline object, add to list
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        LocalDateTime dateTime = LocalDateTime.parse(by, formatter);
        Deadline itemDeadline = new Deadline(description, dateTime);
        tasks.getTaskList().add(itemDeadline);
        String stringToSave = "D | " + (itemDeadline.getStatusIcon().equals("X") ? "1" : "0")
                + " | " + description + " | " + by + "\n";

        // May produce IOException
        storage.writeToFile(stringToSave);
        this.storeString.append(Ui.showTaskAdded());
        this.storeString.append(itemDeadline).append("\n");
        this.storeString.append(Ui.showNumberOfTasks(tasks.getTaskList().size()));
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
    public void addToDo(TaskList tasks, String input) throws EmptyDescriptionException, IOException {
        String[] parts = input.split(" ",2);
        if (parts.length < 2) {
            throw new EmptyDescriptionException("Description is EMPTY!");
        }
        String toDo = parts[1];
        Todo item_toDo = new Todo(toDo);
        tasks.getTaskList().add(item_toDo);
        String stringToSave = "T | " + (item_toDo.getStatusIcon().equals("X") ? "1" : "0") + " | " + toDo +"\n";
        // May produce IOException
        storage.writeToFile(stringToSave);
        this.storeString.append(Ui.showTaskAdded());
        this.storeString.append(item_toDo).append("\n");
        this.storeString.append(Ui.showNumberOfTasks(tasks.getTaskList().size()));
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
                   DateTimeParseException {
        String[] parts = input.split(" ",2);
        if (parts.length < 2) {
            throw new EmptyDescriptionException("Description is EMPTY!");
        }
        String description_date = parts[1];
        String[] parts2 = description_date.split("/from ");
        if (parts2.length < 2) {
            throw new NoTimingException("WOI! Please include time!");
        }
        String[] details = parts2[1].split("/to ");
        String description = parts2[0].trim();
        String from = details[0].trim();
        String to = details[1].trim();

        // Automatically assume that if time is not given, then time is 0000hrs
        int sizeOfFrom = from.split(" ").length;
        if (sizeOfFrom < 2) {
            from += " 0000";
        }
        int sizeOfTo = to.split(" ").length;
        if (sizeOfTo < 2) {
            to += " 0000";
        }

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
        String[] parts = input.split(" ");
        if (parts.length < 2) {
            throw new EmptyDescriptionException("Description is EMPTY!");
        } else if (parts.length > 2) {
            throw new ExcessArgumentException("TOO MANY ARGUMENTS PROVIDED!");
        }
        if (Integer.parseInt(parts[1]) > tasks.getTaskList().size() | Integer.parseInt(parts[1]) < 1) {
            throw new IndexOutOfBoundsException("Integer provided out of range!");
        }

        this.storeString.append("Noted Master. I've removed this task: \n");
        this.storeString.append(tasks.getTaskList().get(Integer.parseInt(parts[1]) - 1).toString()).append("\n");
        tasks.getTaskList().remove(Integer.parseInt(parts[1]) - 1);
        storage.removeFromFile(Integer.parseInt(parts[1]) - 1);
        this.storeString.append(Ui.showNumberOfTasks(tasks.getTaskList().size()));
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
        boolean hasMatched = false;
        String[] parts = input.split(" ");
        if (parts.length < 2) {
            throw new EmptyDescriptionException("Description is EMPTY!");
        }

        String keyword = parts[1].toLowerCase();
        this.storeString.append("Here are your matching tasks in your list: \n");
        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.getTask(i);
            String taskString = task.toString();
            if (taskString.toLowerCase().contains(keyword)) {
                this.storeString.append((i + 1)).append(".").append(taskString).append("\n");
                hasMatched = true;
            }
        }

        if (!hasMatched) {
            this.storeString.append("NONE!");
        }
    }
    private String outPutString() {
        String outPut = this.storeString.toString();
        this.storeString.setLength(0);
        return outPut;
    }

    public String callCommand(String input, TaskList tasks)
            throws NoTimingException,
                    EmptyDescriptionException,
                    IOException,
                    ExcessArgumentException {
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
