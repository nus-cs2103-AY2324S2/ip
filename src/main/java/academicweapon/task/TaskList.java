package academicweapon.task;

import academicweapon.exceptions.DukeExceptions;
import academicweapon.ui.Ui;

import java.util.ArrayList;

/**
 * Represents a list of tasks in the Duke application.
 * The TaskList class provides methods to manipulate and display a list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Consturcts a TaskList based on the provided list of string representations of tasks
     *
     * @param strLst List of string representations of tasks
     */
    public TaskList(ArrayList<String> strLst) {
        ArrayList<Task> tasks = new ArrayList<>();
        for (int i = 0; i < strLst.size(); i++) {
            String line = strLst.get(i);
            String[] lineSplit = line.split("\\|");
            String action = lineSplit[0].trim();
            String description = lineSplit[2].trim();
            String isDone = lineSplit[1].trim();

            if (action.equals("T")) {
                Todo addToDoTask = new Todo(description);
                checkIfDone(addToDoTask, isDone);
                tasks.add(addToDoTask);
            } else if (action.equals("D")) {
                try {
                    Deadline addDeadlineTask = new Deadline(description, lineSplit[3].trim());
                    checkIfDone(addDeadlineTask, isDone);
                    tasks.add(addDeadlineTask);
                } catch (DukeExceptions e) {
                    System.out.println("Corrupted file.");
                }
            } else if (action.equals("E")) {
                String[] splitFromAndTo = lineSplit[3].trim().split("-");
                Event addEventTask = new Event(description, splitFromAndTo[0], splitFromAndTo[1]);
                checkIfDone(addEventTask, isDone);
                tasks.add(addEventTask);
            }
        }

        this.tasks = tasks;
    }

    /**
     * Checks if a task is done based on the input value and updates its status.
     *
     * @param t Task to check and update
     * @param val Value representing the task status
     */
    public static void checkIfDone(Task t, String val) {
        if (val.equals("0")) {
            t.markAsNotDone();
        } else {
            t.markAsDone();
        }
    }

    /**
     * Adds a task to the task list.
     *
     * @param task Task to be added to the list
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Displays the list of tasks in a formatted manner.
     * If the list is empty, it shows an appropriate message.
     */
    public String showList() {
        StringBuilder sb = new StringBuilder();
        try {
            DukeExceptions.checkListNotEmpty(this.tasks);
        } catch (DukeExceptions e) {
            System.out.println(e.getMessage());
            sb.append(e.getMessage());
        }

        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            System.out.println((i + 1) + "." + t.toString());
            sb.append((i + 1) + "." + t.toString() + "\n");
        }
        return sb.toString();
    }

    /**
     * Retrieves a task at the specified index in the task list.
     *
     * @param index Index of the task to retrieve
     * @return The task at the specified index
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return Number of tasks in the task list
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Removes a task at the specified index from the task list.
     *
     * @param index Index of the task to be removed
     * @return The removed task
     */
    public Task removeTask(int index) {
        Task toBeRemoved = this.tasks.get(index);
        this.tasks.remove(index);
        return toBeRemoved;
    }

    /**
     * Returns the list of tasks in the task list.
     *
     * @return ArrayList containing tasks in the task list
     */
    public ArrayList<Task> getList() {
        return this.tasks;
    }

    /**
     * Finds tasks containing a specified keyword in their description.
     *
     * @param keyword The keyword to search for in task descriptions
     * @return An ArrayList of strings representing tasks that contain the keyword
     */
    public ArrayList<String> findKeyword(String keyword) {
        ArrayList<String> lst = new ArrayList<>();
        for (Task task: this.tasks) {
            if (task.getDescription().contains(keyword)) {
                lst.add(task.toString());
            }
        }

        return lst;
    }
}
