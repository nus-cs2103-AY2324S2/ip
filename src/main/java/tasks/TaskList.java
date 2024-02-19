package tasks;

import java.util.ArrayList;
import java.util.List;


/**
 * The TaskList class represents a list of tasks.
 */
public class TaskList {


    public enum SortType {
        ALPHABETICAL,
        START_DATE,
        END_DATE,
        TASK_TYPE,
        MARK
    }

    private static List<Task> taskList;


    /**
     * Constructs a TaskList object with an empty list of tasks.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Obtain List object with the specified list of tasks.
     * @return the list of tasks in the TaskList
     */
    public List<Task> getTaskList() {
        return taskList;
    }

    /**
     * Adds a task to the list of tasks..
     * @param task the task to be added to the TaskList
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Constructs a TaskList object with the specified list of tasks.
     * @return the number of tasks in the TaskList
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Constructs a representation of the TaskList.
     * @return a String representation of the tasks in the TaskList
     */
    public String showList() {
        int i = 1;
        StringBuilder sb = new StringBuilder();
        for (Task task : taskList) {
            sb.append(i++).append(". ").append(task).append("\n");
        }
        return sb.toString();
    }


    /**
     * Gets a task at a specific index
     * @param index the index of the task to be retrieved
     * @return the task at the specified index
     */
    public Task getTaskAtIndex(int index) {
        return taskList.get(index);
    }

    /**
     * Deletes a task at a specific index
     * @param index the index of the task to be deleted
     */
    public void deleteAtIndex(int index) {
        taskList.remove(index);
    }

    /**
     * Marks a task at a specific index as done
     * @param index the index of the task to be marked as done
     */
    public void markTask(int index) {
        taskList.get(index).mark();
    }

    /**
     * Unmarks a task at a specific index
     * @param index the index of the task to be unmarked
     */
    public void unmarkTask(int index) {
        taskList.get(index).unmark();
    }

    /**
     * Adds a deadline task to the TaskList
     * @param desc description of the task
     * @param start start time of the task
     */
    public void addDeadlineTask(String desc, String start) {
        Task deadlineTask = new Task(desc, false, start);
        taskList.add(deadlineTask);
    }

    /**
     * Adds an event task to the TaskList
     * @param desc description of the task
     * @param start start time of the task
     * @param end end time of the task
     */
    public void addEventTask(String desc, String start, String end) {
        Task eventTask = new Task(desc, false, start, end);
        taskList.add(eventTask);
    }

    /**
     * Finds a list of tasks through the keyword.
     * @param keyword keyword to compare
     * @return the tasks that contain the keyword
     */
    public String findTask(String keyword) {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Task task : taskList) {
            if (task.getDesc().contains(keyword)) {
                sb.append(i++).append(". ").append(task).append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * Sorts the TaskList by the start time of the tasks.
     */
    public void sortTaskList(TaskList.SortType sortType) {
        List<Task> tempTaskList = new ArrayList<>(taskList);
        tempTaskList.sort((task1, task2) -> {
            switch (sortType) {
            case ALPHABETICAL:
                return task1.getDesc().compareTo(task2.getDesc());
            case START_DATE:
                if (task1.getStart() == null) {
                    return task1.getDesc().compareTo(task2.getDesc());
                }
                if (task2.getStart() == null) {
                    return task1.getDesc().compareTo(task2.getDesc());
                }
                return task1.getStart().compareTo(task2.getStart());
            case END_DATE:
                if (task1.getEnd() == null) {
                    return task1.getDesc().compareTo(task2.getDesc());
                }
                if (task2.getEnd() == null) {
                    return task1.getDesc().compareTo(task2.getDesc());
                }
                return task1.getEnd().compareTo(task2.getEnd());
            case TASK_TYPE:
                return task1.getType().compareTo(task2.getType());
            case MARK:
                return Boolean.compare(task1.isDone(), task2.isDone());
            default:
                return 0;
            }
        });
        taskList.clear();
        for (Task task : tempTaskList) {
            taskList.add(task);
        }
    }
}
