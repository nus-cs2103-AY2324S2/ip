package hal.task;

import hal.exception.HALException;
import hal.gui.Parser;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * The TaskList class manages a list of tasks and provides methods for task manipulation.
 */
public class TaskList {
    ArrayList<Task> taskList;
    private static final boolean IS_DONE_DEFAULT = false;
    private final Parser parser;

    /**
     * Constructs a new TaskList object.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
        this.parser = new Parser();
    }

    /**
     * Adds a task to the task list based on user input.
     *
     * @param userInput The user input containing the task description.
     * @return A message indicating the result of the add operation.
     */
    public String addTask(String userInput) {
        String returnOutput;

        try {
            assert(userInput.length() > 1);

            ArrayList<String> parsedInputArray = parser.parse(userInput);
            String taskType = parsedInputArray.get(0);
            String description = parsedInputArray.get(1);

            if (taskType.equalsIgnoreCase("todo")) {
                taskList.add(new Todo(IS_DONE_DEFAULT, description));

            } else if (taskType.equalsIgnoreCase("deadline")) {
                String deadlineBy = parsedInputArray.get(2);
                taskList.add(new Deadline(IS_DONE_DEFAULT, description, deadlineBy));

            } else if (taskType.equalsIgnoreCase("event")) {
                String eventFrom = parsedInputArray.get(2);
                String eventTo = parsedInputArray.get(3);
                taskList.add(new Event(IS_DONE_DEFAULT, description, eventFrom, eventTo));

            }

            Task taskObject = taskList.get(taskList.size() - 1);
            returnOutput = taskObject.toString();

        } catch (HALException | DateTimeParseException | AssertionError e) {
            System.out.println(e.getMessage());
            returnOutput = "Give me clearer instructions. I cannot do that.";
        }

        return returnOutput;
    }

    /**
     * Removes a task from the task list.
     *
     * @param taskIndex The index of the task to be removed.
     * @return A message indicating the result of the remove operation.
     */
    public String removeTask(int taskIndex) {
        Task taskObject = taskList.get(taskIndex);
        taskList.remove(taskIndex);

        return taskObject.toString();
    }

    /**
     * Marks a task as done.
     *
     * @param taskIndex The index of the task to be marked as done.
     * @return A message indicating the result of the mark as done operation.
     */
    public String markAsDone(int taskIndex) {
        Task t = taskList.get(taskIndex);
        t.markAsDone();
        return t.toString();
    }

    /**
     * Marks a task as undone.
     *
     * @param taskIndex The index of the task to be marked as undone.
     * @return A message indicating the result of the mark as undone operation.
     */
    public String markAsUndone(int taskIndex) {
        Task t = taskList.get(taskIndex);
        t.markAsUndone();
        return t.toString();
    }

    /**
     * Lists all tasks in the task list.
     *
     * @return A string containing all tasks in the task list.
     */
    public String listTasks() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < taskList.size(); i++) {
            Task t = taskList.get(i);

            stringBuilder.append(String.format("%d. ", i + 1))
                    .append(String.format("%s\n", t.toString()));
        }

        return stringBuilder.toString();
    }

    /**
     * Lists tasks in the task list matching a given keyword.
     *
     * @param findKeyword The keyword to search for.
     * @return A string containing tasks in the task list matching the keyword.
     */
    public String listMatchingTasks(String findKeyword) {

        StringBuilder stringBuilder = new StringBuilder();
        int foundTaskNumber = 1;

        for (int i = 0; i < taskList.size(); i++) {
            Task t = taskList.get(i);
            String taskDescription = t.getDescription();
            int index = taskDescription.indexOf(findKeyword);

            if (index != -1) {
                stringBuilder.append(String.format("%d. ", foundTaskNumber))
                        .append(String.format("%s\n", t.toString()));

                foundTaskNumber++;
            }
        }

        return stringBuilder.toString();
    }

    /**
     * Returns the task list.
     *
     * @return The task list.
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Initializes the task list with previous tasks.
     *
     * @param prevTaskList The list of previous tasks to initialize the task list with.
     */
    public void initialisePrevTaskList(ArrayList<Task> prevTaskList) {
        System.out.println(prevTaskList);
        this.taskList = prevTaskList;
    }
}
