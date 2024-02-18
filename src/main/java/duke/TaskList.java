package duke;

import java.time.format.DateTimeParseException;
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
        reply += currentTask.getTaskType() + currentTask.getStatus()
                + " " + currentTask.getTask() +"\n";

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
        reply += currentTask.getTaskType() + currentTask.getStatus()
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
        reply += currentTask.getTaskType() + currentTask.getStatus()
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
     * Adds new task to the TaskList.
     * @param tasktype
     * @param otherInfo
     */

    public String addTask(String tasktype, String otherInfo) {
        Task newTask;
        String reply = "";

        if (tasktype.equals("todo")) {
            newTask = new ToDo(otherInfo, "T");
            this.taskList.add(newTask);
            reply += newTask.getAnnouncement() + "\n";
            reply += newTask + "\n";
            return reply;
        } else if (tasktype.equals("deadline")) {
            String[] secondaryInputSplit = otherInfo.split(" /");
            newTask = new Deadline(secondaryInputSplit[0], "D", secondaryInputSplit[1]);
            this.taskList.add(newTask);
            reply += newTask.getAnnouncement() + "\n";
            reply += newTask + "\n";
            return reply;
        } else if (tasktype.equals("event")) {
            String[] secondaryInputSplit = otherInfo.split(" /");
            newTask = new Event(secondaryInputSplit[0], "E", secondaryInputSplit[1],
                    secondaryInputSplit[2]);
            this.taskList.add(newTask);
            reply += newTask.getAnnouncement() + "\n";
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

    /**
     * Method clears the tasklist.
     * @return String that notifies the user tasklist has been cleared.
     */
    public String clear() {
        this.taskList.clear();
        return "TaskList has been cleared!";
    }

    /**
     * Method returns the index of task required.
     * @param taskDesc
     * @return int index of task
     */
    public int getTaskIndex(String taskDesc) {
        for (int i = 0; i < this.taskList.size(); i++) {
            Task current = this.taskList.get(i);
            if (current.getTask().equals(taskDesc)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Method checks the type of task before updating it.
     * @param input
     * @return String notification of updated task.
     */
    public String postponeTask(String input) {
        String[] substrings = input.split(" /", 2);
        String taskDesc = substrings[0];

        int taskIndex = getTaskIndex(taskDesc);
        if (taskIndex == -1) {
            return "No such task to postpone.";
        }

        Task currentTask = getTask(taskIndex);

        if (currentTask instanceof ToDo) {
            return "ToDo task cannot be snoozed or postponed!";
        } else if (currentTask instanceof Deadline) {
            try {
                return postponeDeadline((Deadline) currentTask, substrings[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                return "Deadline requires a Date and Time!";
            }
        } else if (currentTask instanceof Event) {
            try {
                return postponeEvent((Event) currentTask, substrings[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                return "Event requires a start/end Date and Time!";
            }
        }
        return "No such task to postpone!";
    }

    private String postponeEvent(Event event, String dateTimeInput) {
        String[] inputSplit = dateTimeInput.split(" /", 2);
        try {
            String start = inputSplit[0];
            String end = inputSplit[1];
            return event.postpone(start, end);
        } catch (ArrayIndexOutOfBoundsException e) {
            return "Please input a start and end time "
                    + "or date with a / in front of both periods.\n";
        } catch (DateTimeParseException e) {
            return "Invalid DateTime Format. Please input as follows:\n"
                    + "dd-mm-yyyy hh:mm";
        }
    }

    private String postponeDeadline(Deadline deadline, String dateTimeInput) {
        try {
            return deadline.postpone(dateTimeInput);
        } catch (ArrayIndexOutOfBoundsException e) {
            return "Please input a date or time with a / in front.\n";
        } catch (DateTimeParseException e) {
            return "Invalid DateTime Format. Please input as follows:\n"
                    + "dd-mm-yyyy hh:mm";
        }
    }
}
