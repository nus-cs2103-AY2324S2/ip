package riri;

import java.io.IOException;
import java.util.ArrayList;
/**
 * Class responsible for handling the logic of the task lists.
 */
public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();
    private Storage storage = new Storage();
    /**
     * Create a task list based on the contents.
     * @param contents tasks to be added to task list
     */
    public TaskList(ArrayList<String> contents) throws IOException {
        for (String content : contents) {
            loadTask(parser(content));
        }
    }
    /**
     * Prints the task list to console.
     */
    public String returnList() {
        StringBuilder list = new StringBuilder();
        if (taskList.size() == 0) {
            return "You have no items in your list.";
        }
        for (int i = 0; i < taskList.size(); i++) {
            int j = i + 1;
            list.append(j).append(". ").append(taskList.get(i).toString()).append("\n");
        }
        return list.toString();
    }
    /**
     * Adds task to the task list.
     * @param task task to be added to task list
     */
    public String addTask(Task task) throws IOException {
        taskList.add(task);
        storage.writeToFile(this.toString());
        return "Got it. Added: " + task.toString();
    }

    /**
     * Loading up tasks from storage to task list.
     * @param task tasks from storage to be added to task list
     */
    public void loadTask(Task task) {
        taskList.add(task);
    }
    /**
     * Marking task at index i as done. Displays list at the end of marking.
     */
    public String mark(int i) {
        assert(i > 0);
        this.taskList.get(i - 1).markDone();
        return "Marked task number " + i + " as done";
    }
    /**
     * Marking task at index i as undone. Displays list at the end of marking.
     */
    public String unmarked(int i) throws IOException {
        assert(i > 0);
        this.taskList.get(i - 1).markUndone();
        storage.writeToFile(this.toString());
        return "Marked task number " + i + " as not done";
    }
    /**
     * This function removes a task from the task list.
     * @param index remove index'th task
     */
    public String delete(int index) throws IOException {
        assert(index > 0);
        if (taskList.size() < index) {
            return "Cannot delete that";
        }
        this.taskList.remove(index - 1);
        storage.writeToFile(this.toString());
        return "Deleted task no. " + index + ". You have " + this.len() + " tasks left. \n";
    }
    /**
     * Returns the size of the task list.
     */
    private int len() {
        return taskList.size();
    }

    /**
     * Parses the string to create a Task object
     * @param line string that describes a task.
     * @return a task object
     *
     */
    private static Task parser(String line) {
        if (line.startsWith("[D]")) {
            return Deadline.parseDeadlineFromString(line);
        } else if (line.startsWith("[E]")) {
            return Event.parseEventFromString(line);
        } else if (line.startsWith("[T]")) {
            return Todo.parseTodoFromString(line);
        }
        return null;
    }
    /**
     * Searches for tasks in the task list containing a specific keyword in their description.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return A list of tasks that match the search criteria. If no matches are found, an empty list is returned.
     */
    public String searchTasks(String keyword) {
        StringBuilder matchingTasks = new StringBuilder();
        for (Task task : this.taskList) {
            if (task.toString().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.append(task.toString()).append("\n");
            }
        }
        if (matchingTasks.length() == 0) {
            return "No matching tasks found";
        }
        return matchingTasks.toString();
    }
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            s.append(taskList.get(i).toString()).append("\n");
        }
        return s.toString();
    }
}
