package duke.task;

import duke.command.CommandType;
import duke.exception.DukeException;
import duke.helpers.MyDateTime;
import duke.helpers.Ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> storage;
    private Ui ui;

    public TaskList() {
        this.storage = new ArrayList<Task>();
        this.ui = new Ui();
    }

    /**
     * Adds input task into storage.
     *
     * @param type Type of the task.
     * @param task The task to be done.
     */
    public void addTask(String type, String task) throws DukeException {
        Task newTask;

        if (type.equals(CommandType.TODO.toString())) {
            if (task.equals("")) {
                throw new DukeException("The description is not provided. " +
                        "Write command using format: " +
                        CommandType.TODO.getCommand());
            }
            newTask = new ToDo(task);

        } else if (type.equals(CommandType.DEADLINE.toString())) {

            String[] taskArr = task.split(" /by ");

            if (taskArr.length <= 1) {
                throw new DukeException("The description or deadline is not provided. " +
                        "Write command using format: " +
                        CommandType.DEADLINE.getCommand());
            }

            String by = taskArr[1];
            LocalDateTime datetime = MyDateTime.convertDateTime(by);
            String description = taskArr[0];
            newTask = new Deadline(description, datetime);

        } else if (type.equals(CommandType.EVENT.toString())) {
            String[] taskArr = task.split(" /from ");

            if (taskArr.length <= 1) {
                throw new DukeException("The description or event period is not provided. " +
                        "Write command using format: " +
                        CommandType.EVENT.getCommand());
            }

            String[] fromArr = taskArr[1].split(" /to ");

            if (fromArr.length <= 1) {
                throw new DukeException("The description or event period is not provided. " +
                        "Write command using format: " +
                        CommandType.EVENT.getCommand());
            }

            String by = fromArr[0];
            String to = fromArr[1];
            String description = taskArr[0];
            newTask = new Event(description, MyDateTime.convertDateTime(by), MyDateTime.convertDateTime(to));
        } else {
            throw new DukeException("This command is unavailable. Please refer to command list by using command: " +
                    CommandType.LISTCOMMANDS.getCommand());
        }

        storage.add(newTask);

        String temp = storage.size() > 1 ? " tasks" : " task";
        ui.displayToScreen("Got it. I've added this task:\n" + newTask + "\n" +
                "Now you have " + storage.size() + temp + " in the list.");
    }

    /**
     * Deletes a task.
     *
     * @param input index of the task to be deleted.
     * @return length of storage after deleted the task.
     * @throws DukeException If input task number is not in storage range.
     */
    public int deleteTask(String input) throws DukeException {
        try {
            int index = Integer.parseInt(input);
            if (index > storage.size() || index <= 0) {
                throw new DukeException("Invalid task number. Please check your task list using command : list.\n" +
                        "Write command using format: " +
                        CommandType.DELETE.getCommand());
            }

            Task curr = storage.get(index - 1);
            storage.remove(index - 1);
            String temp = storage.size() > 1 ? " tasks" : " task";
            ui.displayToScreen("Noted. I've removed this task:\n" + curr + "\nNow you have "
                    + storage.size() + temp + " in the list.");
            return this.storage.size();
        } catch (NumberFormatException e) {
            throw new DukeException("Please insert valid integer for task number. Write command using format: " +
                    CommandType.DELETE.getCommand());
        }
    }

    /**
     * Lists out all the task from the storage.
     */
    public void listTask() {
        ui.drawLine();
        if (storage.size() == 0) {
            System.out.println("Your task list is empty. Create your first task now!");
        } else {
            System.out.println("Here are the tasks in your list:");
        }
        for (int i = 0; i < storage.size(); i++) {
            System.out.println((i + 1) + ". " + storage.get(i));
        }
        ui.drawLine();
    }

    public void listTask(LocalDate date) {
        ui.drawLine();
        if (storage.size() == 0) {
            System.out.println("No event on " + date.toString());
        } else {
            System.out.println("Here are the tasks in your list related to " +  date.toString() + " :");
        }
        for (int i = 0; i < storage.size(); i++) {
            System.out.println((i + 1) + ". " + storage.get(i));
        }
        ui.drawLine();
    }


    /**
     * Marks a task as done.
     *
     * @param input index of the task to be marked as done.
     * @throws DukeException If input task number is not in storage range.
     */
    public void markDone(String input) throws DukeException {
        try {
            int index = Integer.parseInt(input);
            if (index > storage.size() || index <= 0) {
                throw new DukeException("Invalid task number. Please check your task list using command : " +
                        CommandType.LIST.getCommand() +
                        "\n" +
                        "Write command using format: " +
                        CommandType.MARK.getCommand());
            }

            Task curr = storage.get(index - 1);
            ui.drawLine();
            curr.markDone();
            ui.drawLine();
        } catch (NumberFormatException e) {
            throw new DukeException("Please insert valid integer for task number. Write command using format: " +
                    CommandType.MARK.getCommand());
        }
    }

    /**
     * Unmarks a task.
     *
     * @param input index of the task to be unmarked.
     * @throws DukeException If input task number is not in storage range.
     */
    public void markUndone(String input) throws DukeException {
        try {
            int index = Integer.parseInt(input);
            if (index > storage.size() || index <= 0) {
                throw new DukeException("Invalid task number. Please check your task list using command : " +
                        CommandType.LIST.getCommand() +
                        ".\n" +
                        "Write command using format: " +
                        CommandType.UNMARK.getCommand());
            }

            Task curr = storage.get(index - 1);
            ui.drawLine();
            curr.markUndone();
            ui.drawLine();
        } catch (NumberFormatException e) {
            throw new DukeException("Please insert valid integer for task number. Write command using format: " +
                    CommandType.UNMARK.getCommand());
        }
    }

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
     * Prints out all event/deadline related to the date given.
     *
     * @param date date that user interested in.
     */
    public void checkDate(String date) {
        try {
            LocalDate d = MyDateTime.convertDate(date);
            TaskList arr = new TaskList();

            for (Task curr : storage) {
                if (curr.checkDate(d)) {
                    arr.add(curr);
                }
            }
            arr.listTask(d);
        } catch (DukeException e){
            ui.displayToScreen(e.getMessage() + ". Please use format : " + CommandType.CHECKDATE.getCommand());
        }
    }
}
