package oak.task;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import oak.task.model.Deadline;
import oak.task.model.Event;
import oak.task.model.Task;
import oak.task.model.Todo;
import oak.utility.FileUtility;


/**
 * The TaskService Class to handle all backend-related items to do with tasks
 */
public class TaskService {
    /** An Array of all the tasks in the system */
    public static final ArrayList<Task> TASKS = new ArrayList<>();
    /** The relative file path to the tasklist.txt where all the tasks are stored  */
    private final String tasklistFilePath = "/src/main/resources/tasklist.txt";
    /** The separator for the tasklist.txt */
    private final String taskListSep = "\\|";
    /** An instance of the file utility class to load, save and delete tasks */
    private FileUtility fileUtility = new FileUtility();

    public TaskService() {
        this.loadTasks();
    }

    /**
     * Load tasks from the tasklist.txt (located at this.taskListFilePath),
     * and calls a helper method, `parseTaskList` to parse each line to save it into this.tasks
     */
    public void loadTasks() {
        ArrayList<String> fileData = new ArrayList<>();

        try {
            fileData = this.fileUtility.loadFile(this.tasklistFilePath);
        } catch (FileNotFoundException e) {
            System.out.println("Error reading file.. Unable to load tasks from "
                    + this.tasklistFilePath);
        }

        for (String line : fileData) {
            this.parseTaskList(line);
        }
    }

    /**
     * Saves the Task item in tasklist.txt (located at this.taskListFilePath)
     *
     * @param task to be saved
     * @throws IOException
     */
    private void saveTask(Task task) throws IOException {
        this.fileUtility.writeToFile(this.tasklistFilePath, task.toTaskListStringFormat());
    }

    /**
     * Deletes the Task item from tasklist.txt (located at this.taskListFilePath)
     *
     * @param task to be deleted
     * @throws IOException
     */
    private void removeTask(Task task) throws IOException {
        this.fileUtility.removeLineFromFile(this.tasklistFilePath, task.toTaskListStringFormat());
    }

    /**
     * Parses a line of the tasklist and adds the task to this.tasks
     *
     * @param line of the tasklist
     */
    private void parseTaskList(String line) {
        String[] task = line.split(this.taskListSep);
        Task newTask = null;

        if (task.length <= 1) {
            return;
        }

        Boolean isCompleted = task[1].equals("1");

        if (task[0].equals(Todo.ICON_TODO)) {
            newTask = new Todo(task[2], isCompleted);
        } else if (task[0].equals(Deadline.ICON_DEADLINE)) {
            newTask = new Deadline(task[2], isCompleted, task[3]);
        } else if (task[0].equals(Event.ICON_EVENT)) {
            newTask = new Event(task[2], isCompleted, task[3], task[4]);
        } else {
            System.err.println("Error processing tasklist.txt, skipping this line: "
                    + line);
        }

        this.TASKS.add(newTask);
    }


    /**
     * Add todo task to this.tasks, saves it and returns a string updating the status of the operation
     *
     * @param taskName the task name
     * @return the string updating the status of the operation
     * @throws IOException the io exception
     */
    public String addTodo(String taskName) throws IOException {
        Todo newTodo = new Todo(taskName);

        int expectedLength = this.TASKS.size() + 1;

        this.TASKS.add(newTodo);
        this.saveTask(newTodo);

        assert expectedLength == this.TASKS.size();

        return String.format("Added new Todo: %s", taskName);
    }

    /**
     * Add deadline task to this.tasks, saves it and returns a string updating the status of the operation
     *
     * @param taskName   the task name
     * @param byDateTime the by date time
     * @return the string updating the status of the operation
     * @throws IOException the io exception
     */
    public String addDeadline(String taskName, String byDateTime) throws IOException {
        Deadline newDeadline = new Deadline(taskName, byDateTime);

        int expectedLength = this.TASKS.size() + 1;

        this.TASKS.add(newDeadline);
        this.saveTask(newDeadline);

        assert expectedLength == this.TASKS.size();

        return String.format("Added new Deadline: %s with Due Date: %s", taskName, byDateTime);
    }

