package luke;

import java.util.ArrayList;

//for CURRENT tasks only.
public class TaskList {

    private ArrayList<Task> tasks = new ArrayList<Task>();

    //fetch tasks from history
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds task to tasklist.
     *
     * @param task The task to be added.
     * @return Success message or error.
     */
    public String addTask(Task task) {
        String returnMessage = "";
        if (task != null) {
            tasks.add(task);
            returnMessage = "I helped you add task '" + task.getFullStatus() + "'. But do it yourself next time! Hmmph!" + "\n";
        }
        return returnMessage;
    }

    /**
     * Marks a task at index in the tasklist as done.
     *
     * @param index Index of task to be marked as done.
     * @return The completed task (now marked as done).
     * @throws TasklistException
     */
    public String markTask(int index) throws TasklistException {
        String errorMessage;
        String returnMessage;
        try {
            tasks.get(index).complete();
        } catch (IndexOutOfBoundsException e) {
            errorMessage = "Jeez, you really ought to give me a number I can work with... got that?\n"
                    + "[Item index exceeds history count]\n";
            throw new TasklistException(errorMessage);
        }
        returnMessage = "Good work, I guess.\n"
                + (index + 1) + "." + tasks.get(index).getFullStatus() + "\n";
        return returnMessage;
    }

    /**
     * Looks for a specified task in the tasklist using the given keyword.
     * Prints them out, as well as their position in the list (index + 1), if any are found.
     *
     * @param keyword The keyword to search for a task.
     * @return The tasks found.
     * @throws TasklistException
     */
    public String findTask(String keyword) throws TasklistException {
        String returnMessage = "Here... here you go!!!\n";
        String errorMessage = returnMessage;
        int num = 1;
        boolean found = false;
        for (Task task : tasks) {
            String taskName = task.getName();
            if (taskName.contains(keyword)) {
                found = true;
                returnMessage += num + ". " + task.getFullStatus() + "\n";
            }
            num += 1;
        }
        if (!found) {
            errorMessage += "[Nothing was found, however.]\n";
            throw new TasklistException(errorMessage);
        }
        return returnMessage;
    }

    /**
     * Prints a list of all current tasks and their statuses.
     *
     * @return A list of all tasks.
     * @throws TasklistException
     */
    public String listTasks() throws TasklistException {
        String returnMessage = "";
        int num = 1;
        if (tasks.size() == 0) {
            returnMessage = "Looks like you have way too much free time on your hands, huh.\n"
            + "[No items in list]\n";
            throw new TasklistException(returnMessage);
        }
        for (Task s : tasks) {
            if (s.isDone()) {
                returnMessage += num + "." + s.getFullStatus() + "\n";
            } else {
                returnMessage += num + "." + s.getFullStatus() + "\n";
            }
            num += 1;
        }
        return returnMessage;
    }

    /**
     * Deletes a task at index in the tasklist.
     *
     * @param index Index of task to be deleted.
     * @return Delete or error message.
     * @throws TasklistException
     */
    public String deleteTask(int index) throws TasklistException {
        String returnMessage;
        String errorMessage;
        String fullStatus;
        try {
            fullStatus = tasks.get(index).getFullStatus();
            tasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            errorMessage = "There's nothing there, dummy...\n"
                    + "[Tried to remove non-existent event]\n";
            throw new TasklistException(errorMessage);
        }
        returnMessage = "Fine! If that's what you really want...\n"
                + "[Removed " + fullStatus + "]\n";
        return returnMessage;
    }

    /**
     * Reminds the user of tasks they have yet to complete.
     *
     * @return The list of tasks a user has yet to complete.
     */
    public String remind() {
        String returnMessage = "Get to it!\n";
        int num = 1;
        for (Task s : tasks) {
            System.out.println("Task: " + s.getFullStatus() +  s.isDone);
            if (!s.isDone()) {
                returnMessage += num + "." + s.getFullStatus() + "\n";
                num += 1;
            }
        }
        if (num == 1) { //no new tasks
            returnMessage = "Looks like you have way too much free time on your hands, huh.\n"
                    + "[Nothing left to do!]\n";
        }
        return returnMessage;
    }

}
