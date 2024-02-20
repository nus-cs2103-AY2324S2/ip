package pan;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import pan.enums.TaskStatus;
import pan.exceptions.TaskIndexException;

/**
 * TaskList - Represents the TaskList Class that handles CRUD operations to the TaskList
 * @author Jerome Goh
 */
public class TaskList {
    private List<Task> tasks;
    private Storage storage;

    /**
     * Constructs a TaskList instance.
     *
     * @param storage Storage instance that would handle the writing of the state of taskList to a .txt file.
     */
    public TaskList(Storage storage) {
        this.storage = storage;
        this.tasks = storage.readList();
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
    public String add(Task instruction) {
        tasks.add(instruction);
        System.out.println();
        this.storage.save(tasks);

        assert tasks.size() >= 1;
        return "Got it. I've added this task:\n\t" + instruction.toString()
            + "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Updates the completion status of a given task within the Task List to completed.
     *
     * @param index Numeric Index of the Task's position within a Task List.
     * @throws TaskIndexException Occurs whenever the Index provided is out of bounds or do not exist.
     */
    public String mark(int index) throws TaskIndexException {
        if (index > tasks.size() || index < 0) {
            throw new TaskIndexException("You have entered an invalid index!");
        } else {
            Task task = tasks.get(index - 1);
            task.setIsDone(TaskStatus.COMPLETE);
            this.storage.save(tasks);

            assert task.getIsDone() == TaskStatus.COMPLETE;
            return "Nice! I've marked this task as done:\n\t" + task.toString();
        }
    }

    /**
     * Updates the completion status of a given task within the Task List to incomplete.
     *
     * @param index Numeric Index of the Task's position within a Task List.
     * @throws TaskIndexException Occurs whenever the Index provided is out of bounds or do not exist.
     */
    public String unmark(int index) throws TaskIndexException {
        if (index > tasks.size()) {
            throw new TaskIndexException("You have entered an invalid index!");
        } else {
            Task task = tasks.get(index - 1);
            task.setIsDone(TaskStatus.INCOMPLETE);

            assert task.getIsDone() == TaskStatus.INCOMPLETE;
            this.storage.save(tasks);
            return "OK, I've marked this task as not done yet:\n\t" + task.toString();
        }
    }

    /**
     * Deletes a given task within the Task List.
     *
     * @param index Numeric Index of the Task's position within a Task List.
     * @throws TaskIndexException Occurs whenever the Index provided is out of bounds or do not exist.
     */
    public String delete(int index)throws TaskIndexException {
        if (index > tasks.size()) {
            throw new TaskIndexException("You have entered an invalid index!");
        } else {
            Task task = tasks.get(index - 1);
            tasks.remove(index - 1);
            this.storage.save(tasks);
            return "Noted. I've removed this task:\n\t" + task.toString()
                + "Now you have " + tasks.size() + " tasks in the list.";
        }
    }

    /**
     * Prints out the tasks within a Task List to the user.
     */
    public String list(List<Task> tasks) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            stringBuilder.append("\n\t" + (i + 1) + "." + tasks.get(i).toString());
        }
        return stringBuilder.toString();
    }

    /**
     * Finds and prints out the tasks that matches the user's search keyword.
     *
     * @param searchKeyword String representation of the keyword that has been entered by the user.
     */
    public String find(String searchKeyword) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().contains(searchKeyword)) {
                stringBuilder.append("\t" + (i + 1) + "." + tasks.get(i).toString());
            }
        }
        return stringBuilder.toString();
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

    /**
     * Sorts the list in accordance to lexicographical order for each task's description.
     *
     * @return String representation of a sorted task list.
     */
    public String sort() {
        List<Task> unsortedList = this.tasks;
        unsortedList.sort((x, y) -> x.getDescription().compareTo(y.getDescription()));
        return this.list(unsortedList);
    }
}
