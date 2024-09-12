package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import duke.command.CommandType;
import duke.exception.DukeException;
import duke.helpers.MyDateTime;
import duke.helpers.Ui;

/**
 * TaskList class.
 */
public class TaskList {
    private List<Task> storage;
    private Ui ui;

    /**
     * Constructor of TaskList.
     */
    public TaskList() {
        this.storage = new ArrayList<Task>();
        this.ui = new Ui();
    }

    /**
     * Creates a todo task based on the provided task description.
     *
     * @param task The description of the todo task.
     * @return The created todo task.
     * @throws DukeException If task description is empty.
     */
    private Task createTodoTask(String task) throws DukeException {
        if (task.equals("")) {
            throw new DukeException("The description is not provided. "
                    + "Write command using format: "
                    + CommandType.TODO.getCommand());
        }
        return new ToDo(task);
    }

    /**
     * Creates a deadline task based on the provided task description.
     *
     * @param task The description of the deadline task.
     * @return The created deadline task.
     * @throws DukeException If task description or deadline is not provided.
     */
    private Task createDeadlineTask(String task) throws DukeException {
        String[] taskArr = task.split(" /by ");

        if (taskArr.length <= 1) {
            throw new DukeException("The description or deadline is not provided. "
                    + "Write command using format: "
                    + CommandType.DEADLINE.getCommand());
        }

        String by = taskArr[1];
        LocalDateTime datetime = MyDateTime.convertDateTime(by);
        String description = taskArr[0];
        return new Deadline(description, datetime);
    }

    /**
     * Creates an event task based on the provided task description.
     *
     * @param task The description of the event task.
     * @return The created event task.
     * @throws DukeException If task description or event period is not provided.
     */
    private Task createEventTask(String task) throws DukeException {
        String[] taskArr = task.split(" /from ");

        if (taskArr.length <= 1) {
            throw new DukeException("The description or event period is not provided. "
                    + "Write command using format: "
                    + CommandType.EVENT.getCommand());
        }

        String[] fromArr = taskArr[1].split(" /to ");

        if (fromArr.length <= 1) {
            throw new DukeException("The description or event period is not provided. "
                    + "Write command using format: "
                    + CommandType.EVENT.getCommand());
        }

        String by = fromArr[0];
        String to = fromArr[1];
        String description = taskArr[0];
        return new Event(description, MyDateTime.convertDateTime(by), MyDateTime.convertDateTime(to));
    }

    /**
     * Creates a task based on the provided type and task description.
     *
     * @param type The type of task to create.
     * @param task The description of the task.
     * @return The created task.
     * @throws DukeException If the command is unavailable or if task description is invalid.
     */
    private Task createTask(String type, String task) throws DukeException {
        if (type.equals(CommandType.TODO.toString())) {
            return createTodoTask(task);
        } else if (type.equals(CommandType.DEADLINE.toString())) {
            return createDeadlineTask(task);
        } else if (type.equals(CommandType.EVENT.toString())) {
            return createEventTask(task);
        } else {
            throw new DukeException("This command is unavailable. Please refer to command list by using command: "
                    + CommandType.LISTCOMMANDS.getCommand());
        }
    }

    /**
     * Returns message and adds input task into storage.
     *
     * @param type Type of the task.
     * @param task The task to be done.
     * @return message.
     */
    public String addTask(String type, String task) throws DukeException {
        Task newTask = createTask(type, task);
        storage.add(newTask);

        String temp = storage.size() > 1 ? " tasks" : " task";

        return "Hi Hi! I've added this task:\n" + newTask + "\n"
                + "Now you have " + storage.size() + temp + " in the list.";
    }

    /**
     * Returns string when deletes a task.
     *
     * @param input index of the task to be deleted.
     * @return String of actions.
     * @throws DukeException If input task number is not in storage range.
     */
    public String deleteTask(String input) throws DukeException {
        try {
            int index = Integer.parseInt(input);
            if (index > storage.size() || index <= 0) {
                throw new DukeException("Invalid task number. Please check your task list using command : list.\n"
                        + "Write command using format: "
                        + CommandType.DELETE.getCommand());
            }

            Task curr = storage.get(index - 1);
            storage.remove(index - 1);
            String temp = storage.size() > 1 ? " tasks" : " task";
            return "Hi Hi! I've removed this task:\n" + curr + "\nNow you have "
                    + storage.size() + temp + " in the list.";
        } catch (NumberFormatException e) {
            throw new DukeException("Please insert valid integer for task number. Write command using format: "
                    + CommandType.DELETE.getCommand());
        }
    }

