package numerator.task;

import java.util.ArrayList;
import java.util.stream.IntStream;

import numerator.exceptions.NumeratorException;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> taskList = new ArrayList<>();


    /**
     * Marks the task at the specified index as done.
     *
     * @param idx the index of the task to be marked as done.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public void markAsDone(int idx) throws IndexOutOfBoundsException {
        Task t = this.taskList.get(idx);
        t.markAsDone();

    }

    /**
     * Finds the tasks which contain the pattern in the description.
     *
     * @param pattern the pattern to search.
     * @return the String representation of the tasks.
     */
    public String findTasks(String pattern) {
        TaskList matchedTasks = new TaskList();
        for (Task t : this.taskList) {

            assert t != null;

            if (t.containsKeyword(pattern)) {
                matchedTasks.addTask(t);
            }
        }
        return matchedTasks.toString();
    }

    /**
     * Adds a task to the task list.
     *
     * @param t the task to be added.
     */
    public void addTask(Task t) {
        this.taskList.add(t);
    }


    /**
     * Marks the task at the specified index as not done.
     *
     * @param idx the index of the task to be marked as not done.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public void markAsUndone(int idx) throws IndexOutOfBoundsException {
        Task t = this.taskList.get(idx);
        t.markAsNotDone();

    }


    /**
     * Removes the task at the specified index from the task list.
     *
     * @param idx the index of the task to be removed.
     * @return the task that was removed.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public Task removeTask(int idx) throws IndexOutOfBoundsException {
        Task t = this.taskList.get(idx);

        assert t != null;

        this.taskList.remove(idx);
        return t;
    }

    /**
     * Adds a new ToDo task to the task list.
     *
     * @param taskDesc the description of the task.
     * @return the task that was added.
     */
    public Task addToDo(String taskDesc) {
        Task t = new ToDo(taskDesc);
        this.taskList.add(t);
        return t;
    }

    /**
     * Adds a new Event task to the task list.
     *
     * @param taskDesc the description of the task.
     * @param from     the start date and time of the event in the format of "yyyy/MM/dd HH:mm" or "yyyy/MM/dd".
     * @param to       the end date and time of the event in the format of "yyyy/MM/dd HH:mm" or "yyyy/MM/dd".
     * @return the task that was added.
     */
    public Task addEvent(String taskDesc, String from, String to) {
        Task t = new Event(taskDesc, from, to);
        this.taskList.add(t);
        return t;
    }

    /**
     * Adds a new Deadline task to the task list.
     *
     * @param taskDesc the description of the task.
     * @param by       the deadline of the task in the format of "yyyy/MM/dd HH:mm" or "yyyy/MM/dd".
     * @return the task that was added.
     */
    public Task addDeadline(String taskDesc, String by) {
        Task t = new Deadline(taskDesc, by);
        this.taskList.add(t);
        return t;
    }


    /**
     * Prints the task at the specified index.
     *
     * @param idx the index of the task to be printed.
     */
    public String getTaskAtIndex(int idx) {
        return this.taskList.get(idx).toString() + "\n";
    }


    /**
     * Returns a String representing the task that was added.
     *
     * @param task the task that was added.
     * @return the string representation of the task that was added.
     */
    public String getAddTaskString(Task task) {
        return "Added:\n"
                + task.toString() + "\nNow you have "
                + taskList.size() + " tasks in the list";
    }

    /**
     * Returns a String indicating that a task was removed.
     *
     * @param task the task that was removed.
     * @return a string indicating that a task was removed.
     */
    public String getDeleteTaskString(Task task) {
        return "Noted. I've removed this task:\n"
                + task.toString() + "\nNow you have "
                + taskList.size() + " tasks in the list";
    }

    /**
     * Returns a string of all the tasks in the task list.
     *
     * @return a string of all the tasks in the task list.
     */
    public String getSavedTasksString() {
        return String.join("\n", this.taskList
                .stream()
                .map(Task::getSaveString)
                .toArray(String[]::new));
    }


    @Override
    public String toString() {
        if (this.taskList.isEmpty()) {
            return "Congrats! Your list is empty. Go enjoy your day!";
        }

        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");

        IntStream.iterate(1, x -> x + 1)
                .limit(this.taskList.size())
                .forEachOrdered(i -> {
                    String s = String.format("%d. %s\n", i, this.taskList.get(i - 1));
                    sb.append(s);
                });
        return sb.toString().stripTrailing();

    }

    /**
     * Tags a task.
     *
     * @param taskNum the task number.
     * @param tag     the tag to be added.
     * @throws NumeratorException if the task number does not exist.
     */
    public void tagTask(int taskNum, String tag) throws NumeratorException {
        try {
            this.taskList.get(taskNum).addTag(tag);
        } catch (IndexOutOfBoundsException e) {
            throw new NumeratorException("Task number does not exist");
        }
    }

    /**
     * Untags a task.
     *
     * @param taskNum the task number.
     * @param tag     the tag to be removed.
     */
    public void untagTask(int taskNum, String tag) {
        try {
            this.taskList.get(taskNum).removeTag(tag);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Task number does not exist");
        }
    }
}
