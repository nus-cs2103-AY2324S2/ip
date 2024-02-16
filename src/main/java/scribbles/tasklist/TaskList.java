package scribbles.tasklist;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import scribbles.task.Deadline;
import scribbles.task.Event;
import scribbles.task.Task;
import scribbles.task.Todo;

/**
 * This class represents the user's task list.
 */
public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task Task to be added to the task list.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param index Index of the task to delete from the task list.
     * @throws IndexOutOfBoundsException If index does not exist in the task list.
     */
    public void deleteTask(int index) throws IndexOutOfBoundsException {
        taskList.remove(index);
    }

    /**
     * Returns the size of the task list.
     *
     * @return Size of task list.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Gets the task at index from the task list.
     *
     * @param index Index of the task to get.
     * @return Task at index.
     * @throws IndexOutOfBoundsException If the index does not exist in the task list.
     */
    public Task get(int index) throws IndexOutOfBoundsException {
        return taskList.get(index);
    }

    /**
     * Sorts tasks in task list by description in alphabetical order.
     */
    public void sortByDescription() {
        Collections.sort(taskList, Comparator.comparing(Task::getDescription));
    }

    /**
     * Sorts task in task list by status of completion, placing the completed tasks first.
     */
    public void sortByCompletedFirst() {
        Collections.sort(taskList, new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                if (!t1.isCompleted() && t2.isCompleted()) {
                    return 1;
                }
                if (t1.isCompleted() && !t2.isCompleted()) {
                    return -1;
                }
                return 0;
            }
        });
    }

    /**
     * Sorts task in task list by status of completion, placing the incompleted tasks first.
     */
    public void sortByIncompleteFirst() {
        Collections.sort(taskList, new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                if (t1.isCompleted() && !t2.isCompleted()) {
                    return 1;
                }
                if (!t1.isCompleted() && t2.isCompleted()) {
                    return -1;
                }
                return 0;
            }
        });
    }

    /**
     * Sorts task in task list by type of task in the order of to-do, deadlines, then events.
     */
    public void sortByType() {
        Collections.sort(taskList, new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                int typeOrder = getOrder(t1) - getOrder(t2);
                return typeOrder;
            }

            private int getOrder(Task task) {
                if (task instanceof Todo) {
                    return 1;
                } else if (task instanceof Deadline) {
                    return 2;
                } else {
                    // task instanceof Event
                    return 3;
                }
            }
        });
    }

    public void sortByDate() {
        Collections.sort(taskList, new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                if (t1 instanceof Todo && !(t2 instanceof Todo)) {
                    return 1;
                } else if (!(t1 instanceof Todo) && t2 instanceof Todo) {
                    return -1;
                } else if (t1 instanceof Todo && t2 instanceof Todo) {
                    return 0;
                } else {
                    LocalDateTime dateTime1 = getDateTime(t1);
                    LocalDateTime dateTime2 = getDateTime(t2);
                    return dateTime1.compareTo(dateTime2);
                }
            }

            public LocalDateTime getDateTime(Task task) {
                if (task instanceof Deadline) {
                    return ((Deadline) task).getBy();
                } else if (task instanceof Event) {
                    return ((Event) task).getStart();
                } else {
                    return null;
                }
            }
        });
    }
}
