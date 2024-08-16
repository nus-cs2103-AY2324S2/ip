package podz.stubs;

import java.util.ArrayList;

import podz.task.Task;
import podz.task.TaskList;
import podz.task.Todo;

/**
 * Represents a stub for TaskList used for testing purposes.
 */
public class TaskListStub extends TaskList {
    /**
     * Constructs a TaskListStub object with the given list of tasks.
     *
     * @param tasks the list of tasks to initialize the TaskListStub with.
     */
    public TaskListStub(ArrayList<Task> tasks) {
        super(tasks);
    }
    
    /**
     * Deletes a task at the specified index.
     *
     * @param i the index of the task to delete.
     */
    @Override
    public void deleteTask(int i) {
        super.tasks.remove(i);
    }

    /**
     * Marks a task at the specified index.
     *
     * @param i the index of the task to mark.
     */
    @Override
    public void markTask(int i) {
        super.tasks.get(i).mark();
    }

    /**
     * Initializes the TaskListStub with three predefined tasks.
     */
    public void initDataThreeTasks() {
        super.tasks.add(new Todo("eat chicken"));
        super.tasks.add(new Todo("sleep"));
        super.tasks.add(new Todo("read"));
    }

    /**
     * Retrieves the list of tasks in the TaskListStub.
     *
     * @return the list of tasks.
     */
    public ArrayList<Task> getTaskList() {
        return super.tasks;
    }
}
