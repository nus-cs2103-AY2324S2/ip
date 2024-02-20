package duke;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a list of tasks.
 * The tasks can be added, retrieved, marked as done, and deleted.
 */
public class MyList {
    private List<Task> tasks;

    /**
     * Constructs an empty list.
     */
    public MyList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a list with the saved list of tasks.
     *
     * @param t The list of tasks saved in the text file.
     */
    public MyList(List<Task> t) {
        this.tasks = t;
    }

    /**
     * Returns the list of tasks for saving.
     *
     * @return The list of tasks.
     */
    public List<Task> getTasksForSaving() {
        return this.tasks;
    }


    /**
     * Adds a task to the list.
     *
     * @param t The task to be added.
     * @return A message indicating the success of the operation.
     */
    public String addTask(Task t) {
        assert t != null : "Task should not be null";
        assert !t.getDescription().isEmpty() : "Task description should not be empty";
        this.tasks.add(t);
        return "Got it. I've added this task:\n" + t.toString() + "\nNow you have " + this.tasks.size()
                + " tasks in the list.";
    }

    /**
     * Retrieves a string representation of all the tasks in the list.
     *
     * @return A string representing all the tasks in the list.
     */
    public String getTasks() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Here are the tasks in your list:\n");
        int index = 1;

        for (Task task : this.tasks) {
            String taskstring = String.format("%d. %s\n", index, task.toString());
            stringBuilder.append(taskstring);
            index++;
        }

        return stringBuilder.toString();
    }

    /**
     * Marks a task as done based on its index in the list.
     *
     * @param index The index of the task to be marked as done.
     * @return A message indicating the success of the operation or if the index is out of bounds.
     */
    public String markTask(int index) throws DukeException {
        if (index < 1 || index > this.tasks.size()) {
            throw new DukeException("Number is outside length of list");
        } else {
            return "Nice! I've marked this task as done:\n" + this.tasks.get(index - 1).markAsDone();
        }
    }

    /**
     * Deletes a task based on its index in the list.
     *
     * @param index The index of the task to be deleted.
     * @return A message indicating the success of the operation or if the index is out of bounds.
     */
    public String delete(int index) throws DukeException {
        if (index < 1 || index > this.tasks.size()) {
            throw new DukeException("Number is outside length of list");
        } else {
            Task t = this.tasks.remove(index - 1);
            return "Noted. I've removed this task:\n" + t.toString() + "\nNow you have "
                    + this.tasks.size() + " tasks in the list.";
        }
    }

    /**
     * Finds and retrieves tasks containing a specified keyword.
     *
     * @param keyword The keyword to search for within task descriptions.
     * @return A string representation of tasks containing the specified keyword,
     *         or a message indicating that no matching tasks were found.
     * @throws DukeException If the provided keyword is empty.
     */
    public String findByKeyword(String keyword) throws DukeException {
        if (keyword.isEmpty()) {
            throw new DukeException("Keyword cannot be empty");
        }
        List<Task> matchList = this.tasks.stream()
                .filter(task -> task.matchKeyword(keyword))
                .collect(Collectors.toList());
        MyList listWithKeyword = new MyList(matchList);

        if (matchList.isEmpty()) {
            return "No tasks found containing keyword";
        } else {
            return listWithKeyword.getTasks();
        }
    }
}
