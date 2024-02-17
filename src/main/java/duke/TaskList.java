package duke;

import java.util.ArrayList;

/**
 * Represents a list that keeps track of tasks.
 * The TaskList is able to add, delete, and mark or unmark tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();
    private Ui ui = new Ui();

    /**
     * Creates a new TaskList object with existing task content.
     *
     * @param content Task content to be converted to Task objects and added to the list.
     */
    public TaskList(String content) {
        if (!content.equals("")) {
            String[] separatedContent = content.split("\n");
            for (int i = 0; i < separatedContent.length; i++) {
                String[] details = separatedContent[i].split("\\|");
                String taskType = details[0];
                Task taskRead = null;
                switch (taskType) {
                case "T":
                    ToDo td = new ToDo(details[2]);
                    taskList.add(td);
                    taskRead = td;
                    break;
                case "D":
                    Deadline d = new Deadline(details[2], details[3]);
                    taskList.add(d);
                    taskRead = d;
                    break;
                case "E":
                    Event e = new Event(details[2], details[3], details[4]);
                    taskList.add(e);
                    taskRead = e;
                    break;
                default:
                }
                if (details[1].equals("X")) {
                    taskRead.markAsDone();
                }
            }
        }
    }

    /**
     * Creates a new TaskList object.
     */
    public TaskList() {

    }

    /**
     * Adds a task to the task list.
     *
     * @param t Task to be added.
     */
    public void add(Task t) {
        taskList.add(t);
        int numItems = taskList.size();
        String sOrP = numItems == 1 ? "task" : "tasks";
        ui.showMessage("Got it. I've added this task:\n" + t.toString()
                + "\nNow you have " + numItems + " " + sOrP + " in the list.");
    }

    /**
     * Prints all tasks in the task list including task name, detail, and status.
     */
    public String list() {
        String result = "Here are the tasks in your list:\n";
        for (int i = 0; i < taskList.size(); i++) {
            Task t = taskList.get(i);
            int index = i + 1;
            result = result + index + ". " + t.toString() + "\n";
        }
        ui.showMessage(result);
        return result;
    }

    /**
     * Removes task of specified index from the task list.
     *
     * @param index Index of task on the list to be deleted
     * @throws DukeException If index is invalid.
     */
    public void delete(int index) throws DukeException {
        try {
            Task removedTask = taskList.get(index - 1);
            taskList.remove(index - 1);
            int numItems = taskList.size();
            String sOrP = numItems == 1 ? "task" : "tasks";
            ui.showMessage("Noted. I've removed this task:\n" + removedTask.toString()
                    + "\nNow you have " + numItems + " " + sOrP + " in the list.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Invalid task number... count properly xx");
        }
    }

    /**
     * Returns a new TaskList of tasks that match the keyword.
     *
     * @param keyword Keyword to find.
     * @return New TaskList of matching tasks.
     */
    public TaskList find(String keyword) {
        TaskList filteredList = new TaskList();
        for (int i = 0; i < taskList.size(); i++) {
            Task currTask = taskList.get(i);
            if (currTask.getDescription().contains(keyword)) {
                filteredList.taskList.add(currTask);
            }
        }
        return filteredList;
    }

    /**
     * Returns number of tasks in the list.
     *
     * @return Number of tasks.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Retrieves Task from list given the task index.
     *
     * @param i Index of task.
     * @return Task of index i in the list.
     * @throws DukeException If the index specified is invalid.
     */
    public Task get(int i) throws DukeException {
        try {
            return taskList.get(i);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Invalid task number... count properly xx");
        }
    }
}
