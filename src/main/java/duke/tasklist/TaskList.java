package duke.tasklist;

import java.time.LocalDate;
import java.util.ArrayList;

import duke.dukeexception.DukeException;
import duke.task.Task;
import duke.task.deadlines.Deadlines;
import duke.task.events.Events;
import duke.task.todos.ToDos;
import duke.ui.Ui;

/**
 * This class contains the list of the tasks.
 * There are also functions to edit the tasks.
 * @author Tang Hao Liang
 */
public class TaskList {
    private static ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Constructor that adds the loaded tasks from the file.
     *
     * @param tasks List of saved tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        taskList = tasks;
    }

    /**
     * Marks the specified task to be done.
     *
     * @param num Task's specified by user.
     * @return Display text for mark.
     * @throws DukeException If number is not on the list.
     */
    public String mark(int num) throws DukeException {
        try {
            Task task = taskList.get(num);
            task.markAsDone();
            return Ui.printMark(task.toString());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please enter a number for the task that is on the list.\n");
        }
    }

    /**
     * Unmarks the specified task to be undone.
     *
     * @param num Task's specified by user.
     * @return Display text for unmark.
     * @throws DukeException f number is not on the list.
     */
    public String unmark(int num) throws DukeException {
        try {
            Task task = taskList.get(num);
            task.markAsUndone();
            return Ui.printUnmark(task.toString());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please enter a number for the task that is on the list.\n");
        }
    }

    /**
     * Adds todos to the list.
     *
     * @param description Todos' Description.
     * @return Display text for add todos.
     */
    public String toDo(String description) {
        Task task = new ToDos(description);
        taskList.add(task);
        return Ui.printAdd(task.toString(), taskList.size());
    }

    /**
     * Adds deadlines to the list.
     *
     * @param description Deadline's Description.
     * @param date Task's Deadline.
     * @return Display text for add deadlines.
     */
    public String deadline(String description, LocalDate date) {
        Task task = new Deadlines(description, date);
        taskList.add(task);
        return Ui.printAdd(task.toString(), taskList.size());
    }

    /**
     * Adds events to the list.
     *
     * @param description Event's Description.
     * @param from Event's Start.
     * @param to Event's End.
     * @return Display text for add events.
     */
    public String event(String description, String from, String to) {
        Task task = new Events(description, from, to);
        taskList.add(task);
        return Ui.printAdd(task.toString(), taskList.size());
    }

    /**
     * Deletes specified task from the list.
     *
     * @param num Task's specified by user.
     * @return Display text for delete.
     * @throws DukeException If number is not on the list.
     */
    public String delete(int num) throws DukeException {
        try {
            Task task = taskList.get(num);
            taskList.remove(num);
            return Ui.printDelete(task.toString(), taskList.size());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please enter a number for the task that is on the list.\n");
        }
    }

    /**
     * Returns list of tasks that contains input.
     *
     * @param input User's input to find similar tasks.
     * @return List of tasks that contains input.
     */
    public ArrayList<Task> findContains(String input) {
        ArrayList<Task> contains = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).contains(input)) {
                contains.add(taskList.get(i));
            }
        }
        return contains;
    }

    public Task getTask(int num) {
        return taskList.get(num);
    }

    public ArrayList<Task> getList() {
        return taskList;
    }


    public int getNumberOfTasks() {
        return taskList.size();
    }
}
