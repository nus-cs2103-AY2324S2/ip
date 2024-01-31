package Duke.TaskList;

import Duke.DukeException.DukeException;
import Duke.Task.Deadlines.Deadlines;
import Duke.Task.Events.Events;
import Duke.Task.Task;
import Duke.Task.ToDos.ToDos;
import Duke.Ui.Ui;
import java.time.LocalDate;
import java.util.ArrayList;

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
     * @throws DukeException If number is not on the list.
     */
    public void mark(int num) throws DukeException {
        try {
            Task task = taskList.get(num);
            task.markAsDone();
            Ui.printMark(task.toString());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please enter a number for the task that is on the list.\n");
        }
    }

    /**
     * Marks the specified task to be undone.
     *
     * @param num Task's specified by user.
     * @throws DukeException f number is not on the list.
     */
    public void unmark(int num) throws DukeException {
        try {
            Task task = taskList.get(num);
            task.markAsUndone();
            Ui.printUnmark(task.toString());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please enter a number for the task that is on the list.\n");
        }
    }

<<<<<<< HEAD
    /**
     * Adds todos to the list.
     *
     * @param description Todos' Description.
     */
=======
>>>>>>> branch-A-CodingStandard
    public void toDo(String description) {
        Task task = new ToDos(description);
        taskList.add(task);
        Ui.printAdd(task.toString(), taskList.size());
    }

    /**
     * Adds deadlines to the list.
     *
     * @param description Deadline's Description.
     * @param date Task's Deadline.
     */
    public void deadline(String description, LocalDate date) {
        Task task = new Deadlines(description, date);
        taskList.add(task);
        Ui.printAdd(task.toString(), taskList.size());
    }

    /**
     * Adds events to the list.
     *
     * @param description Event's Description.
     * @param from Event's Start.
     * @param to Event's End.
     */
    public void event(String description, String from, String to) {
        Task task = new Events(description, from, to);
        taskList.add(task);
        Ui.printAdd(task.toString(), taskList.size());
    }

    /**
     * Deletes specified task from the list.
     *
     * @param num Task's specified by user.
     * @throws DukeException If number is not on the list.
     */
    public void delete(int num) throws DukeException {
        try {
            Task task = taskList.get(num);
            taskList.remove(num);
            Ui.printDelete(task.toString(), taskList.size());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please enter a number for the task that is on the list.\n");
        }
    }

    public Task getTask(int num) {
        return taskList.get(num);
    }

    public int getSize() {
        return taskList.size();
    }

    public ArrayList<Task> getList() {
        return taskList;
    }
}
