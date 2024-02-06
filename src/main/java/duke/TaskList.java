package duke;

import java.util.ArrayList;

/**
 * TaskList Class is responsible for housing the taskList array and methods for adding and removing tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();
    private ArrayList<Task> findList = new ArrayList<>();

    /**
     * Empty constructor for TaskList.
     */
    public TaskList() {
    }

    /**
     * Marks a mask as complete.
     * @param index
     */
    public String markTask(int index) {
        Task currentTask = taskList.get(index);
        String reply = "";
        reply += "We have completed this task!\n";
        currentTask.mark();
        reply += currentTask.getTaskType() + " " + currentTask.getStatus()
                + " " + currentTask.getTask();

        return reply;
    }

    /**
     * Deletes a task from the TaskList.
     * @param index
     */
    public String deleteTask(int index) {
        Task currentTask = taskList.get(index);
        String reply = "";

        reply += "Task has been deleted!\n";
        reply += currentTask.getTaskType() + " " + currentTask.getStatus()
                + " " + currentTask.getTask() + "\n";

        taskList.remove(index);
        return reply;
    }

    /**
     * Unmarks a task as completed.
     * @param index
     */
    public String unmarkTask(int index) {
        Task currentTask = taskList.get(index);
        String reply = "";
        reply += "Oops, task unmarked!\n";
        currentTask.unmark();
        reply += currentTask.getTaskType() + " " + currentTask.getStatus()
                + " " + currentTask.getTask() + "\n";

        return reply;
    }

    /**
     * Lists all the tasks currently created.
     */
    public String listTask() {
        String reply = "";
        reply += "These are the tasks we currently have: \n";

        for (int i = 0; i < taskList.size(); i++) {
            Task currentTask = taskList.get(i);
            reply += (i + 1) + ". " + currentTask.toString() + "\n";
        }

        reply += "We have " + (taskList.size()) + " tasks.\n";

        return reply;
    }

    /**
     * Adds new task to the TaskListt.
     * @param first
     * @param second
     */

    public String addTask(String first, String second) {
        Task newTask;
        String reply = "";

        if (first.equals("todo")) {
            newTask = new ToDo(second, "T");
            this.taskList.add(newTask);
            reply += newTask.announcement() + "\n";
            reply += newTask + "\n";
            return reply;
        } else if (first.equals("deadline")) {
            String[] secondaryInputSplit = second.split(" /");
            newTask = new Deadline(secondaryInputSplit[0], "D", secondaryInputSplit[1]);
            this.taskList.add(newTask);
            reply += newTask.announcement() + "\n";
            reply += newTask + "\n";
            return reply;
        } else if (first.equals("event")) {
            String[] secondaryInputSplit = second.split(" /");
            newTask = new Event(secondaryInputSplit[0], "E", secondaryInputSplit[1],
                    secondaryInputSplit[2]);
            this.taskList.add(newTask);
            reply += newTask.announcement() + "\n";
            reply += newTask + "\n";
            return reply;
        } else {
            return "Invalid Task\n";
        }
    }

    /**
     * Method returns all task that has the matching word as desc.
     * @param desc
     */
    public String findTask(String desc) {
        findList.clear();

        String reply = "";
        for (Task task : taskList) {
            if (task.toString().contains(desc)) {
                findList.add(task);
            }
        }

        for (int i = 0; i < findList.size(); i++) {
            Task currentTask = findList.get(i);
            reply += (i + 1) + ". " + currentTask.toString() + "\n";
        }

        reply += "We have " + (findList.size()) + " matching tasks with the word "
                + desc + ".\n";

        return reply;
    }

    /**
     * Returns the number of tasks in the TaskList.
     * @return number of tasks.
     */
    public int getSize() {
        return this.taskList.size();
    }

    /**
     * Returns the ArrayList of tasks
     * @return Arraylist of tasks
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Gets a particular task at index.
     * @param index
     * @return Task at index value.
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }
}
