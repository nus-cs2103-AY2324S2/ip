package pan;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import pan.enums.TaskStatus;
import pan.exceptions.TaskIndexException;

class TaskList {
    private List<Task> tasks;
    private Storage storage;

    /**
     * Constructs a TaskList instance.
     *
     * @param storage Storage instance that would handle the writing of the state of taskList to a .txt file.
     */
    public TaskList(Storage storage) {
        this.tasks = new ArrayList<Task>();
        this.storage = storage;
    }

    /**
     * Gets the tasks attribute of the TaskList instance.
     *
     * @return List containing Task instances that have beena added by the user.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds a new task to the Task List.
     *
     * @param instruction New Task Instance that the user wants to add to the Task List.
     */
    public void add(Task instruction) {
        tasks.add(instruction);
        System.out.println("Got it. I've added this task:\n\t" + instruction.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        this.storage.save(tasks);
    }

    /**
     * Updates the completion status of a given task within the Task List to completed.
     *
     * @param index Numeric Index of the Task's position within a Task List.
     * @throws TaskIndexException Occurs whenever the Index provided is out of bounds or do not exist.
     */
    public void mark(int index) throws TaskIndexException {
        if (index > tasks.size() || index < 0) {
            throw new TaskIndexException("You have entered an invalid index!");
        } else {
            Task task = tasks.get(index - 1);
            task.setIsDone(TaskStatus.COMPLETE);
            System.out.println("Nice! I've marked this task as done:\n\t" + task.toString());
            this.storage.save(tasks);
        }
    }

    /**
     * Updates the completion status of a given task within the Task List to incomplete.
     *
     * @param index Numeric Index of the Task's position within a Task List.
     * @throws TaskIndexException Occurs whenever the Index provided is out of bounds or do not exist.
     */
    public void unmark(int index) throws TaskIndexException {
        if (index > tasks.size()) {
            throw new TaskIndexException("You have entered an invalid index!");
        } else {
            Task task = tasks.get(index - 1);
            task.setIsDone(TaskStatus.INCOMPLETE);
            System.out.println("OK, I've marked this task as not done yet:\n\t" + task.toString());
            this.storage.save(tasks);
        }
    }

    /**
     * Deletes a given task within the Task List.
     *
     * @param index Numeric Index of the Task's position within a Task List.
     * @throws TaskIndexException Occurs whenever the Index provided is out of bounds or do not exist.
     */
    public void delete(int index)throws TaskIndexException {
        if (index > tasks.size()) {
            throw new TaskIndexException("You have entered an invalid index!");
        } else {
            Task task = tasks.get(index - 1);
            tasks.remove(index - 1);
            System.out.println("Noted. I've removed this task:");
            System.out.println("\t" + task);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            this.storage.save(tasks);
        }
    }

    /**
     * Prints out the tasks within a Task List to the user.
     */
    public void list() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t" + (i + 1) + "." + tasks.get(i).toString());
        }
    }

    /**
     * Converts the date into a Java DateTime instance.
     *
     * @param dateStr String representation of a date that has been keyed in by the user.
     * @return LocalDate instance representing the Date, Month and Year that the user has entered.
     */
    public LocalDate convertDate(String dateStr) {
        // assuming that byDate is in yyyy-mm-dd
        String[] dateArr = dateStr.split("-");
        return LocalDate.of(Integer.parseInt(dateArr[0]), Integer.parseInt(dateArr[1]), Integer.parseInt(dateArr[2]));
    }
}
