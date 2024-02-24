package BadApple.task;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Handles Keep Track of tasks that are in the program.
 * Able to list out what tasks exist currently in the Drive.
 */
public class TaskList {
    public static ArrayList<Task> tasks = new ArrayList<>(); // Globally accessible Tasks in memory.

    public static String listTasks(BufferedReader bufferedReader) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        while (bufferedReader.ready()) {
            stringBuilder.append(bufferedReader.readLine()).append("\n");
        }
        return stringBuilder.toString();
    }

    /**
     * This method will use the locally stored runtime tasklist
     * and list all the Tasks during this run.
     * @return list of tasks in String form
     */
    public static String listTasks() {

        if (tasks.isEmpty()) {
            return "Hey there's nothing to do, join me for a picnic?";
        }

        StringBuilder stringBuilder = new StringBuilder();
        int index = 1;
        for (Task task : tasks) {
            stringBuilder.append(index).append(". ").append(task.toString()).append("\n");
            index++;
        }
        return stringBuilder.toString();
    }

    public static String filterTasks(String filter) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Task t : tasks) {
            if (t.brief().toLowerCase().contains(filter.toLowerCase())) {
                stringBuilder.append(t).append("\n");
            }
        }
        return stringBuilder.toString();
    }

    /**
     * Directly interacts with the tasklist to indicate task completion.
     * SIDE EFFECTS: marks the task specified.
     * @param b mark as done or not. true for done, false for not done
     * @param index of the task in the taskList.
     * @return A string that indicates to the user a task has been marked
     */
    public static String markTask(boolean b, int index) {
        Task t = tasks.get(index);
        return t.mark(b, index);
    }

    /**
     * Gives the user an indication that a task has been marked.
     * SIDE EFFECTS: marks the task specified.
     * @param b mark as done or not. true for done, false for not done
     * @param index the number of the task to delete.
     * @return A string that indicates to the user a task has been marked
     */
    public static String markTask(boolean b, String index) {
        int actualIndex = Integer.parseInt(index) - 1;
        return markTask(b, actualIndex);
    }

    public static String addTask(Task task) {
        String reply = "";
        int before = tasks.size();
        TaskList.tasks.add(task);
        int after = tasks.size();
        assert (after == before + 1);
        if (!Tracker.isSuppressingMsgs) {
            reply = Tracker.CustomMessages.randomMsg(task) + "\n" + task;
            System.out.println(reply);
        }
        return reply;
    }

    public static String addTask(String command, String query) {
        switch (command.toLowerCase()) {
            case "todo":
                return addTask(Todo.extractDetails(query));
            case "deadline":
                return addTask(Deadline.extractDetails(query));
            case "event":
                return addTask(Event.extractDetails(query));
            default:
                return "FAILED TO ADD TASK.";
        }
    }

    public static String removeTask(int i) {
        assert (!tasks.isEmpty());
        int before = tasks.size();
        if (before <= i || i < 0) {
            return "hey, you don't have such a task!";
        }
        String reply = "Kel has nuked " + TaskList.tasks.remove(i).brief() + "\n"
                + "You now have " + TaskList.tasks.size() + " tasks in your list";
        int after = tasks.size();
        assert(after < before);
        return reply;
    }

    public static String removeTask(String index) {
        int actualIndex = Integer.parseInt(index) - 1;
        return removeTask(actualIndex);
    }
}
