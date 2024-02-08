package duke;
import java.util.ArrayList;
/**
 * Class responsible for handling the logic of the task lists.
 */
public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();
    /**
     * Create a task list based on the contents.
     * @param contents tasks to be added to task list
     */
    public TaskList(ArrayList<String> contents) {
        for (String content : contents) {
            addTask(parser(content));
        }
    }
    /**
     * Prints the task list to console.
     */
    public void returnList() {
        if (taskList.size() == 0) {
            System.out.println("You have no items in your list.");
            return;
        }
        for (int i = 1; i < taskList.size() + 1; i++) {
            System.out.println(i + ". " + taskList.get(i - 1).toString());
        }
    }
    /**
     * Adds task to the task list.
     * @param task task to be added to task list
     */
    public void addTask(Task task) {
        taskList.add(task);
        System.out.println("Got it. Added: " + task.toString());
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
    public void mark(int i) {
        this.taskList.get(i - 1).markDone();
        this.returnList();
    }
    /**
     * Marking task at index i as undone. Displays list at the end of marking.
     */
    public void unmark(int i) {
        this.taskList.get(i - 1).markUndone();
        this.returnList();
    }
    /**
     * This function removes a task from the task list.
     * @param index remove index'th task
     */
    public void delete(int index) {
        this.taskList.remove(index - 1);
        System.out.println("Deleted task no. " + index);
        System.out.println("You have " + this.len() + " tasks left");
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
        for (Task task : this.taskList) {
            if (task.toString().toLowerCase().contains(keyword.toLowerCase())) {
                return task.toString();
            }
        }
        return "There are no matching tasks found.";
    }
    @Override
    public String toString() {
        String s = "";
        for (int i = 1; i < taskList.size() + 1; i++) {
            s += (taskList.get(i - 1).toString() + "\n");
        }
        return s;
    }
}
