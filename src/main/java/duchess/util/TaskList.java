package duchess.util;

import duchess.tasks.Task;

import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> taskList;
    private static final String LINE_BREAK = "\n------------------------------------------";

    /**
     * Create new empty TaskList object.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Create new Task List object with previous tasks already loaded.
     *
     * @param taskList ArrayList of Task objects.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Print list of tasks.
     */
    public String printTaskList() {
        String response = "";
        response += LINE_BREAK;
        response += "\nHere are the tasks in your list:";
        for (int i = 1; i <= taskList.size(); i++) {
            Task t = taskList.get(i - 1);
            response += "\n" + i + "." + t;
        }
        response += LINE_BREAK;
        return response;
    }

    /**
     * Retrieve ArrayList of Task objects.
     *
     * @return ArrayList of Task objects.
     */
    public ArrayList<Task> retrieveTaskList() {
        return this.taskList;
    }

    /**
     * Delete Task object in ArrayList of Task objects.
     *
     * @param id id of task in list to be deleted.
     */
    public String deleteTask(int id) throws IndexOutOfBoundsException {
        String response = "";
        Task t = taskList.remove(id - 1);
        response += LINE_BREAK;
        response += "\nNoted. I've removed this task:";
        response += "\n " + t;
        response += "\nNow you have " + taskList.size() + " tasks in the list.";
        response += LINE_BREAK;
        return response;
    }

    /**
     * Create Task object in ArrayList of Task objects.
     *
     * @param task new Task object.
     */
    public String createTask(Task task) {
        String response = "";

        for (int i = 1; i <= taskList.size(); i++) {
            if (task.getDescription().equals(taskList.get(i - 1).getDescription())) {
                response += LINE_BREAK;
                response += "\nTask with the same description already exists.";
                response += LINE_BREAK;
                return response;
            }
        }

        taskList.add(task);

        response += LINE_BREAK;
        response += "\nGot it. I've added this task: \n  " + task;
        response += "\nNow you have " + taskList.size() + " tasks in the list.";
        response += LINE_BREAK;
        return response;
    }

    /**
     * Unmark Task object in ArrayList of Task objects.
     *
     * @param id id of task in list to be unmarked.
     */
    public String unmarkTask(int id) {
        Task t = taskList.get(id - 1);
        t.markAsUndone();
        String response = "";
        response += LINE_BREAK;
        response += "\nOK, I've marked this task as not done yet:";
        response += "\n " + t;
        response += LINE_BREAK;
        return response;
    }

    /**
     * Mark Task object in ArrayList of Task objects.
     *
     * @param id id of task in list to be marked.
     */
    public String markTask(int id) {
        Task t = taskList.get(id - 1);
        t.markAsDone();
        String response = "";
        response += LINE_BREAK;
        response += "\nNice! I've marked this task as done:";
        response += "\n " + t;
        response += LINE_BREAK;
        return response;
    }

    /**
     * Print Task objects containing keyword.
     *
     * @param keyword keyword to look for in Task objects
     */
    public String findTasks(String keyword) {
        String response = "";
        response += LINE_BREAK;
        response += "\nHere are the matching tasks in your list: ";
        int counter = 1;
        for (int i = 0; i < this.taskList.size(); i++) {
            Task t = taskList.get(i);
            if (t.getDescription().toUpperCase().contains(keyword.toUpperCase())) {
                response += "\n" + counter + "." + t;
                counter += 1;
            }
        }
        response += LINE_BREAK;
        return response;
    }

    /**
     * Get current number of Task objects in ArrayList.
     *
     * @return int number of Task objects.
     */
    public int getNumberOfTasks() {
        return taskList.size();
    }
}
