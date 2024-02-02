package taskstorage;

import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import nicoleexceptions.NicoleException;

import task.Task;

public class TaskList {
    protected static final List<Task> TASKS = new ArrayList<>();
    private final Storage storage;

    /**
     * Initialises a TaskList and creates Storage for tasks.
     *
     * @throws NicoleException if Storage throws it due to IO issues with the
     *                         tasks file.
     */
    public TaskList() throws NicoleException {
        this.storage = new Storage();
    }

    private void crudChecker(int taskNumber) throws NicoleException {
        if (taskNumber <= 0 || taskNumber > TaskList.TASKS.size()) {
            throw new NicoleException("Huh? That's not a valid item number :')");
        }
    }

    /**
     * Unmarks a task in the list.
     *
     * @throws NicoleException if the unmark index is out of bounds of the list.
     */
    public void unmarkTask(int taskNumber) throws NicoleException {
        this.crudChecker(taskNumber);
        System.out.println(TaskList.TASKS.get(taskNumber - 1).markUndone());
        this.storage.saveTasksToFile();
    }

    /**
     * Marks a task in the list.
     *
     * @throws NicoleException if the mark index is out of bounds of the list.
     */
    public void markTask(int taskNumber) throws NicoleException {
        this.crudChecker(taskNumber);
        System.out.println(TaskList.TASKS.get(taskNumber - 1).markDone());
        this.storage.saveTasksToFile();
    }

    /**
     * Deletes a task in the list.
     *
     * @throws NicoleException if the delete index is out of bounds of the list.
     */
    public void deleteTask(int taskNumber) throws NicoleException {
        this.crudChecker(taskNumber);
        TaskList.TASKS.remove(taskNumber - 1);
        System.out.println("Nicole: Phew...deleted  :>");
        this.storage.saveTasksToFile();
    }

    /**
     * Adds a task to the list.
     *
     * @throws NicoleException if there are write issues to tasks.txt
     */
    public void addTask(Task newTask) throws NicoleException {
        TaskList.TASKS.add(newTask);
        try {
            FileWriter taskFileWriter = new FileWriter("tasks.txt", true);
            taskFileWriter.write(newTask.toString() + "\n");
            taskFileWriter.close();
        } catch (IOException e) {
            throw new NicoleException("I couldn't save the task >< try again plss");
        }
        System.out.println("Nicole: Oki I added " + "\"" + newTask.toString().substring(7) + "\"");
    }

    /**
     * Searches for a task using a specific keyword.
     *
     * @param name the keyword.
     * @throws NicoleException if there are no matching tasks.
     */
    public void findTasks(String name) throws NicoleException {
        int numMatchingTasks = 0;
        System.out.println("Nicole: Let me see..");
        for (int i = 0; i < TaskList.TASKS.size(); i++) {
            if (TaskList.TASKS.get(i).contains(name)) {
                System.out.println((i + 1) + ". " + TASKS.get(i));
                numMatchingTasks += 1;
            }
        }

        if (numMatchingTasks == 0) {
            throw new NicoleException("Sorry I couldn't find any matching tasks");
        }
    }

    /**
     * Lists the present tasks. Sorts the list by task date if the user requests it
     * before printing the tasks.
     *
     */
    public void listTasks() {
        if (TaskList.TASKS.size() == 0) {
            System.out.println("Nicole: No tasks yet. Let's make some moves BD");
        } else {
            System.out.println("Nicole: Here's the tasks I saved so far,");
        }

        for (int i = 0; i < TaskList.TASKS.size(); i++) {
            System.out.println((i + 1) + ". " + TaskList.TASKS.get(i));
        }
    }

    /**
     * Sorts the list by task date if the user requests priority ordering.
     *
     */
    public void sortTasksByPriority() {
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
    }
}
