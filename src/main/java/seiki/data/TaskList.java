package seiki.data;

import static seiki.common.ErrorMessages.ERROR_MESSAGE_INVALID_TASK_NUMBER;

import java.util.ArrayList;

import seiki.data.exception.SeikiException;
import seiki.data.task.Task;

/**
 * Represents the entire task list.
 * Contains the data of the task list.
 */
public class TaskList {
    protected ArrayList<Task> taskList;
    protected int taskCount;

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        taskList = new ArrayList<>();
        taskCount = 0;
    }

    /**
     * Constructs a task list with the given data.
     * @param tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        taskList = tasks;
        taskCount = tasks.size();
    }

    /**
     * Adds task to the task list.
     * @param task
     */
    public void addTask(Task task) {
        taskList.add(task);
        assert taskList.contains(task) : "Task should be added into task list";
        taskCount++;
    }

    /**
     * Deletes task from the task list.
     * @param task
     */
    public void deleteTask(Task task) {
        taskList.remove(task);
        assert !taskList.contains(task) : "Task should be deleted from task list";
        taskCount--;
    }

    public Task getTaskByNumber(int taskNum) {
        return taskList.get(taskNum - 1);
    }

    public int getTaskCount() {
        return taskCount;
    }

    public ArrayList<Task> getAllTasks() {
        return taskList;
    }

    /**
     * Searches for task that matches the {@code keyword}.
     * @param keyword
     */
    public TaskList searchByKeyword(String keyword) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task task : taskList) {
            if (task.hasKeyword(keyword)) {
                result.add(task);
            }
        }
        return new TaskList(result);
    }

    /**
     * Checks if task list is empty.
     * @param errMsg the error message in the exception
     * @throws SeikiException when task list is empty
     */
    public void checkIfListEmpty(String errMsg) throws SeikiException {
        if (taskCount == 0) {
            throw new SeikiException(errMsg);
        }
    }

    /**
     * Checks if the given task number is within range of the task list.
     * @param taskNumber the task number that the user inputs
     * @throws SeikiException when task number is out of range
     */
    public void checkIfNumberValid(Integer taskNumber) throws SeikiException {
        if (taskNumber < 1 || taskNumber > taskCount) {
            throw new SeikiException(ERROR_MESSAGE_INVALID_TASK_NUMBER);
        }
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (taskCount == 0) {
            sb.append("There are currently no task added.");
        } else {
            getAllTaskToString(sb);
        }
        return sb.toString();
    }

    private void getAllTaskToString(StringBuilder sb) {
        for (int i = 0; i < taskList.size(); i++) {
            int taskNum = i + 1;
            String taskString = "\u2794 " + taskNum + ". " + taskList.get(i).toString();
            sb.append(taskString);
            if (taskNum != taskList.size()) {
                sb.append("\n");
            }
        }
    }
}
