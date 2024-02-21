package catchat;
import java.util.ArrayList;

/**
 * TaskList class contains the task list
 */
public class TaskList {
    private final ArrayList<Task> taskList;
    private final Ui ui;
    private int taskIndex;
    private final Storage storage;

    /**
     * Constructor for TaskList
     *
     * @param storage
     * @param taskList
     */
    public TaskList(Storage storage, ArrayList<Task> taskList) {
        this.taskList = taskList;
        this.ui = new Ui();
        this.storage = storage;
    }

    /**
     * Adds task to taskList
     *
     * @param task task to be added
     */
    public String addTask(String task) {
        if (taskExists(task)) {
            return "This task already exists!";
        }

        TaskType taskType = getTaskType(task);

        switch (taskType) {
        case TODO:
            return addTodoTask(task);
        case DEADLINE:
            return addDeadline(task);
        case EVENT:
            return addEvent(task);
        default: // UNKNOWN TaskType
            return "Sorry, that's not a command. Enter 'help' for instructions.";
        }
    }

    /**
     * Detects if the task being added already exists in the taskList
     *
     * @param task
     * @return
     */
    public boolean taskExists(String task) {
        for (Task t : this.taskList) {
            if (t.toString().equals(task)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds todoTask to taskList
     *
     * @param task task to be added
     */
    public String addTodoTask(String task) {

        String todoDescription = task.substring(4).trim();
        if (todoDescription.isEmpty()) {
            return "Invalid input. Please enter a valid todo task.";
        } else {
            Todo newTodo = new Todo(todoDescription);
            this.taskList.add(newTodo);
            storage.saveTaskListToFile();

            return "Added todo: " + todoDescription;
        }
    }

    /**
     * Adds deadline to taskList
     *
     * @param task task to be added
     */
    public String addDeadline(String task) {

        String[] deadlineDescription = task.substring(8).trim().split("/by", 2);
        if (deadlineDescription.length != 2 || deadlineDescription[0].trim().isEmpty()
                || deadlineDescription[1].trim().isEmpty()) {
            return "Invalid input. Enter 'deadline <task> /by <DEADLINE>'";
        } else {
            String description = deadlineDescription[0].trim();
            String by = deadlineDescription[1].trim();

            Deadline newDeadline = new Deadline(description, by);
            this.taskList.add(newDeadline);
            storage.saveTaskListToFile();

            return "Added deadline: " + newDeadline;
        }
    }

    /**
     * Adds eventTask to taskList
     *
     * @param task task to be added
     */
    public String addEvent(String task) {

        String[] eventParts = task.substring(6).trim().split("/from");
        if (eventParts.length == 2) {
            String[] durationParts = eventParts[1].trim().split("/to");
            if (durationParts.length == 2) {
                String desc = eventParts[0].trim();
                String from = durationParts[0].trim();
                String to = durationParts[1].trim();
                Events newEvent = new Events(desc, from, to);
                this.taskList.add(newEvent);
                storage.saveTaskListToFile();

                return "Added event: " + desc + " (from: " + from + ", to: " + to + ")";

            } else {
                return "Invalid input for event. "
                        + "Please use the format: event <task> /from <start time> /to <end time>";
            }
        } else {
            return "Invalid input for event. "
                    + "Please use the format: event <task> /from <start time> /to <end time>";
        }
    }

    /**
     * Deletes task from taskList
     *
     * @param index index of task to be deleted
     * @throws IndexOutOfBoundsException if index is out of bounds
     * @throws NumberFormatException if input is not a number
     */
    public String deleteTask(int index) {
        try {
            Task removedTask = this.taskList.remove(index);
            storage.saveTaskListToFile();
            return "Noted. I've removed this task:\n"
                    + "[ " + removedTask + " ]\n"
                    + "There are " + this.taskList.size() + " tasks in your list.";
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            return ui.printInvalidTaskIndex();
        }
    }

    /**
     * Returns task type
     *
     * @param task task to be added
     * @return TaskType
     */
    public TaskType getTaskType(String task) {
        if (task.startsWith("todo")) {
            return TaskType.TODO;
        } else if (task.startsWith("deadline")) {
            return TaskType.DEADLINE;
        } else if (task.startsWith("event")) {
            return TaskType.EVENT;
        } else {
            return TaskType.UNKNOWN;
        }
    }

    /**
     * Marks task as done
     * If task is already done, prints error message
     * If task is undone, marks as done
     * If task does not exist, prints error message
     * If input is not a number, prints error message
     *
     * @param index index of task to be marked as done
     * @throws IndexOutOfBoundsException if index is out of bounds
     * @throws NumberFormatException if input is not a number
     */
    public String markDone(int index) {
        try {
            if (this.taskList.get(index).isDone()) {
                return "You completed this task already!";
            } else {
                this.taskList.get(index).markDone();
                storage.saveTaskListToFile();
                return "Good job completing the task!";
            }
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            ui.printInvalidTaskIndex();
        }
        return "";
    }

    /**
     * Marks task as undone
     * If task is already undone, prints error message
     * If task is done, marks as undone
     * If task does not exist, prints error message
     * If input is not a number, prints error message
     *
     * @param index index of task to be marked as undone
     * @throws IndexOutOfBoundsException if index is out of bounds
     * @throws NumberFormatException if input is not a number
     */
    public String markUndone(int index) {
        try {
            if (!this.taskList.get(index).isDone()) {
                return "Oops! You still haven't done this task!";
            } else {
                this.taskList.get(index).markUndone();
                storage.saveTaskListToFile();
                return "Better get to it soon!";
            }
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            ui.printInvalidTaskIndex();
        }
        return "";
    }

    /**
     * Find tasks that contain keyword
     */
    public String findTasks(String keyword) {
        StringBuilder result = new StringBuilder();
        int count = 0;

        for (Task task : this.taskList) {
            if (task.toString().contains(keyword)) {
                if (count == 0) {
                    result.append("\t").append("Here are the matching tasks in your list:\n");
                }
                count++;
                result.append("\t").append(count).append(". ").append(task).append("\n");
            }
        }

        if (count == 0) {
            result.append("\t").append("No matching tasks found :(");
        }

        return result.toString();
    }


    public String getList() {
        if (this.taskList.isEmpty()) {
            return "Your tasklist is empty";
        } else {
            String list = "Here is your to-do list:\n";

            this.taskIndex = 1;
            for (Task task : this.taskList) {
                list += this.taskIndex + ". " + task + "\n";
                System.out.println("\t" + this.taskIndex + ". " + task);
                this.taskIndex++;
            }
            return list;
        }
    }
}
