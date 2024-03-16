package jimmy.essentials;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import jimmy.exceptions.JimmyException;
import jimmy.tasks.Deadline;
import jimmy.tasks.Event;
import jimmy.tasks.Task;
import jimmy.tasks.Todo;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> taskList = new ArrayList<>();
    private final Parser parser = new Parser();
    private final Ui ui;

    /**
     * Constructor for jimmy.essentials.TaskList class.
     *
     * @param storage The storage object to load the task list from.
     * @param ui      The user interface object.
     * @throws JimmyException If the file contents cannot be loaded.
     */
    public TaskList(Storage storage, Ui ui) throws JimmyException {
        assert storage != null;
        assert ui != null;
        this.ui = ui;
        storage.loadFileContents(taskList);
    }

    public TaskList() {
        this.ui = new Ui();
    }

    /**
     * Gets the task list.
     *
     * @return The task list.
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Creates a new task and adds it to the task list.
     *
     * @param instruction The type of task to be created.
     * @param details     The details of the task to be created.
     * @throws JimmyException If the task type is invalid or the details are invalid.
     */
    public String createNewTask(String instruction, String details) throws JimmyException {
        assert !instruction.isEmpty();
        assert !details.isEmpty();
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
            throw new JimmyException("Error: Cannot parse the date written by the user. "
                    + "Please use the following format: dd-MM-yyyy (e.g. 19-01-2025)");
        }

        taskList.add(newTask);
        return ui.showAddedClass(newTask.toString(), getListSize());
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
        String deadlineName = deadlineDetails[0];
        String deadline = deadlineDetails[1];

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
        String eventName = eventDetails[0];
        String start = eventDetails[1];
        String end = eventDetails[2];

        if (eventName.length() == 0 || start.length() == 0 || end.length() == 0) {
            throw new JimmyException("Please check that you have entered a event name, a start time and an end time.");
        }

        return new Event(eventName, start, end, false);
    }

    /**
     * Gets the size of the task list.
     *
     * @return The size of the task list.
     */
    private int getListSize() {
        return taskList.size();
    }

    /**
     * Deletes a task from the task list.
     *
     * @param taskIndex The index of the task to be deleted.
     * @return The task that was deleted with the number of tasks left in the list.
     * @throws JimmyException If the task index is invalid.
     */
    public String deleteTask(String taskIndex) throws JimmyException {
        assert !taskIndex.isEmpty();
        int taskToDelete;

        try {
            taskToDelete = Integer.parseInt(taskIndex) - 1;
        } catch (NumberFormatException e) {
            throw new JimmyException("Please only enter an integer.");
        }

        if (taskToDelete < 0) {
            throw new JimmyException("Please only enter a positive integer to represent the task index.");
        } else if (taskToDelete >= getListSize()) {
            throw new JimmyException("The task you are looking for does not exist; "
                    + "you might have entered an index that is larger than the number of tasks.");
        }

        Task deletedTask = taskList.remove(taskToDelete);
        return ui.showDeletedTask(deletedTask.toString(), getListSize());
    }

    /**
     * Displays the tasks in the task list.
     *
     * @return The tasks in the task list.
     */
    public String displayTasks() {
        return ui.showAllTasks(taskList);
    }

    /**
     * Marks a task as complete.
     *
     * @param taskIndex The index of the task to be marked as complete.
     * @return The task that was marked.
     * @throws JimmyException If the task index is invalid.
     */
    public String markTask(String taskIndex) throws JimmyException {
        assert !taskIndex.isEmpty();
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
        return ui.showMarkedTask(curr.toString());
    }

    /**
     * Marks a task as incomplete.
     *
     * @param taskIndex The index of the task to be marked as incomplete.
     * @return The task that was unmarked.
     * @throws JimmyException If the task index is invalid.
     */
    public String unmarkTask(String taskIndex) throws JimmyException {
        assert !taskIndex.isEmpty();
        int taskToUnmark;
        try {
            taskToUnmark = Integer.parseInt(taskIndex) - 1;
        } catch (NumberFormatException e) {
            throw new JimmyException("Please only enter an integer.");
        }
        if (taskToUnmark < 0) {
            throw new JimmyException("Please only enter a positive integer.");

        } else if (taskToUnmark >= getListSize()) {
            throw new JimmyException("The task you are looking for does not exist.");
        }
        Task curr = taskList.get(taskToUnmark);
        curr.markAsIncomplete();
        return ui.showUnmarkedTask(curr.toString());
    }

    /**
     * Finds a task in the task list.
     *
     * @param keyword The keyword to search for.
     * @return The tasks that match the keyword.
     * @throws JimmyException If the keyword is empty.
     */
    public String findTask(String keyword) throws JimmyException {
        if (keyword.length() == 0) {
            throw new JimmyException("Please enter a keyword to search for.");
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getDesc().contains(keyword)) {
                sb.append(i + 1).append(".").append(taskList.get(i).toString());
                sb.append(System.getProperty("line.separator"));
            }
        }
        return ui.showFoundTasks(sb.toString());
    }

    /**
     * Views the schedule for a specific date.
     *
     * @param details The date to view the schedule for.
     * @return The schedule for the date.
     * @throws JimmyException If the date is empty.
     */
    public String viewSchedule(String details) throws JimmyException {
        if (details.length() == 0) {
            throw new JimmyException("Please enter a date to view the schedule for.");
        }

        LocalDate dateOfInterest = LocalDate.parse(details,
                DateTimeFormatter.ofPattern("d-MM-yyyy"));
        StringBuilder sb = new StringBuilder();

        if (dateOfInterest.isBefore(LocalDate.now())) {
            throw new JimmyException("The date you are looking for is in the past.");
        }

        for (Task curr : taskList) {
            if (curr instanceof Event) {
                Event currEvent = (Event) curr;
                if (doesEventCoincideWithDate(currEvent.getStart(), currEvent.getEnd(), dateOfInterest)) {
                    sb.append(curr);
                    sb.append(System.getProperty("line.separator"));
                }
            } else if (curr instanceof Deadline) {
                Deadline currDeadline = (Deadline) curr;
                if (currDeadline.getDeadline().isEqual(dateOfInterest)) {
                    sb.append(curr);
                    sb.append(System.getProperty("line.separator"));
                }
            }
        }
        return ui.showSchedule(sb.toString(),
                dateOfInterest.format(DateTimeFormatter.ofPattern("d MMM yyyy")));
    }

    private boolean doesEventCoincideWithDate(LocalDate start, LocalDate end, LocalDate dateOfInterest) {
        boolean dateEqualsStartOrEnd = start.isEqual(dateOfInterest) || end.isEqual(dateOfInterest);
        boolean dateIsBetweenStartAndEnd = (start.isBefore(dateOfInterest) && end.isAfter(dateOfInterest));

        return (dateEqualsStartOrEnd || dateIsBetweenStartAndEnd);
    }
}
