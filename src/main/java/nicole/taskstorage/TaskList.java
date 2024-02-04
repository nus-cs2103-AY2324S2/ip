package nicole.taskstorage;

import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import nicole.nicoleexceptions.NicoleException;
import nicole.task.Task;

public class TaskList {
    protected static final List<Task> TASKS = new ArrayList<>();
    private final Storage STORAGE;

    /**
     * Initialises a TaskList and creates Storage for tasks.
     *
     */
    public TaskList() {
        this.STORAGE = new Storage();
    }

    private void crudChecker(int taskNumber) throws NicoleException {
        if (taskNumber <= 0 || taskNumber > TaskList.TASKS.size()) {
            throw new NicoleException("Huh? That's not a valid item number :')");
        }
    }

    /**
     * Unmarks a task in the list.
     *
     * @return a success response if the task is unmarked
     * @throws NicoleException if the unmark index is out of bounds of the list or the task is already unmarked.
     */
    public String unmarkTask(int taskNumber) throws NicoleException {
        this.crudChecker(taskNumber);
        String unmarkedMessage = TaskList.TASKS.get(taskNumber - 1).markUndone();
        this.STORAGE.saveTasksToFile();
        return unmarkedMessage;
    }

    /**
     * Marks a task in the list.
     *
     * @return a success response if the task is marked
     * @throws NicoleException if the mark index is out of bounds of the list or the task is already marked.
     */
    public String markTask(int taskNumber) throws NicoleException {
        this.crudChecker(taskNumber);
        String markedMessage = TaskList.TASKS.get(taskNumber - 1).markDone();
        this.STORAGE.saveTasksToFile();
        return markedMessage;
    }

    /**
     * Deletes a task in the list.
     *
     * @return a success response if the task is deleted.
     * @throws NicoleException if the delete index is out of bounds of the list.
     */
    public String deleteTask(int taskNumber) throws NicoleException {
        this.crudChecker(taskNumber);
        TaskList.TASKS.remove(taskNumber - 1);
        this.STORAGE.saveTasksToFile();
        return "Phew...deleted  :>";
    }

    /**
     * Adds a task to the list.
     *
     * @return a success response if the task is added.
     * @throws NicoleException if there are write issues to tasks.txt
     */
    public String addTask(Task newTask) throws NicoleException {
        TaskList.TASKS.add(newTask);
        try {
            FileWriter taskFileWriter = new FileWriter("tasks.txt", true);
            taskFileWriter.write(newTask.toString() + "\n");
            taskFileWriter.close();
        } catch (IOException e) {
            throw new NicoleException("I couldn't save the task >< try again plss");
        }
        return "Oki I added " + "\"" + newTask.toString().substring(7) + "\"";
    }

    /**
     * Searches for a task using a specific keyword.
     *
     * @param name the keyword.
     * @return a filler message and the matching tasks, each on a new line.
     * @throws NicoleException if there are no matching tasks.
     */
    public String findTasks(String name) throws NicoleException {
        StringBuilder namesMatchingTasks = new StringBuilder();
        int numMatchingTasks = 0;
        namesMatchingTasks.append("Hmmm let me see...").append("\n");
        for (int i = 0; i < TaskList.TASKS.size(); i++) {
            if (TaskList.TASKS.get(i).contains(name)) {
                namesMatchingTasks.append(i + 1).append(". ").append(TASKS.get(i)).append("\n");
                numMatchingTasks += 1;
            }
        }
        if (numMatchingTasks == 0) {
            throw new NicoleException("Sorry I couldn't find any matching tasks");
        }
        return namesMatchingTasks.toString();
    }

    /**
     * Lists the present tasks. Sorts the list by task date if the user requests it
     * before printing the tasks.
     *
     * @return a filler message and the saved tasks or just a filler message if there are no tasks yet.
     */
    public String listTasks() {
        StringBuilder savedTasks = new StringBuilder();
        if (TaskList.TASKS.size() == 0) {
            savedTasks.append("No tasks yet. Let's make some moves BD");
        } else {
            savedTasks.append("Here's the tasks I saved so far,").append("\n");
        }

        for (int i = 0; i < TaskList.TASKS.size(); i++) {
            savedTasks.append(i + 1).append(". ").append(TaskList.TASKS.get(i)).append("\n");
        }
        return savedTasks.toString();
    }

    /**
     * Sorts the list by task date if the user requests priority ordering.
     *
     * @return a success message if the tasklist is sorted.
     */
    public String sortTasksByPriority() {
        Comparator<Task> sorter = (task1, task2) -> {
            if (task1.getDate().isBefore(task2.getDate())) {
                return -1;
            } else if (task1.getDate().isEqual(task2.getDate())) {
                return 0;
            } else {
                return 1;
            }
        };
        TaskList.TASKS.sort(sorter);
        return "Alriiiiighty sorted by date :6";
    }
}
