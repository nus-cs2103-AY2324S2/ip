import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList = new ArrayList<>();
    private final Parser parser = new Parser();

    /**
     * Constructor for TaskList class.
     *
     * @param storage The storage object.
     * @throws JimmyException If the file cannot be loaded.
     */
    public TaskList(Storage storage) throws JimmyException {
        storage.loadFileContents(taskList);
    }

    /**
     * Creates a new task and adds it to the task list.
     *
     * @param instruction The type of task to be created.
     * @param details     The details of the task to be created.
     * @throws JimmyException If the task type is invalid or the details are invalid.
     */
    public void createNewTask(String instruction, String details) throws JimmyException {
        Task newTask = null;
        try {
            switch (instruction) {
            case "todo":
                newTask = createNewTodo(details);
                break;
            case "deadline":
                try {
                    newTask = createNewDeadline(details);
                    break;
                } catch (IllegalArgumentException e) {
                    throw new JimmyException("Please enter a valid deadline.");
                }
            case "event":
                try {
                    newTask = createNewEvent(details);
                    break;
                } catch (IllegalArgumentException e) {
                    throw new JimmyException("Please enter a valid start and end date.");
                }
            default:
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            throw new JimmyException("Please enter a valid task type.");
        } catch (DateTimeParseException e) {
            throw new JimmyException("Error: Cannot parse the date written by the user. Please uselist the following format: dd-MMM-yyyy (e.g. 19-01-2025)");
        }

        taskList.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println(newTask);
        System.out.println(generateListCounter() + "\n");
    }

    /**
     * Creates a new todo task.
     *
     * @param details The details of the todo task.
     * @return The new todo task.
     */
    private Todo createNewTodo(String details) {
        return new Todo(details, false);
    }

    /**
     * Creates a new deadline task.
     *
     * @param details The details of the deadline task.
     * @return The new deadline task.
     * @throws JimmyException If the deadline task details are invalid.
     */
    private Deadline createNewDeadline(String details) throws JimmyException {
        String[] deadlineDetails = parser.parseDeadlineDetails(details);
        String deadlineName = deadlineDetails[0], deadline = deadlineDetails[1];
        if (deadlineName.length() == 0 || deadline.length() == 0) {
            throw new JimmyException("Please check that you have entered a deadline name and a deadline.");
        }
        return new Deadline(deadlineName, deadline, false);
    }

    /**
     * Creates a new event task.
     *
     * @param details The details of the event task.
     * @return The new event task.
     * @throws JimmyException If the event task details are invalid.
     */
    private Event createNewEvent(String details) throws JimmyException {
        String[] eventDetails = parser.parseEventDetails(details);
        String eventName = eventDetails[0], start = eventDetails[1], end = eventDetails[2];
        if (eventName.length() == 0 || start.length() == 0 || end.length() == 0) {
            throw new JimmyException("Please check that you have entered a event name, a start time and an end time.");
        }
        return new Event(eventName, start, end, false);
    }

    /**
     * Generates the counter for the number of tasks in the task list.
     *
     * @return The counter for the task list.
     */
    private String generateListCounter() {
        if (taskList.size() == 0) {
            return "You have no tasks, create some now!";
        } else if (taskList.size() == 1) {
            return "Now you have 1 task in the list.";
        } else {
            return "Now you have " + taskList.size() + " tasks in the list.";
        }
    }

    /**
     * Deletes a task from the task list.
     *
     * @param taskIndex The index of the task to be deleted.
     * @throws JimmyException If the task index is invalid.
     */
    public void deleteTask(String taskIndex) throws JimmyException {
        int deleteTask;
        try {
            deleteTask = Integer.parseInt(taskIndex) - 1;
        } catch (NumberFormatException e) {
            throw new JimmyException("Please only enter an integer.");
        }
        if (deleteTask < 0) {
            throw new JimmyException("Please only enter a positive integer.");

        } else if (deleteTask >= getListSize()) {
            throw new JimmyException("The task you are looking for does not exist.");
        }
        Task deletedTask = taskList.remove(deleteTask);
        System.out.println("Noted. I've removed this task:");
        System.out.println(deletedTask);
        System.out.println(generateListCounter() + "\n");
    }

    /**
     * Displays the tasks in the task list.
     */
    public void displayTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            if (i == taskList.size() - 1) {
                System.out.println((i + 1) + "." + taskList.get(i).toString() + "\n");
            } else {
                System.out.println((i + 1) + "." + taskList.get(i).toString());
            }
        }
    }

    /**
     * Marks a task as complete.
     *
     * @param taskIndex The index of the task to be marked as complete.
     * @throws JimmyException If the task index is invalid.
     */
    public void markTaskComplete(String taskIndex) throws JimmyException {
        int completeTask;
        try {
            completeTask = Integer.parseInt(taskIndex) - 1;
        } catch (NumberFormatException e) {
            throw new JimmyException("Please only enter an integer.");
        }
        if (completeTask < 0) {
            throw new JimmyException("Please only enter a positive integer.");

        } else if (completeTask >= getListSize()) {
            throw new JimmyException("The task you are looking for does not exist.");
        }
        Task curr = taskList.get(completeTask);
        curr.markAsComplete();
    }

    /**
     * Marks a task as incomplete.
     *
     * @param taskIndex The index of the task to be marked as incomplete.
     * @throws JimmyException If the task index is invalid.
     */
    public void markTaskIncomplete(String taskIndex) throws JimmyException {
        int incompleteTask;
        try {
            incompleteTask = Integer.parseInt(taskIndex) - 1;
        } catch (NumberFormatException e) {
            throw new JimmyException("Please only enter an integer.");
        }
        if (incompleteTask < 0) {
            throw new JimmyException("Please only enter a positive integer.");

        } else if (incompleteTask >= getListSize()) {
            throw new JimmyException("The task you are looking for does not exist.");
        }
        Task curr = taskList.get(incompleteTask);
        curr.markAsIncomplete();
    }

    /**
     * Gets the size of the task list.
     *
     * @return The size of the task list.
     */
    private int getListSize() {
        return taskList.size();
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }
}
