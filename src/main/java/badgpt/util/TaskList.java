package badgpt.util;

import badgpt.exceptions.ListEmptyException;
import badgpt.exceptions.ListFullException;
import badgpt.exceptions.SameStatusException;
import badgpt.exceptions.TaskNotFoundException;

import badgpt.tasks.Deadline;
import badgpt.tasks.Event;
import badgpt.tasks.Task;
import badgpt.tasks.ToDo;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a todo list.
 */
public class TaskList {
    private List<Task> tasks = new ArrayList<>(100);
    private TasksUi tasksUi;
    private boolean hasChanges = false;

    public TaskList(TasksUi tasksUi) {
        this.tasksUi = tasksUi;
    }

    /**
     * Stores a Task object in the list. The list has a max size of 100.
     *
     * @param task The Task object to be stored in the list.
     * @throws ListFullException If the list already has 100 tasks.
     */
    public void store(Task task) throws ListFullException {
        if (tasks.size() == 100) {
            throw new ListFullException("Your todo list is currently full.");
        } else {
            tasks.add(task);
            tasksUi.printAddTask(task, tasks.size());
            hasChanges = true;
        }
    }

    /**
     * Lists out all the tasks currently in the list.
     *
     * @throws ListEmptyException If the list does not contain any tasks.
     */
    public void listTasks() throws ListEmptyException {
        if (tasks.size() == 0) {
            throw new ListEmptyException("Your todo list is currently empty.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                tasksUi.printTask(tasks.get(i), i + 1, tasks.size());
            }
        }
    }

    /**
     * Returns the current number of tasks in the list.
     */
    public int getListSize() {
        return tasks.size();
    }

    /**
     * Marks the task in the specified position in the list as complete.
     *
     * @param taskNum The position of the task in the list.
     * @throws TaskNotFoundException If the number entered does not exist in the list.
     * @throws SameStatusException If the task is already complete.
     */
    public void mark(int taskNum) throws TaskNotFoundException, SameStatusException {
        try {
            if (!tasks.get(taskNum).isComplete()) {
                tasks.get(taskNum).complete();
                tasksUi.printMarkUnmarkOutcome(tasks.get(taskNum), 0);
                hasChanges = true;
            } else {
                throw new SameStatusException("The operation cannot be completed.", tasks.get(taskNum), 0);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotFoundException(e.getMessage(), tasks.size());
        }
    }

    /**
     * Marks the task in the specified position in the list as incomplete.
     *
     * @param taskNum The position of the task in the list.
     * @throws TaskNotFoundException If the number entered does not exist in the list.
     * @throws SameStatusException If the task is already incomplete.
     */
    public void unmark(int taskNum) throws TaskNotFoundException, SameStatusException {
        try {
            if (tasks.get(taskNum).isComplete()) {
                tasks.get(taskNum).uncomplete();
                tasksUi.printMarkUnmarkOutcome(tasks.get(taskNum), 1);
                hasChanges = true;
            } else {
                throw new SameStatusException("The operation cannot be completed.", tasks.get(taskNum), 1);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotFoundException(e.getMessage(), tasks.size());
        }
    }

    /**
     * Removes the task in the specified position in the list from the list.
     *
     * @param taskNum The position of the task in the list.
     * @throws TaskNotFoundException If the number entered does not exist in the list.
     */
    public void delete(int taskNum) throws TaskNotFoundException {
        try {
            Task task = tasks.remove(taskNum);
            tasksUi.printDelete(task, tasks.size());
            hasChanges = true;
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotFoundException(e.getMessage(), tasks.size());
        }
    }

    /**
     * Loads the data from the saved task list and adds it to the current run.
     *
     * @param type The type of the task.
     * @param descr The description of the task.
     * @param deadline The deadline for the task, only for tasks of type Deadline.
     * @param from When the task starts, only for tasks of type Event.
     * @param to When the task ends, only for tasks of type Event.
     * @param isComplete Whether the task is complete.
     */
    public void loadData(char type, String descr, String deadline, String from, String to, boolean isComplete) {
        Task task;
        if (type == 'T') {
            task = new ToDo(descr);
        } else if (type == 'E') {
            task = new Event(descr, from, to);
        } else {
            task = new Deadline(descr, deadline);
        }

        if (isComplete) {
            task.complete();
        }

        tasks.add(task);
    }

    /**
     * Finds any tasks containing the specified keyword.
     *
     * @param keyword The term of interest to filter out tasks.
     */
    public void find(String keyword) throws ListEmptyException {
        List<Task> filtered = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                filtered.add(task);
            }
        }

        if (filtered.isEmpty()) {
            throw new ListEmptyException("There are no tasks containing the specified keyword.");
        } else {
            for (int i = 0; i < filtered.size(); i++) {
                tasksUi.printTask(filtered.get(i), i + 1, filtered.size());
            }
        }
    }

    /**
     * Writes any changes to the task list to the save file.
     *
     * @param fileManager The FileManager instance handling the saving of data.
     */
    public void writeChanges(FileManager fileManager) {
        if (hasChanges) {
            String data = "";
            for (Task task : tasks) {
                data += task.saveTask() + "\n";
            }
            fileManager.writeToFile(data);
        }
    }
}