    /**
     * Returns string of all the task from the storage.
     * @return String of all tasks.
     */
    public String getListTasksMessage() {
        StringBuilder output = new StringBuilder();
        if (storage.size() == 0) {
            return "Hey, your task list is empty!! Faster create your first task now!!!!";
        } else {
            output.append("Hi Hi ! Here are the tasks in your list:\n");
        }
        for (int i = 0; i < storage.size(); i++) {
            output.append((i + 1) + ". " + storage.get(i) + "\n");
        }
        return output.toString();
    }

    /**
     * Returns out the task matching to given word.
     *
     * @param word Matching word.
     * @return String of tasks.
     */
    public String getListTasksMessage(String word) {
        StringBuilder output = new StringBuilder();
        if (storage.size() == 0) {
            return "Hi Hi !There is no matching task in your list.";
        } else {
            output.append("Hi Hi ! Here are the matching tasks in your list related to " + word + " :\n");
        }
        for (int i = 0; i < storage.size(); i++) {
            output.append((i + 1) + ". " + storage.get(i) + "\n");
        }
        return output.toString();
    }


    /**
     * Returns string when marks a task as done.
     *
     * @param input index of the task to be marked as done.
     * @return String of action.
     * @throws DukeException If input task number is not in storage range.
     */
    public String markDone(String input) throws DukeException {
        try {
            int index = Integer.parseInt(input);
            if (index > storage.size() || index <= 0) {
                throw new DukeException("Invalid task number. Please check your task list using command : "
                        + CommandType.LIST.getCommand()
                        + "\n"
                        + "Write command using format: "
                        + CommandType.MARK.getCommand());
            }

            Task curr = storage.get(index - 1);
            return "Hi Hi !" + curr.markDone();
        } catch (NumberFormatException e) {
            throw new DukeException("Please insert valid integer for task number. Write command using format: "
                    + CommandType.MARK.getCommand());
        }
    }

    /**
     * Unmarks a task.
     *
     * @param input index of the task to be unmarked.
     * @throws DukeException If input task number is not in storage range.
     */
    public String markUndone(String input) throws DukeException {
        try {
            int index = Integer.parseInt(input);
            if (index > storage.size() || index <= 0) {
                throw new DukeException("Invalid task number. Please check your task list using command : "
                        + CommandType.LIST.getCommand()
                        + ".\n"
                        + "Write command using format: "
                        + CommandType.UNMARK.getCommand());
            }

            Task curr = storage.get(index - 1);
            return "Hi Hi !" + curr.markUndone();
        } catch (NumberFormatException e) {
            throw new DukeException("Please insert valid integer for task number. Write command using format: "
                    + CommandType.UNMARK.getCommand());
        }
    }

    /**
     * Adds item into arraylist.
     *
     * @param item Item to be added.
     */
    public void add(Task item) {
        this.storage.add(item);
    }

    /**
     * Returns string representation to store in hard disk.
     *
     * @return String representation to store in hard disk.
     */
    public String storageListing() {
        StringBuffer textToWrite = new StringBuffer("");

        for (Task item : storage) {
            if (textToWrite.toString().equals("")) {
                textToWrite.append(item.toStorageString());
            } else {
                textToWrite.append("\n");
                textToWrite.append(item.toStorageString());
            }
        }
        return textToWrite.toString();
    }

    /**
     * Returns string of all task matching to the word given.
     *
     * @param word Matching word.
     * @throws DukeException If no input word.
     */
    public String findTask(String word) throws DukeException {
        if (word.equals("")) {
            throw new DukeException("Please insert the word that you are interested in. "
                    + "Write command using format: "
                    + CommandType.FIND.getCommand());
        }

        TaskList arr = new TaskList();

        for (Task item : storage) {
            if (item.checkDescription(word)) {
                arr.add(item);
            }
        }
        return arr.getListTasksMessage(word);
    }


    /**
     * Returns out all event/deadline related to the date given.
     *
     * @param date date that user interested in.
     * @return string of events.
     */
    public String checkDate(String date) {
        try {
            LocalDate d = MyDateTime.convertDate(date);
            TaskList arr = new TaskList();

            for (Task curr : storage) {
                if (curr.checkDate(d)) {
                    arr.add(curr);
                }
            }
            return arr.getListTasksMessage(d.toString());
        } catch (DukeException e) {
            return e.getMessage() + ". Please use format : " + CommandType.CHECKDATE.getCommand();
        }
    }

    /**
     * Removes all items in list.
     */
    public void removeAll() {
        this.storage.removeAll(storage);
    }
}
