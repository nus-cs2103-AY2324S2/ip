package utils;

import java.util.ArrayList;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private static final String FILE_NAME = "data/task_list.txt";
    private final ArrayList<Task> list;

    /**
     * Constructs an empty taskList.
     */
    public TaskList() {
        list = new ArrayList<>();
    }

    /**
     * Adds a task to the list.
     *
     * @param t The task to be added.
     */
    public void addTask(Task t) {
        list.add(t);
        FileManager.writeFile(FILE_NAME, this);
    }

    public void addTaskWithoutFileWrite(Task t) {
        list.add(t);
    }

    /**
     * Returns the number of tasks in the list.
     */
    public int getLength() {
        return list.size();
    }

    /**
     * Marks a task as completed.
     *
     * @param i The index of the task to mark.
     * @return The marked task.
     */
    public Task markTask(int i) {
        Task t = list.get(i);
        t.mark();
        FileManager.writeFile(FILE_NAME, this);
        return t;
    }

    /**
     * Unmarks a completed task.
     *
     * @param i The index of the task to unmark.
     * @return The unmarked task.
     */
    public Task unmarkTask(int i) {
        Task t = list.get(i);
        t.unmark();
        FileManager.writeFile(FILE_NAME, this);
        return t;
    }

    /**
     * Deletes a task from the list.
     *
     * @param i The index of the task to delete.
     * @return The deleted task.
     */
    public Task deleteTask(int i) {
        return list.remove(i);
    }

    /**
     * Finds tasks in the task list that contain the specified query in their descriptions.
     *
     * @param query The query to search for within task descriptions.
     * @return A new TaskList containing tasks that match the query.
     */
    public TaskList findTasks(String query) {
        TaskList filteredTask = new TaskList();
        for (Task task: list) {
            if (task.hasQueryInDescription(query)) {
                filteredTask.addTaskWithoutFileWrite(task);
            }
        }
        return filteredTask;
    }

    /**
     * Converts the task list to a string in a file-compatible format.
     *
     * @return A string representation of the task list suitable for writing to a file.
     */
    public String convertTaskListToFileString() {
        StringBuilder fileStringBuilder = new StringBuilder();
        for (Task task: list) {
            fileStringBuilder.append(task.convertTaskToFileString()).append("\n");
        }
        return fileStringBuilder.toString();
    }

    /**
     * Converts a string representation of tasks from a file to a TaskList object.
     *
     * @return The TaskList object containing tasks parsed from the file string.
     */
    public static TaskList convertFileStringToTaskList() {
        String fileString = FileManager.readFile(FILE_NAME);
        TaskList newTaskList = new TaskList();
        if (fileString.isEmpty()) {
            return newTaskList;
        }
        String[] taskStrings = fileString.split("\n");
        for (String taskString: taskStrings) {
            String[] taskDetails = taskString.split("\\|");
            Task newTask;
            switch (taskDetails[0]) {
            case "T":
                newTask = new Todo(taskDetails[2]);
                break;
            case "D":
                newTask = new Deadline(taskDetails[2], taskDetails[3]);
                break;
            case "E":
                newTask = new Event(taskDetails[2], taskDetails[3], taskDetails[4]);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + taskDetails[0]);
            }
            if (taskDetails[1].equals("1")) {
                newTask.mark();
            }
            newTaskList.addTask(newTask);
        }
        return newTaskList;
    }

    /**
     * Returns a string representation of the taskList.
     * Each task in the list is converted to a string and appended to the result.
     * Tasks are numbered and listed in the order they are stored in the list.
     */
    @Override
    public String toString() {
        StringBuilder displayString = new StringBuilder();
        int count = 1;
        for (Task task: list) {
            displayString.append(count).append(". ").append(task.toString()).append("\n");
            count++;
        }
        return displayString.toString();
    }
}
