package dino;

import java.time.LocalDateTime;
import java.util.ArrayList;

import dino.task.Deadline;
import dino.task.Event;
import dino.task.Task;

/** Represents an ArrayList of Task. */
public class TaskList {

    private ArrayList<Task> taskList;

    /** Constructs a new TaskList. */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Adds the Task into the TaskList.
     *
     * @param task The Task to be added.
     * @throws DinoException If there's a scheduling conflict with an existing task.
     */
    public void addTask(Task task) throws DinoException {
        if (task instanceof Deadline || task instanceof Event) {
            if (isConflict(task)) {
                throw new DinoException("Scheduling conflict: "
                        + "The new task conflicts with an existing task.");
            }
        }
        this.taskList.add(task);
    }

    /**
     * Checks if this task conflicts with another task.
     *
     * @param newTask The other task to check for conflicts with.
     * @return true if there is a conflict, false otherwise.
     */
    public boolean isConflict(Task newTask) {
        if (newTask instanceof Event || newTask instanceof Deadline) {
            LocalDateTime newStart = newTask instanceof Event ? ((Event) newTask).getStartTime()
                    : ((Deadline) newTask).getDateTime();
            LocalDateTime newEnd = newTask instanceof Event ? ((Event) newTask).getEndTime()
                    : ((Deadline) newTask).getDateTime();

            for (Task existingTask : taskList) {
                if (existingTask instanceof Event || existingTask instanceof Deadline) {
                    boolean isOverlap = hasOverlap(existingTask, newStart, newEnd);
                    if (isOverlap) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean hasOverlap(Task existingTask, LocalDateTime newStart, LocalDateTime newEnd) {
        LocalDateTime existingStart = existingTask instanceof Event
                ? ((Event) existingTask).getStartTime()
                : ((Deadline) existingTask).getDateTime();
        LocalDateTime existingEnd = existingTask instanceof Event
                ? ((Event) existingTask).getEndTime()
                : ((Deadline) existingTask).getDateTime();

        return newStart.isBefore(existingEnd) && newEnd.isAfter(existingStart);
    }

    /**
     * Removes the Task from the TaskList.
     *
     * @param taskNum The index of the Task to be removed.
     * @return String representation of the updated TaskList.
     */
    public String deleteTask(int taskNum) throws DinoException {
        if (taskNum < 1 || taskNum > taskList.size()) {
            throw new DinoException("Invalid task number. Please provide a valid task number to delete.");
        }

        Task removedTask = taskList.remove(taskNum - 1);

        return "Noted. I've removed this task:\n"
                + "  " + removedTask + "\n"
                + "Now you have " + taskList.size() + " tasks in the list.";
    }

    /**
     * Lists the Task in the TaskList.
     *
     * @return String representation of TaskList.
     */
    public String listTask() {
        try {
            if (taskList.isEmpty()) {
                throw new DinoException("The list is empty.");
            }
            StringBuilder printTaskList = new StringBuilder("Here are the tasks that you wanted to do:\n");
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                int index = i + 1;
                printTaskList.append(index).append(".").append(task).append("\n");
            }
            return String.valueOf(printTaskList);
        } catch (DinoException e) {
            return (e.getMessage());
        }
    }

    /**
     * Gets the taskList.
     *
     * @return taskList of type ArrayList.
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Gets the size of the taskList.
     *
     * @return integer value of size.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Gets the Task of the specified index.
     *
     * @param index Index of the Task.
     * @return Task in the taskList.
     */
    public Task get(int index) {
        return taskList.get(index);
    }

    /**
     * Searches for Tasks with the specified keyword.
     *
     * @param keyword String value input.
     * @return An ArrayList with the filtered Tasks.
     */
    public ArrayList<Task> findTasksByKeyword(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : taskList) {
            if (task.containsKeyword(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

}
