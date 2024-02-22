package destiny;

import java.util.ArrayList;

/**
 * Used to store and manage the list of tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>(100);

    /**
     * Loads given ArrayList of tasks into the tasklist.
     *
     * @param taskList List of tasks.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }


    /**
     * Returns task based on index.
     *
     * @param i index of the given task.
     * @return Specified task.
     */

    public Task get(int i) {
        return taskList.get(i);
    }

    /**
     * Gets the TaskList.
     *
     * @return the TaskList as an ArrayList.
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Retrieves the current size of the task list.
     *
     * @return Size of the task list.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Adds given task into the task list like a stack.
     *
     * @param newTask Task that will be inserted into the TaskList.
     * @return Message for successful addition of task.
     */
    public String addTask(Task newTask) throws DestinyException {
        for (Task task: taskList) {
            boolean isDuplicate = newTask.equals(task);
            if (isDuplicate) {
                throw new DestinyException("This task already exists!");
            }
        }

        taskList.add(newTask);
        return "Got it. I've added this task:\n   " + newTask.toString()
                + "\nNow you have " + taskList.size() + (taskList.size() > 1 ? " tasks " : " task ")
                + "in the list.";
    }

    /**
     * Removes the specified task from the TaskList.
     *
     * @param index The index of the task to be deleted.
     * @return Message for successful deletion of task.
     * @throws DestinyException If TaskList is empty, or the index is invalid.
     */
    public String delete(int index) throws DestinyException {
        if (taskList.size() == 0) {
            throw new DestinyException("Nothing is in the list yet");
        }
        if (index < 1 || index > taskList.size()) {
            throw new DestinyException("Please enter a number between 1 and " + taskList.size());
        }
        Task tempTask = taskList.get(index - 1);
        taskList.remove(index - 1);
        return "Noted. I've removed this task:\n   " + tempTask.toString()
                + "\nNow you have " + taskList.size() + (taskList.size() > 1 ? " tasks " : " task ")
                + "in the list.";
    }
}
