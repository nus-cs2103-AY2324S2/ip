package tasks;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;

import exceptions.DukeException;


/**
 * Represents a list of tasks in the task management application.
 * This class encapsulates operations such as adding, removing, marking tasks as done, and listing all tasks.
 */
public class TaskList {


    private ArrayList<Task> taskList; // The list of tasks

    /**
     * Constructs a TaskList with the specified list of tasks.
     *
     * @param taskList The initial list of tasks.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.taskList;
    }

    /**
     * Displays all tasks in the list.
     * If the list is empty, it prints a message indicating there are no tasks.
     */
    public String yapTasks() {
        if (taskList.isEmpty()) {
            return "Nothin' to yap...";
        }
        String output = "";
        for (int i = 0; i < taskList.size(); i++) {
            output += (i + 1) + ". " + taskList.get(i) + "\n";
        }
        return output;
    }

    /**
     * Marks a task as done.
     *
     * @param index The index of the task in the list to be marked as done, starting from 1.
     */
    public String markTaskAsDone(int index) throws DukeException {
        try {
            Task task = taskList.get(index - 1);
            return task.markDone(false);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Quit yappin, that task does not exist");
        }
    }

    /**
     * Marks a task as done.
     *
     * @param index The index of the task in the list to be marked as done, starting from 1.
     */
    public String unmarkTaskAsDone(int index) throws DukeException {
        try {
            Task task = taskList.get(index - 1);
            return task.unmarkDone();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Quit yappin, that task does not exist");
        }
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added to the list.
     */
    public void addTasktoTaskList(Task task) {
        if (task == null) {
            return;
        }
        taskList.add(task);
    }

    /**
     * Removes a task from the list.
     *
     * @param index The index of the task in the list to be removed, starting from 1.
     * @return The task that was removed.
     */
    public Task removeTaskfromTaskList(int index) throws DukeException {
        try {
            Task task = taskList.remove(index - 1);
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Quit yappin, that task does not exist");
        }
    }

    /**
     * Initializes a task based on the input message and task type.
     * This method attempts to parse the input and create the appropriate task object.
     *
     * @param message The input message containing task details.
     * @param taskType The type of the task (e.g., "todo", "deadline", "event").
     * @return The initialized task, or null if the task could not be created due to invalid input.
     */
    public Task initTask(String message, String taskType) throws DukeException {
        Task task;
        switch (taskType) {
        case "todo":
            task = initTodo(message);
            break;
        case "deadline":
            task = initDeadline(message);
            break;
        case "event":
            task = initEvent(message);
            break;
        default:
            //should not reach here because of filter in main logic
            task = new Task(message);
        }
        return task;
    }

    /**
     * Initializes a {@code ToDo} object based on the given input message.
     * The method expects the message to start with "todo " followed by the task description.
     *
     * @param message The input message containing the task description.
     * @return A new {@code ToDo} object with the specified description.
     * @throws DukeException If the input message does not contain a valid task description.
     */
    private ToDo initTodo(String message) throws DukeException {
        try {
            String[] inputs = message.split("todo ");
            return new ToDo(inputs[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Whats the task, yapper???");
        }
    }

    /**
     * Initializes a {@code Deadline} object based on the given input message.
     * The method expects the message to start with "deadline " followed by the task description
     * and a "/by" delimiter indicating the deadline date.
     *
     * @param message The input message containing the task description and deadline.
     * @return A new {@code Deadline} object with the specified description and deadline date.
     * @throws DukeException If the input message does not contain a valid task description or deadline date,
     *                       or if the deadline date is not in the expected format.
     */
    private Deadline initDeadline(String message) throws DukeException {
        try {
            message = message.substring("deadline ".length());
            String[] inputs = message.split("/by");
            return new Deadline(inputs[0].trim(), inputs[1].trim());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Deadlines need a deadline, yapper!");
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("Whats the task, yapper???");
        } catch (DateTimeException e) {
            throw new DukeException("Please put dates in format \"yyyy-mm-dd\"");
        }
    }

    /**
     * Initializes an {@code Event} object based on the given input message.
     * The method expects the message to start with "event " followed by the event description,
     * a "/from" delimiter indicating the start time, and a "/to" delimiter indicating the end time.
     *
     * @param message The input message containing the event description, start time, and end time.
     * @return A new {@code Event} object with the specified description, start time, and end time.
     * @throws DukeException If the input message does not contain a valid event description, start time, or end time,
     *                       or if the start and end times are not in the expected format.
     */
    public Event initEvent(String message) throws DukeException {
        try {
            message = message.substring("event ".length());
            String[] inputs = message.split("/from");
            String[] innerInputs = inputs[1].split("/to");
            return new Event(inputs[0].trim(), innerInputs[0].trim(), innerInputs[1].trim());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("YAPYAP, What time is your from and to?");
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("Whats the task, yapper???");
        } catch (DateTimeException e) {
            throw new DukeException("Please put dates in format \"yyyy-mm-dd\"");
        }
    }
    /**
     * Filters the current list of tasks, returning a new TaskList that contains only tasks
     * whose descriptions contain the specified query string.
     *
     * @param queryString The string to search for within each task's description. The method
     *                    performs a case-sensitive search.
     * @return A new TaskList containing only the tasks that have the queryString in their
     *         description. If no tasks match the criteria, an empty TaskList is returned.
     */
    public TaskList filterByString(String queryString) {
        ArrayList<Task> newList = new ArrayList<>();
        for (Task task : this.taskList) {
            if (task.getDescription().contains(queryString)) {
                newList.add(task);
            }
        }
        return new TaskList(newList);
    }

    /**
     * Filters the list of tasks and returns a new {@code TaskList} containing
     * only the tasks that are due on or are happening on the specified date.
     * This method specifically checks for tasks of type {@code Deadline} and {@code Event}.
     * For {@code Deadline} tasks, it checks if the deadline is due on the given date.
     * For {@code Event} tasks, it checks if the event is happening on the given date.
     *
     * @param stringDate The date to filter the tasks by, in the format of "yyyy-mm-dd".
     *                   It is used to identify tasks that are due on or are happening on this specific date.
     * @return A new {@code TaskList} containing only the tasks due on or happening on the specified date.
     * @throws DukeException If the provided date string does not conform to the expected format ("yyyy-mm-dd")
     *                          a {@code DukeException} is thrown with a message prompting the correct format.
     */
    public TaskList filterByDate(String stringDate) throws DukeException {
        try {
            LocalDate date = LocalDate.parse(stringDate);
            ArrayList<Task> newList = new ArrayList<>();
            for (Task task : this.taskList) {
                if (task instanceof Deadline && ((Deadline) task).isDueToday(date)) {
                    newList.add(task);
                } else if (task instanceof Event && ((Event) task).isHappening(date)) {
                    newList.add(task);
                }
            }
            return new TaskList(newList);
        } catch (DateTimeException e) {
            throw new DukeException("Please put dates in format \"yyyy-mm-dd\"");
        }
    }


}
