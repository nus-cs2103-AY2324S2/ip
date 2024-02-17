package nicole.taskstorage;

import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import nicole.nicoleexceptions.NicoleException;
import nicole.task.Event;
import nicole.task.Task;

public class TaskList {
    protected static final List<Task> TASKS = new ArrayList<>();
    private final Storage storage;

    /**
     * Initialises a TaskList and creates Storage for tasks.
     *
     */
    public TaskList() {
        this.storage = new Storage();
    }

    private void checkValidTaskNumber(int taskNumber) throws NicoleException {
        if (taskNumber <= 0 || taskNumber > TASKS.size()) {
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
        checkValidTaskNumber(taskNumber);
        String unmarkedMessage = TASKS.get(taskNumber - 1).markUndone();
        storage.saveTasksToFile();
        return unmarkedMessage;
    }

    /**
     * Marks a task in the list.
     *
     * @return a success response if the task is marked
     * @throws NicoleException if the mark index is out of bounds of the list or the task is already marked.
     */
    public String markTask(int taskNumber) throws NicoleException {
        checkValidTaskNumber(taskNumber);
        String markedMessage = TASKS.get(taskNumber - 1).markDone();
        storage.saveTasksToFile();
        return markedMessage;
    }

    /**
     * Deletes a task in the list.
     *
     * @return a success response if the task is deleted.
     * @throws NicoleException if the delete index is out of bounds of the list.
     */
    public String deleteTask(int taskNumber) throws NicoleException {
        checkValidTaskNumber(taskNumber);
        TASKS.remove(taskNumber - 1);
        storage.saveTasksToFile();
        return "Phew...deleted  :>";
    }

    /**
     * Adds a task to the list.
     *
     * @return a success response if the task is added.
     * @throws NicoleException if there are write issues to tasks.txt
     */
    public String addTask(Task newTask) throws NicoleException {
        checkClashingTasks(newTask);
        TASKS.add(newTask);
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
        for (int i = 0; i < TASKS.size(); i++) {
            // This method looks for a full match of the keyword not just partial
            if (TASKS.get(i).contains(name)) {
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
     * Updates a task's name in the list.
     *
     * @param newTaskName the new task name
     * @param taskNumber the index of the task to update
     * @return a success response if the task is deleted.
     * @throws NicoleException if the delete index is out of bounds of the list.
     */
    public String updateTask(String newTaskName, int taskNumber) throws NicoleException {
        checkValidTaskNumber(taskNumber);
        TASKS.get(taskNumber - 1).updateName(newTaskName);
        storage.saveTasksToFile();
        return "Okie I updated the name";
    }

    private void checkDuplicateTasks(Task newTask, String oldTaskName) throws NicoleException {
        boolean existsDuplicateTask = TASKS.stream().anyMatch(task -> task.equals(newTask));
        if (existsDuplicateTask) {
            newTask.updateName(oldTaskName);
            throw new NicoleException("Ouh sorry a task with that description already exists");
        }
    }

    private void checkClashingTasks(Task newTask) throws NicoleException {
        long numClashingTasks = TASKS.stream().filter(task -> {
            // The datetime clashes only apply to Events since Deadlines can be the same
            return task instanceof Event
                    // Case when two event intervals are exactly the same
                    && (newTask.getFromDateTime().isEqual(task.getFromDateTime())
                        && newTask.getToDateTime().isEqual(task.getToDateTime()))
                    // Case when the new event starts after but ends before an existing event
                    || (newTask.getFromDateTime().isAfter(task.getFromDateTime())
                        && newTask.getFromDateTime().isBefore(task.getToDateTime()))
                    // Case when the new event starts before and continues into an existing event
                    ||  (newTask.getFromDateTime().isBefore(task.getFromDateTime())
                        && newTask.getToDateTime().isAfter(task.getFromDateTime()))
                    // Case when the new event starts before an existing event ends but after it begins
                    ||  (newTask.getFromDateTime().isBefore(task.getToDateTime())
                        && newTask.getToDateTime().isAfter(task.getToDateTime()));
        }).count();
        if (numClashingTasks > 0) {
            throw new NicoleException("Careful! You already have an event at the same time");
        }
    }

    /**
     * Lists the present tasks. Sorts the list by task date if the user requests it
     * before printing the tasks.
     *
     * @return a filler message and the saved tasks or just a filler message if there are no tasks yet.
     */
    public String listTasks() {
        StringBuilder savedTasks = new StringBuilder();
        if (TASKS.size() == 0) {
            savedTasks.append("No tasks yet. Let's make some moves BD");
        } else {
            savedTasks.append("Here's the tasks I saved so far,").append("\n");
        }
        for (int i = 0; i < TASKS.size(); i++) {
            savedTasks.append(i + 1).append(". ").append(TASKS.get(i)).append("\n");
        }
        return savedTasks.toString();
    }

    /**
     * Sorts the list by task date if the user requests priority ordering.
     *
     * @return a success message.
     */
    public String sortTasksByDate() {
        // This sorter doesn't meaningfully sort Todos, whose Date is by default the maximum possible date
        Comparator<Task> dateSorter = (task1, task2) -> {
            if (task1.getDate().isBefore(task2.getDate())) {
                return -1;
            } else if (task1.getDate().isEqual(task2.getDate())) {
                return 0;
            } else {
                return 1;
            }
        };
        TASKS.sort(dateSorter);
        return "Alriiiiighty sorted by date :6";
    }
}
