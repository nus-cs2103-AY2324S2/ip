package luke;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> taskList;
    private int noTasks;

    /**
     * Constructs a TaskList object with tasks loaded from the specified list.
     *
     * @param saveTaskList the list of tasks loaded from file
     */
    TaskList(ArrayList<String> saveTaskList) {
        this.taskList = new ArrayList<>();
        if (!saveTaskList.isEmpty()) {
            saveTaskList.forEach(this::loadToTaskList);
        }
        this.noTasks = this.taskList.size();
    }

    /**
     * Constructs an empty TaskList object.
     */
    TaskList() {
        this.taskList = new ArrayList<>();
        this.noTasks = 0;
    }

    /**
     * Loads a task from a string and adds it to the task list.
     *
     * @param taskString the string representing the task
     */
    private void loadToTaskList(String taskString) {
        String taskType = taskString.substring(1, 2);
        switch (taskType) {
        case "T":
            Todo todo = new Todo(taskString.substring(7).trim());
            taskList.add(todo);
            break;
        case "D":
            String[] deadlineSplit = taskString.split("by: ");
            String deadlineDescription = deadlineSplit[0].substring(7, deadlineSplit[0].length() - 1);
            String by = deadlineSplit[1].substring(0, deadlineSplit[1].length() - 1).trim();
            Deadline deadline = new Deadline(deadlineDescription, by);
            taskList.add(deadline);
            break;
        case "E":
            String[] eventFirstSplit = taskString.split("from: ");
            String[] eventSecondSplit = eventFirstSplit[1].split(" to: ");
            String eventDescription = eventFirstSplit[0].substring(7, eventFirstSplit[0].length() - 1);
            String from = eventSecondSplit[0].trim();
            String to = eventSecondSplit[1].substring(0, eventSecondSplit[1].length() - 1).trim();
            Event event = new Event(eventDescription, from, to);
            taskList.add(event);
            break;
        default:
            break;
        }
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param taskNo the index of the task to be marked
     */
    public void markTask(int taskNo) {
        taskList.get(taskNo).setToDone();
    }

    /**
     * Unmarks the task at the specified index as not done.
     *
     * @param taskNo the index of the task to be unmarked
     */
    public void unmarkTask(int taskNo) {
        taskList.get(taskNo).setToNotDone();
    }

    /**
     * Lists all tasks in the task list.
     */
    protected String list() {
        StringBuilder taskListString = new StringBuilder();
        for (int i = 0; i < noTasks; i++) {
            taskListString.append((i + 1)).append(". ").append(taskList.get(i).toString()).append("\n");
        }
        return taskListString.toString();
    }

    /**
     * Adds a todo task to the task list.
     *
     * @param description the description of the todo task
     */
    public void addTodo(String description) {
        Todo todo = new Todo(description);
        taskList.add(todo);
        noTasks++;
    }

    /**
     * Adds a deadline task to the task list.
     *
     * @param deadlineDescription the description of the deadline task
     * @param by the deadline of the task
     */
    public void addDeadline(String deadlineDescription, String by) {
        Deadline deadline = new Deadline(deadlineDescription, by);
        taskList.add(deadline);
        noTasks++;
    }

    /**
     * Adds an event task to the task list.
     *
     * @param eventDescription the description of the event task
     * @param from the starting date/time of the event
     * @param to the ending date/time of the event
     */
    public void addEvent(String eventDescription, String from, String to) {
        Event event = new Event(eventDescription, from, to);
        taskList.add(event);
        noTasks++;
    }

    /**
     * Deletes the task at the specified index from the task list.
     *
     * @param taskNo the index of the task to be deleted
     * @return the deleted task
     */
    public Task deleteEvent(int taskNo) {
        Task taskDeleted = taskList.get(taskNo);
        taskList.remove(taskNo);
        noTasks--;
        return taskDeleted;
    }

    /**
     * Searches for tasks containing the specified keyword in their descriptions.
     * Creates a new TaskList containing tasks matching the keyword.
     *
     * @param keyword the keyword to search for in task descriptions
     * @return a new TaskList containing tasks with descriptions matching the keyword
     */
    public TaskList search(String keyword) {
        ArrayList<String> tasksWithKeyword = new ArrayList<>();
        for (Task task: taskList) {
            if (task.getDescription().contains(keyword)) {
                tasksWithKeyword.add(task.toString());
            }
        }
        return new TaskList(tasksWithKeyword);
    }

    /**
     * Returns the task list.
     *
     * @return the task list
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return the number of tasks
     */
    public int getNoTasks() {
        return noTasks;
    }

    /**
     * Returns the task at the specified index in the task list.
     *
     * @param taskNo the index of the task to retrieve
     * @return the task at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public Task getTask(int taskNo) {

        return taskList.get(taskNo);
    }

    /**
     * Returns the most recently added task.
     *
     * @return the most recent task
     */
    public Task getMostRecentTask() {
        return taskList.get(noTasks - 1);
    }
}
