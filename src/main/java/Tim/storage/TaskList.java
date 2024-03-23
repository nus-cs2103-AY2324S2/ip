package Tim.storage;

import Tim.exception.TimException;
import Tim.task.Deadline;
import Tim.task.Event;
import Tim.task.SpecialTask;
import Tim.task.Task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {
    public TaskList() {
        super();
    }

    /**
     * Join all the tasks as a String to be displayed.
     *
     * @return a String of all the tasks joined together
     */
    public String showALlTask() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < this.size(); i++) {
            int counter = i + 1;
            String output = counter + ". " + this.get(i).toString();
            s.append(output).append("\n");
        }
        return s.toString();
    }

    /**
     * Mark or unmark specified task
     *
     * @param index
     * @param toMark
     * @return Task that is marked or unmark
     */
    public Task mark(int index, boolean toMark) {
        index -= 1;
        Task task = this.get(index);
        if (toMark) {
            task.markTask();
        } else {
            task.unmarkTask();
        }
        return task;
    }

    /**
     * Delete specified task
     *
     * @param index
     * @return Task that is deleted
     */
    public Task delete(int index) {
        index -= 1;
        Task task = this.get(index);
        this.remove(index);
        return task;
    }

    /**
     * Check for tasks that occur within in specified date period
     * and save such tasks in a TaskList.
     * @param tasks
     * @param fromDate
     * @param toDate
     */
    public void checkInRange(TaskList tasks, LocalDate fromDate, LocalDate toDate) {

        for (Task task : this) {
            if (task instanceof Deadline || task instanceof Event) {
                SpecialTask specialTask = (SpecialTask) task;
                if (specialTask.inRange(fromDate, toDate)) {
                    tasks.add(specialTask);
                }
            }
        }
    }

    /**
     * Save Tasks in TaskList into specified file.
     * @param filePath
     * @throws TimException
     */
    public void saveTasks(Path filePath) throws TimException {
        try {
            Files.writeString(filePath, "");
            for (Task task : this) {
                Files.writeString(filePath, "\n" + task.toString(), StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            throw new TimException(e.getMessage());
        }

    }

    /**
     * Check if there is any task in the list that is a duplicate of the new task.
     * @param task
     * @return boolean value of whether there is duplicates
     */
    public boolean hasDuplicates(Task task) {
        for (Task t: this) {
            if (task.isDuplicate(t)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if task is present in the list.
     * @param index specified task index
     * @return boolean value of whether task is present
     */
    public boolean isPresent(int index) {
        return (index >= 0 && index < this.size());
    }

}
