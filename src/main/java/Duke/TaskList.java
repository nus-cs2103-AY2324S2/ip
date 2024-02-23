package Duke;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    public TaskList checkInRange(TaskList tasks, LocalDate fromDate, LocalDate toDate) {

        for (Task task : this) {
            if (task instanceof Deadline || task instanceof Event) {
                SpecialTask specialTask = (SpecialTask) task;
                if (specialTask.inRange(fromDate, toDate)) {
                    tasks.add(specialTask);
                }
            }
        }
        return tasks;
    }

}
