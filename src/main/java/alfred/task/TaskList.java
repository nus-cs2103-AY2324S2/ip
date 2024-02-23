package alfred.task;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Encapsulates a list of tasks, providing functionality to manage tasks such as adding, deleting, and retrieving tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructs a new TaskList with a predefined list of tasks.
     *
     * @param list An ArrayList of {@code Task} objects to initialize the task list.
     */
    public TaskList(ArrayList<Task> list){
        this.taskList = list;
    }

    /**
     * Constructs an empty TaskList.
     */
    public TaskList(){
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Adds a task to the task list.
     *
     * @param current The {@code Task} to be added.
     */
    public void addTask(Task current) {
        assert current != null : "task added cannot be null";
        taskList.add(current);
    }

    /**
     * Deletes a task from the task list based on its position in the list.
     * Note: The index is 1-based for user convenience.
     *
     * @param index The 1-based index of the {@code Task} to be removed.
     */
    public void deleteTask(int index){
        taskList.remove(index);
    }

    /**
     * Retrieves a task from the task list based on its position.
     * Note: The index is 0-based, following standard list indexing.
     *
     * @param index The 0-based index of the {@code Task} to be retrieved.
     * @return The {@code Task} at the specified position.
     */
    public Task getTask(int index){
        assert index > 0 && index < taskList.size() : "Index out of bounds";
        return taskList.get(index);
    }

    /**
     * Provides a numbered list of the tasks, with each task represented as a string.
     *
     * @return An ArrayList of strings, each representing a numbered task.
     */
    public ArrayList<String> showList(){
        return (ArrayList<String>) IntStream.range(0, taskList.size())
                .mapToObj(i -> (i+1) + "." + taskList.get(i).getStatus())
                .collect(Collectors.toList());
    }

    /**
     * Returns the current list of tasks.
     *
     * @return An ArrayList of {@code Task} objects representing the current tasks.
     */
    public ArrayList<Task> giveList(){
        return this.taskList;
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The size of the task list.
     */
    public int size(){
        return taskList.size();
    }

    public ArrayList<String> find(String key) {
        ArrayList<String> result = new ArrayList<>();
        int count = 1;
        for (Task i : taskList) {
            if (i.getName().contains(key)) {
                result.add(Integer.toString(count) + "." + i.getStatus());
                count++;
            }
        }
        return result;
    }

}
