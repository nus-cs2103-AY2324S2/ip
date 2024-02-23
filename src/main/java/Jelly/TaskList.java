package jelly;

import java.util.ArrayList;

/**
 * A list of tasks
 */
public class TaskList {

    private static String taskAdded = "Got it. I've added this task:";
    private ArrayList<Task> tasks;

    /**
     * Default constructor of TaskList, creates empty TaskList.
     */
    public TaskList() {

        tasks = new ArrayList<Task>();
    }

    /**
     * Copy constructor of TaskList, SHALLOW copies contents of taskList
     *
     * @param taskList TaskList to copy from
     */
    public TaskList(TaskList taskList) { //read from file

        this.tasks = taskList.tasks;
    }

    /**
     * @param i index of element to get
     * @return returns the task at index i
     */
    public Task get(Integer i) {

        return tasks.get(i);
    }

    /**
     * @return current number of tasks in the TaskList
     */
    public Integer size() {

        return tasks.size();
    }

    /**
     * Prints message containing number of tasks in the TaskList
     */
    public String listSize() {

        return "Now you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * @param name     name of deadline
     * @param deadline date/time of deadline
     * @param isDone   whether task is done
     */
    public String addDeadline(String name, String deadline, boolean isDone) {

        Task task = new Deadline(name, deadline, isDone);
        tasks.add(task);
        return taskAdded + "\n" + task + "\n" + listSize();
    }

    /**
     * @param name   name of todo
     * @param isDone whether task is done
     */
    public String addTodo(String name, boolean isDone) {

        Task task = new Todo(name, isDone);
        tasks.add(task);
        return taskAdded + "\n" + task + "\n" + listSize();
    }

    /**
     * @param name   name of event
     * @param start  start date/time of event
     * @param end    end date/time of event
     * @param isDone whether task is done
     */
    public String addEvent(String name, String start, String end, boolean isDone) {

        Task task = new Event(name, start, end, isDone);
        tasks.add(task);
        return taskAdded + "\n" + task + "\n" + listSize();
    }

    /**
     * @param task Task to add to TaskList
     */
    public void add(Task task) {

        tasks.add(task);
    }

    /**
     * @param index index of task to delete
     */
    public String deleteTask(Integer index) {

        if (index <= 0 || index > tasks.size()) {

            return "Please input a valid index!";
        }
        Task task = tasks.get(index - 1);
        tasks.remove(index - 1);
        return "Noted, I've removed this task:\n" + task + "\n" + listSize();
    }

    /**
     * @param index index of task to mark as done
     */
    public String markTask(Integer index) {

        if (index <= 0 || index > tasks.size()) {

            return "Please input a valid index!";
        }
        Task task = tasks.get(index - 1);
        task.mark();
        assert(task.isDone());
        return "Nice, I've marked this task as done:\n" + task;
    }

    /**
     * @param index index of task to mark as not done
     */
    public String unmarkTask(Integer index) {

        if (index <= 0 || index > tasks.size()) {

            return "Please input a valid index!";
        }
        Task task = tasks.get(index - 1);
        task.unmark();
        assert(!task.isDone());
        return "OK, I've marked this task as not done yet:\n" + task;
    }

    /**
     * @param keyword keyword to be searched in the TaskList
     * @return An array of Task entries whose name contains keyword.
     */
    public TaskList find(String keyword) {

        TaskList result = new TaskList();

        for (int i = 0; i < tasks.size(); ++i) {

            if (tasks.get(i).getName().contains(keyword)) {

                result.add(tasks.get(i));
            }
        }

        return result;
    }

    @Override
    public String toString() {

        if(tasks.size() == 0) {

            return "There isn't anything in the task list yet!";
        }

        String string = "";

        for (Integer i = 1; i <= tasks.size(); ++i) {

            string += i + ". " + tasks.get(i - 1);

            if (i != tasks.size()) {
                string += "\n";
            }
        }

        return string;
    }
}
