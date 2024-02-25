package thames;

import java.util.ArrayList;
import java.util.Scanner;

import thames.task.ToDo;
import thames.task.Deadline;
import thames.task.Event;
import thames.task.Task;

/**
 * Array list of tasks.
 */
public class TaskList {
    ArrayList<Task> taskList;
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Initializes the saved task list.
     *
     * @param sc Scanner file containing contents of saved task list.
     */
    public TaskList(Scanner sc) {
        this.taskList = new ArrayList<>();
        while (sc.hasNextLine()) {
            String task = sc.nextLine();
            String[] split = task.split(",");
            String taskType = split[0];
            Boolean isDone = split[1].equals("X");
            String desc = split[2];
            if (taskType.equals("T")) {
                ToDo todo = new ToDo(desc);
                this.taskList.add(todo);
                if (isDone) {
                    todo.mark();
                }
            } else if (taskType.equals("D")) {
                String by = split[3];

                Deadline deadline = new Deadline(desc, by);
                this.taskList.add(deadline);
                if (isDone) {
                    deadline.mark();
                }
            } else {
                String from = split[3];
                String to = split[4];
                Event event = new Event(desc, from, to);
                this.taskList.add(event);
                if (isDone) {
                    event.mark();
                }
            }

        }
    }

    /**
     * Adds task to task list.
     *
     * @param task Task to be added.
     */
    public void add(Task task) {
        this.taskList.add(task);
    }

    /**
     * Removes task from task list.
     *
     * @param index Index of task in the task list.
     * @return Task that was removed.
     */
    public Task remove(int index) {
        return this.taskList.remove(index);
    }

    /**
     * Gets task in task list.
     * @param index Index of task in the task list.
     * @return Task at index.
     */
    public Task get(int index) {
        return this.taskList.get(index);
    }

    /**
     * Returns size of task list.
     *
     * @return Task list size.
     */
    public int size() {
        return this.taskList.size();
    }

    @Override
    public String toString() {
        return this.taskList.toString();
    }
}
