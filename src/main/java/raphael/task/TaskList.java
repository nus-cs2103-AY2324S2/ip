package raphael.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import raphael.exception.DuplicateTaskException;
import raphael.exception.RaphaelException;
import raphael.format.FileFormattable;
import raphael.format.Formatter;

/**
 * The task list.
 */
public class TaskList implements FileFormattable {
    private ArrayList<Task> tasks;

    /**
     * The overridden constructor of the task list.
     * @param tasks the tasks to be loaded.
     * @throws RaphaelException the exception exclusive for Raphael.
     */
    public TaskList(String tasks) throws RaphaelException {
        this.tasks = new ArrayList<>();
        final String[] tasksArr = tasks.split("\n");
        for (String task : tasksArr) {
            if (task.isEmpty() || task.equals("\n")) {
                continue;
            }
            final String[] taskArr = task.split(" \\|&\\| ");
            switch (taskArr[0]) {
            case "T":
                this.tasks.add(new Todo(taskArr[2], taskArr[1].equals("1")));
                break;
            case "D":
                this.tasks.add(new Deadline(taskArr[2], taskArr[3], taskArr[1].equals("1")));
                break;
            case "E":
                this.tasks.add(new Event(taskArr[2], taskArr[3],
                        taskArr[4], taskArr[1].equals("1")));
                break;
            default:
                throw new RaphaelException("Error in loading the tasks!");
            }
        }
    }

    /**
     * The overriden constructor that initialize the task list to the given task list.
     *
     * @param tasks the given task list.
     * @throws RaphaelException an exception exclusive to Raphael.
     */
    public TaskList(List<? extends Task> tasks) throws RaphaelException {
        this.tasks = new ArrayList<>();
        this.tasks.addAll(tasks);
    }
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns the number of task in the task list.
     *
     * @return the formatted string used to output the number of tasks in the current task list.
     */
    public String getSize() {
        return String.format("You now have %d tasks in your list!", this.tasks.size());
    }
    /**
     * Marks the task indicated by idx and returns 0 if the operation is successful; -1 otherwise.
     *
     * @param idx the index of the task that need to be mark as done.
     * @return 0 if the operation is successful, -1 otherwise.
     * @throws raphael.exception.RaphaelException exception exclusive to Raphael.
     */
    public String markTaskAsDone(int idx) throws RaphaelException {
        if (idx < 0 || idx >= this.tasks.size()) {
            throw new RaphaelException(RaphaelException.Type.INVALID_TASK_INDEX);
        } else {
            if (this.tasks.get(idx).markTaskAsDone() == 0) {
                return String.format("Hooray! Congrats on completing the following task!:\n"
                        + "\t%s\n", this.tasks.get(idx));
            } else {
                return String.format("Hmm. You seems to have completed the task:\n"
                        + "\t%s\n", this.tasks.get(idx));
            }
        }
    }
    /**
     * Unmarks the task indicated by idx and returns 0 if the operation is successful; -1 otherwise.
     *
     * @param idx the index of the task that need to be unmark as done.
     * @return 0 if the operation is successful, -1 otherwise.
     * @throws raphael.exception.RaphaelException exception exclusive to Raphael.
     */
    public String markTaskAsUndone(int idx) throws RaphaelException {
        if (idx < 0 || idx >= this.tasks.size()) {
            throw new RaphaelException(RaphaelException.Type.INVALID_TASK_INDEX);
        } else {
            if (this.tasks.get(idx).markTaskAsUndone() == 0) {
                return String.format("Uh oh! Workload + 1 by having the following task:\n"
                        + "\t%s\n", this.tasks.get(idx));
            } else {
                return String.format("Hmm. You seems to have not complete the task before:\n"
                        + "\t%s\n", this.tasks.get(idx));
            }
        }
    }

    /**
     * Adds the given task into the current task list.
     *
     * @param t the task that is needed tobe added.
     */

    public void addTask(Task t) throws RaphaelException {
        int prevSize = this.tasks.size();
        if (this.isDuplicate(t)) {
            throw new DuplicateTaskException();
        }
        String assertionErrorMessage = "The number of tasks should be increase by 1!";
        this.tasks.add(t);
        this.tasks = this.tasks.parallelStream().sorted().collect(
                Collectors.toCollection(ArrayList::new));
        assert this.tasks.size() == prevSize + 1 : assertionErrorMessage;
    }

    /**
     * Deletes the task indicated by idx from the task list. Upon successful deletion, the method will return 0; -1
     * otherwise.
     *
     * @param idx the index of task that has to be deleted.
     * @return 0 if the deletion is successful; -1 otherwise.
     * @throws raphael.exception.RaphaelException exception exclusive to Raphael.
     */
    public Task deleteTask(int idx) throws RaphaelException {
        if (idx < 0 || idx >= this.tasks.size()) {
            throw new RaphaelException(RaphaelException.Type.INVALID_TASK_INDEX);
        } else {
            int prevSize = this.tasks.size();
            String assertionErrorMessage = "The number of tasks should decrease by 1!";
            Task temp = this.tasks.get(idx);
            this.tasks.remove(idx);
            this.tasks = this.tasks.parallelStream().sorted().collect(
                    Collectors.toCollection(ArrayList::new));
            assert this.tasks.size() == prevSize - 1 : assertionErrorMessage;
            return temp;
        }
    }

    /**
     * Lists all the task in the current task list.
     */
    public String listTasks() {
        if (this.tasks.isEmpty()) {
            return "YAY! You have no tasks ongoing ^_^";
        } else {
            return String.format("Here are the tasks in your list:\n%s", this);
        }
    }

    /**
     * Returns the tasks that contain the keyword.
     *
     * @param keyword the keyword to search for.
     * @return all the matching tasks in a single string.
     */
    public String find(String keyword) {
        String res = this.tasks.stream()
                .filter(t -> t.isContaining(keyword))
                .map(Task::toString)
                .reduce("", (acc, t) -> String.format("%s\n\t%s", acc, t));
        if (res.isEmpty()) {
            return "There are no matching tasks.";
        } else {
            return String.format("Here are the matching tasks in your list:\t%s", res);
        }
    }
    private boolean isDuplicate(Task task) {
        return this.tasks.stream().anyMatch(t -> t.equals(task));
    }
    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < this.tasks.size(); ++i) {
            if (i == 0) {
                res = Formatter.addIndex(this.tasks.get(i), i + 1);
            } else {
                res = String.format("%s\n%s", res, Formatter.addIndex(this.tasks.get(i), i + 1));
            }
        }
        return res;
    }

    @Override
    public String toFileFormat() {
        return this.tasks.stream()
                .map(Task::toFileFormat)
                .reduce("", (acc, task) -> (
                        String.format("%s\n%s", acc, task)
                        ));
    }
}
