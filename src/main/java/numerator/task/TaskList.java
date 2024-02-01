package numerator.task;

import numerator.Ui;

import java.util.ArrayList;
import java.util.stream.IntStream;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> taskList = new ArrayList<>();


    /**
     * Marks the task at the specified index as done
     *
     * @param idx the index of the task to be marked as done
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public void markAsDone(int idx) throws IndexOutOfBoundsException {
        Task t = this.taskList.get(idx);
        t.markAsDone();

    }

    /**
     * Finds the tasks which contain the pattern in the description
     * @param pattern the pattern to search
     * @return the String representation of the tasks
     */
    public String findTasks(String pattern) {
        TaskList matchedTasks = new TaskList();
        for (Task t : this.taskList) {
            if (t.containsKeyword(pattern)) {
                matchedTasks.addTask(t);
            }
        }
        return matchedTasks.toString();
    }

    // used only for findTasks
    private void addTask(Task t) {
        this.taskList.add(t);
    }

    /**
     * Marks the last task in the list as done
     */
    public void markLastAsDone() {
        if (this.taskList.isEmpty()) {
            return;
        }
        Task t = this.taskList.get(this.taskList.size() - 1);
        t.markAsDone();
    }

    /**
     * Marks the task at the specified index as not done
     *
     * @param idx the index of the task to be marked as not done
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public void markAsUndone(int idx) throws IndexOutOfBoundsException {
        Task t = this.taskList.get(idx);
        t.markAsNotDone();

    }

    /**
     * Removes the task at the specified index from the task list
     *
     * @param idx the index of the task to be removed
     * @return the task that was removed
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public Task removeTask(int idx) throws IndexOutOfBoundsException {
        Task t = this.taskList.get(idx);
        this.taskList.remove(idx);
        return t;
    }

    /**
     * Adds a new ToDo task to the task list
     *
     * @param taskDesc the description of the task
     * @return the task that was added
     */
    public Task addToDo(String taskDesc) {
        Task t = new ToDo(taskDesc);
        this.taskList.add(t);
        return t;
    }

    /**
     * Adds a new Event task to the task list
     *
     * @param taskDesc the description of the task
     * @param from     the start date and time of the event in the format of "yyyy/MM/dd HH:mm" or "yyyy/MM/dd"
     * @param to       the end date and time of the event in the format of "yyyy/MM/dd HH:mm" or "yyyy/MM/dd"
     * @return the task that was added
     */
    public Task addEvent(String taskDesc, String from, String to) {
        Task t = new Event(taskDesc, from, to);
        this.taskList.add(t);
        return t;
    }

    /**
     * Adds a new Deadline task to the task list
     *
     * @param taskDesc the description of the task
     * @param by       the deadline of the task in the format of "yyyy/MM/dd HH:mm" or "yyyy/MM/dd"
     * @return the task that was added
     */
    public Task addDeadline(String taskDesc, String by) {
        Task t = new Deadline(taskDesc, by);
        this.taskList.add(t);
        return t;
    }


    /**
     * Prints the task at the specified index
     *
     * @param idx the index of the task to be printed
     */
    public void printTask(int idx) {
        String s = this.taskList.get(idx).toString() + "\n";
        Ui.printMessage(s);
    }

    /**
     * Prints the task that was added
     *
     * @param task the task that was added
     */
    public void printAddTask(Task task) {
        Ui.printMessage("Got it, I've added this task:");
        Ui.printMessage(task.toString() + "\n");
        String s = String.format("Now you have %d tasks in the list\n", taskList.size());
        Ui.printMessage(s);


    }

    /**
     * Returns a string of all the tasks in the task list
     *
     * @return a string of all the tasks in the task list
     */
    public String getSavedTasksString() {
        return String.join("\n", this.taskList
                .stream()
                .map(Task::getSaveString)
                .toArray(String[]::new));
    }

    /**
     * Returns the size of the task list as a string
     *
     * @return the size of the task list as a string
     */
    public String getSizeAsString() {
        return String.valueOf(this.taskList.size());
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
}
