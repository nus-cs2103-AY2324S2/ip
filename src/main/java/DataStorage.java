import CustomExceptions.NoTaskCreatedYetException;
import CustomExceptions.TooManyTasksException;
import TaskList.Task;

import java.util.ArrayList;
import java.util.LinkedList;

public class DataStorage {
    private ArrayList<Task> tasksList;
    private int taskCount;
    private int maxTask;

    public DataStorage(int maxTask) {
        this.maxTask = maxTask;
        this.tasksList = new ArrayList<>();
        this.taskCount = 0;
    }

    public Task getTask(int index) {
        if (index > this.maxTask) {
            throw new IndexOutOfBoundsException();
        } else {
            return this.tasksList.get(index);
        }
    }

    public void addTask(Task task) throws TooManyTasksException {
        if (taskCount >= maxTask) {
            throw new TooManyTasksException();
        } else {
            this.tasksList.add(task);
            this.taskCount++;
        }
    }

    public void setTaskStatus(int taskIndex, boolean status) throws NoTaskCreatedYetException, NoTaskCreatedYetException {
        if (taskIndex < 0 || taskIndex > this.maxTask) {
            throw new IndexOutOfBoundsException();
        } else if (taskIndex >= taskCount) {
            // It is a valid index, but there is no task there yet.
            throw new NoTaskCreatedYetException();
        } else {
            this.tasksList.get(taskIndex).setDone(status);
        }
    }


    public int getTaskCount() {
        return this.taskCount;
    }


    public void deleteTask(int indexToDelete) {
        if (indexToDelete > taskCount) {
            throw new IndexOutOfBoundsException();
        } else {
            this.tasksList.remove(indexToDelete);
            taskCount--;
        }
    }
}
