package chatbot.task;

import chatbot.exception.DukeException;
import chatbot.storage.Storage;
import chatbot.ui.Ui;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Encapsulates the data and behaviour of a task list.
 *
 * @author Huang Zhuoyan, Celeste
 * @version CS2103T AY24/25 Semester 1, G07
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a new TaskList.
     * Initialises a new TaskList with an empty list of tasks.
     */
    public TaskList() {
        ArrayList<Task> newTaskList = new ArrayList<>();
        this.tasks = newTaskList;
    }

    /**
     * Constructs a TaskList with the specified list of tasks.
     *
     * @param tasks The list of tasks to initialise the TaskList with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int getNumOfTasks() {
        return tasks.size();
    }

    /**
     * Prints the task description of the specified task.
     *
     * @param taskNum The index of the task to retrieve.
     * @return The task description of the specified task.
     */
    public String printTask(int taskNum) {
        return tasks.get(taskNum - 1).printTask();
    }

    /**
     * Marks the specified task as completed.
     *
     * @param taskNum The index of the task to be marked.
     * @throws DukeException For an invalid task index.
     */
    public void markTask(int taskNum) throws DukeException {
        if (taskNum == 0) {
            String exceptionMessage = Ui.createLine() + "\n"
                    + "task 0? how can i mark a task that doesn't exist?!\n"
                    + Ui.createLine();
            throw new DukeException(exceptionMessage);
        } else if (taskNum > tasks.size()) {
            String exceptionMessage = Ui.createLine() + "\n"
                    + "hahaha! you only have " + tasks.size() + " tasks in your task list!!\n"
                    + "there's no task " + taskNum + "!\n"
                    + Ui.createLine();
            throw new DukeException(exceptionMessage);
        }
        tasks.get(taskNum - 1).complete();
        Storage.saveData(tasks);
        Ui.printMarkedTask(this, taskNum);
    }

    /**
     * Unmarks the specified task.
     *
     * @param taskNum The index of the task to be unmarked.
     * @throws DukeException For an invalid task index.
     */
    public void unmarkTask(int taskNum) throws DukeException {
        if (taskNum == 0) {
            String exceptionMessage = Ui.createLine() + "\n"
                    + "task 0? how can i unmark a task that doesn't exist?!\n"
                    + Ui.createLine();
            throw new DukeException(exceptionMessage);
        } else if (taskNum > tasks.size()) {
            String exceptionMessage = Ui.createLine() + "\n"
                    + "hahaha! you only have " + tasks.size() + " tasks in your task list!!\n"
                    + "there's no task " + taskNum + "!\n"
                    + Ui.createLine();
            throw new DukeException(exceptionMessage);
        }
        tasks.get(taskNum - 1).unmark();
        Storage.saveData(tasks);
        Ui.printUnmarkedTask(this, taskNum);
    }

    /**
     * Deletes the specified task.
     *
     * @param taskNum The index of the task to be deleted.
     * @throws DukeException For an invalid task index.
     */
    public void deleteTask(int taskNum) throws DukeException {
        if (taskNum == 0) {
            String exceptionMessage = Ui.createLine() + "\n"
                    + "error: there's no such thing as task 0!\n"
                    + Ui.createLine();
            throw new DukeException(exceptionMessage);
        } else if (taskNum > tasks.size()) {
            String exceptionMessage = Ui.createLine() + "\n"
                    + "error! you only have " + tasks.size() + " tasks in your task list!!\n"
                    + "there's no task " + taskNum + "!\n"
                    + Ui.createLine();
            throw new DukeException(exceptionMessage);
        }
        Task deletedTask = tasks.get(taskNum - 1);
        String deletedTaskMessage = deletedTask.printTask();
        tasks.remove(taskNum - 1);
        int remainingNumOfTasks = tasks.size();
        Storage.saveData(tasks);
        Ui.printDeletedTask(deletedTaskMessage, remainingNumOfTasks);
    }

    /**
     * Adds a new ToDo task with the specified task description.
     *
     * @param name The task description of the ToDo task.
     */
    public void addTodoTask(String name) {
        ToDo addedTask = new ToDo(name);
        tasks.add(addedTask);
        int totalNumOfTasks = tasks.size();
        Ui.printAddedTask(addedTask.printTask(), totalNumOfTasks);
        Storage.saveData(tasks);
    }

    /**
     * Adds a new Deadline task with the specified task description and deadline.
     *
     * @param name The task description of the Deadline task.
     * @param deadline The LocalDateTime object containing the deadline of the task.
     */
    public void addDeadlineTask(String name, LocalDateTime deadline) {
        Deadline addedTask = new Deadline(name, deadline);
        tasks.add(addedTask);
        int totalNumOfTasks = tasks.size();
        Ui.printAddedTask(addedTask.printTask(), totalNumOfTasks);
        Storage.saveData(tasks);
    }

    /**
     * Adds a new Event task with the specified task description, start and end dates.
     *
     * @param name The task description of the Event task.
     * @param start The LocalDateTime object containing the start date of the task.
     * @param end The LocalDateTime object containing the end date of the task.
     */
    public void addEventTask(String name, LocalDateTime start, LocalDateTime end) {
        Event addedTask = new Event(name, start, end);
        tasks.add(addedTask);
        int totalNumOfTasks = tasks.size();
        Ui.printAddedTask(addedTask.printTask(), totalNumOfTasks);
        Storage.saveData(tasks);
    }

    /**
     * Finds tasks with descriptions containing the specified keyword.
     *
     * @param keyword The keyword to be searched for.
     */
    public void findTask(String keyword) {
        ArrayList<Task> tasksWithKeyword = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).hasKeyword(keyword)) {
                tasksWithKeyword.add(tasks.get(i));
            }
        }
        TaskList filteredTasklist = new TaskList(tasksWithKeyword);
        Ui.printFindTask(filteredTasklist, keyword);
    }
}
