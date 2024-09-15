package jayne.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import jayne.JayneException;
import jayne.Storage;

/**
 * Represents a list of tasks. This class handles operations such as
 * adding, deleting, and marking tasks as done or not done.
 */
public class TaskList {
    private static final String NO_TASKS_FOUND_WITH_THE_KEYWORD = "No tasks found with the keyword: ";
    private final List<Task> taskArray;
    private int taskCount;

    private Storage storage;

    /**
     * Constructs a new TaskList and initializes it with tasks loaded from storage.
     *
     * @param storage the Storage object used for loading and saving tasks.
     */
    public TaskList(Storage storage) throws JayneException {
        this.taskArray = new ArrayList<>();
        this.storage = storage;
        assert storage != null : "Storage should not be null";
        storage.renameFileIfExists();
        storage.loadTasks(taskArray);
    }
    /**
     * Deletes the task at the specified position in the task list.
     *
     * @param sortCommand the type of sort user wants
     * @return the entire sorted list by the type user wants.
     */
    public String sort(String sortCommand) {
        String indent = "\n";
        switch (sortCommand) {
        case "type":
            return indent + sortByType();
        case "mark":
            return indent + sortByMark();
        case "date":
            return indent + sortByDueDate();
        default:
            return "Invalid sort command, available commands date, mark, type";
        }
    }

    private String sortByType() {
        Collections.sort(taskArray, new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                return getTypePriority(t1) - getTypePriority(t2);
            }

            private int getTypePriority(Task task) {
                if (task instanceof Todo) {
                    return 1;
                }
                if (task instanceof Deadline) {
                    return 2;
                }
                if (task instanceof Event) {
                    return 3;
                }
                return Integer.MAX_VALUE;
            }
        });
        return formatTaskList();
    }

    private String sortByMark() {
        Collections.sort(taskArray, Comparator.comparing(Task::isCompleted));
        return formatTaskList();
    }

    private String sortByDueDate() {
        Collections.sort(taskArray, new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                LocalDate date1 = getDate(t1);
                LocalDate date2 = getDate(t2);

                // Handle Todo tasks and them at the end
                if (date1 == null && date2 == null) {
                    return 0; // Both are Todo tasks
                }
                if (date1 == null) {
                    return 1; // Only t1 is Todo
                }
                if (date2 == null) {
                    return -1; // Only t2 is Todo
                }

                // Compare dates for Deadline and Event tasks
                return date1.compareTo(date2);
            }

            /**
             * Extracts the date from Task, Deadline, or Event.
             * Returns null for Todo tasks since they don't have a date.
             */
            private LocalDate getDate(Task task) {
                if (task instanceof Deadline) {
                    return ((Deadline) task).getBy();
                } else if (task instanceof Event) {
                    return ((Event) task).getEnd();
                }
                // Todo tasks or any other tasks without a specific date return null
                return null;
            }
        });
        return formatTaskList();
    }

    private String formatTaskList() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < taskArray.size(); i++) {
            sb.append(i + 1).append(". ").append(taskArray.get(i)).append("\n");
        }
        return sb.toString();
    }

    public int getTaskCount() {
        return taskCount;
    }

    public Storage getStorage() {
        return storage;
    }

    /**
     * Finds and displays tasks whose description contains the given keyword.
     *
     * @param keyword the keyword to search for in task descriptions.
     */
    public String findTask(String keyword) {
        int count = 0;
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < taskArray.size(); i++) {
            Task task = taskArray.get(i);
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                output.append(++count).append(".").append(task).append("\n");
            }
        }
        if (count == 0) {
            output = new StringBuilder(NO_TASKS_FOUND_WITH_THE_KEYWORD + keyword);
        }

        return "Here are the matching tasks in your list:\n" + output;
    }

    /**
     * Deletes the task at the specified position in the task list.
     *
     * @param taskNumber the position of the task in the task list.
     * @return the deleted task.
     * @throws JayneException if the task number is invalid.
     */
    public Task deleteTask(int taskNumber) throws JayneException {
        if (taskNumber < 1 || taskNumber > taskArray.size()) {
            throw new JayneException("Task number " + taskNumber + " does not exist.");
        }
        this.taskCount = taskCount - 1;
        storage.saveTasks(taskArray);
        return taskArray.remove(taskNumber - 1);
    }

    /**
     * Adds a task to the task list and saves the updated list to storage.
     *
     * @param task the task to be added.
     */
    public void addTask(Task task) throws JayneException {
        taskArray.add(task);
        this.taskCount = taskCount + 1;
        storage.saveTasks(taskArray);
    }

    /**
     * Retrieves the task at the specified position in the task list.
     *
     * @param index the position of the task in the task list.
     * @return the task at the specified position, or null if the index is invalid.
     */
    public Task getTask(int index) {
        if (index >= 0 && index <= taskArray.size()) {
            return taskArray.get(index - 1);
        }
        return null;
    }

    /**
     * Marks the task at the specified position in the task list as done and saves the updated list to storage.
     *
     * @param taskNumber the position of the task in the task list.
     */
    public void markTaskAsDone(int taskNumber) throws JayneException {
        if (taskNumber >= 1 && taskNumber <= taskArray.size()) {
            taskArray.get(taskNumber - 1).markAsDone();
            storage.saveTasks(taskArray);
        }
    }

    /**
     * Marks the task at the specified position in the task list as not done and saves the updated list to storage.
     *
     * @param taskNumber the position of the task in the task list.
     */
    public void markTaskAsNotDone(int taskNumber) throws JayneException {
        if (taskNumber >= 1 && taskNumber <= taskArray.size()) {
            taskArray.get(taskNumber - 1).markAsNotDone();
            storage.saveTasks(taskArray);
        }
    }

    /**
     * Displays all tasks in the task list to the user.
     */
    public String display() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < taskArray.size(); i++) {
            output.append(i + 1).append(". ").append(taskArray.get(i).toString()).append("\n");
        }
        return "Here are the tasks in your list:\n" + output;
    }
}