    /**
     * Add event task to this.tasks, saves it and returns a string updating the status of the operation
     *
     * @param taskName     the task name
     * @param fromDateTime the from date time
     * @param toDateTime   the to date time
     * @return the string updating the status of the operation
     * @throws IOException the io exception
     */
    public String addEvent(String taskName, String fromDateTime, String toDateTime) throws IOException {
        Event newEvent = new Event(taskName, fromDateTime, toDateTime);

        int expectedLength = this.TASKS.size() + 1;

        this.TASKS.add(newEvent);
        this.saveTask(newEvent);

        assert expectedLength == this.TASKS.size();

        return String.format("Added new Event: %s occurring from %s to %s", taskName, fromDateTime, toDateTime);
    }

    /**
     * Delete task from this.tasks and tasklist.txt (located at this.taskListFilePath)
     *
     * @param taskId the task id
     * @return the string updating the status of the operation
     * @throws IOException the io exception
     */
    public String deleteTask(int taskId) throws IOException {
        int expectedLength = this.TASKS.size() - 1;

        Task removedTask = this.TASKS.remove(taskId);
        this.removeTask(removedTask);

        assert expectedLength == this.TASKS.size();

        return String.format("Are you giving up? Or is this task no longer needed?\n"
                        + "Hmmm.. I've deleted Task %s for you for now.\nBut, I'll be watching you.",
                taskId + 1);
    }

    /**
     * Mark task completed
     *
     * @param taskId the task id
     * @return the string
     */
    public String markTaskCompleted(int taskId) throws IOException {
        String originalTaskString = this.TASKS.get(taskId).toTaskListStringFormat();
        this.TASKS.get(taskId).markTaskCompleted();
        this.fileUtility.updateFile(this.tasklistFilePath, originalTaskString,
                this.TASKS.get(taskId).toTaskListStringFormat());

        return "Ok! I've marked Task "
                + (taskId + 1)
                + " as completed!";
    }

    /**
     * Mark task uncompleted
     *
     * @param taskId the task id
     * @return the string
     */
    public String markTaskUncompleted(int taskId) throws IOException {
        String originalTaskString = this.TASKS.get(taskId).toTaskListStringFormat();
        this.TASKS.get(taskId).markTaskNotCompleted();
        this.fileUtility.updateFile(this.tasklistFilePath, originalTaskString,
                this.TASKS.get(taskId).toTaskListStringFormat());

        return "Hmmm, were you teasing me?\n"
                + "Well, I've marked Task "
                + (taskId + 1)
                + " as uncompleted,\n"
                + "But don't do this again, you hear me?";
    }

    /**
     * Gets all tasks.
     *
     * @return All the tasks, formatted in a string and seperated by '\n'
     */
    public String getAllTasks() {
        StringBuilder returnVal = new StringBuilder();

        for (int i = 0; i < this.TASKS.size(); i++) {
            returnVal.append(String.format("%d. %s", i + 1, this.TASKS.get(i)));

            // Only add new line if its not the last task
            if (i < this.TASKS.size() - 1) {
                returnVal.append("\n");
            }
        }

        return returnVal.toString();
    }

    /**
     * Find all tasks with names that match the `matchingValue`
     *
     * @param matchingValue
     * @return All the tasks matching the value passed in (`matchingValue`), formatted in a string and separated by '\n'
     */
    public String findTasks(String matchingValue) {
        StringBuilder returnVal = new StringBuilder();

        for (int i = 0; i < this.TASKS.size(); i++) {
            if (this.TASKS.get(i).getName().contains(matchingValue)) {
                returnVal.append(String.format("%d. %s", i + 1, this.TASKS.get(i)));

                // Only add new line if its not the last task
                if (i < this.TASKS.size() - 1) {
                    returnVal.append("\n");
                }
            }
        }

        return returnVal.toString();
    }
}
