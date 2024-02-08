package jiayou.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jiayou.JiayouException;
import jiayou.Storage;


/**
 * Represents a task list to store all the tasks of the chatbot.
 */
public class TaskList {
    private Storage storage;
    private ArrayList<Task> tasks = new ArrayList<Task>();

    /**
     * Links the given storage to the task list.
     *
     * @param storage the storage to be linked.
     */
    public void linkStorage(Storage storage) {
        this.storage = storage;
    }

    /**
     * Updates the linked text file.
     */
    public void updateStorage() {
        this.storage.saveToFile();
    }

    /**
     * Adds a new task to the task list.
     *
     * @param task the task to be added.
     * @return a response message.
     */
    public String addTask(Task task) {
        this.tasks.add(task);
        String response = ">w<Got it. I've added this task:\n";
        response += ("  " + task + "\n");
        response += ("Now you have " + getSize() + " tasks in the list.\n");
        updateStorage();
        return response;
    }

    /**
     * Deletes a new task from the task list.
     *
     * @param taskIds the task id(s) of the task(s) to be deleted.
     * @return a response message.
     */
    public String deleteTask(String ... taskIds) {
        String response = "";
        List<Integer> idsToInt = new ArrayList<>();
        try {
            for (String taskId : taskIds) {
                int idToInt = Integer.parseInt(taskId) - 1;
                if (idToInt < 0 || idToInt >= tasks.size()) {
                    throw new JiayouException("OOPS!!! The task you wanna delete doesn't exist. "
                            + "Please input a valid number!");
                }
                idsToInt.add(idToInt);
                }

            Collections.sort(idsToInt, Collections.reverseOrder());
            response += ">w<Noted. I've removed task(s):\n";

            for (int idToInt : idsToInt) {
                Task removedTask = tasks.remove(idToInt);
                response += ("  " + removedTask + "\n");
            }
            response += ("Now you have " + tasks.size() + " task(s) in the list.\n");
            updateStorage();
        } catch (NumberFormatException e) {
            System.out.println("OOPS!!! Please enter a valid task number!");
        } catch (JiayouException e) {
            System.out.println(e.getMessage());
        }
        return response;
    }

    /**
     * Marks the certain task in the list as done.
     *
     * @param input the task id of the task to get marked done.
     * @return a response message.
     */
    public String markTask(String input) {
        int taskId = Integer.parseInt(input);
        String response = "";
        Task task = this.tasks.get(taskId - 1);
        task.setStatus(true);
        response += ">w<Nice! I've marked this task as done:\n";
        response += ("  " + task);
        updateStorage();
        return response;
    }

    /**
     * Unmarks the certain task in the list as not done.
     *
     * @param input the task id of the task to get masked not done.
     * @return a response message.
     */
    public String unmarkTask(String input) {
        int taskId = Integer.parseInt(input);
        String response = "";
        Task task = tasks.get(taskId - 1);
        task.setStatus(false);
        response += "OK, I've marked this task as not done yet:";
        response += ("  " + task);
        updateStorage();
        return response;
    }

    /**
     * Searches for the tasks on a certain date and print them out.
     *
     * @param date the date to search.
     * @return a response message.
     */
    public String searchByDate(LocalDate date) {
        String response = "Here are the tasks on " + date + " in your list:\n";
        for (int i = 0; i < this.tasks.size(); i++) {
            Task task = this.tasks.get(i);
            if (task instanceof Event) {
                if (date.equals(((Event) task).getFrom())
                        | date.equals((((Event) task).getTo()))
                        | (date.isAfter((((Event) task).getFrom())) & date.isBefore(((Event) task).getTo()))) {
                    response += ((i + 1) + "." + task.toString() + "\n");
                }
            } else if (task instanceof Deadline) {
                if (date.equals(((Deadline) task).getByTime())) {
                    response += ((i + 1) + "." + task.toString() + "\n");
                }
            }
        }
        return response;
    }

    /**
     * Searches for the tasks with a certain keyword and print them out.
     *
     * @param keyword the keyword to search.
     * @return a response message.
     */
    public String searchByKeyword(String keyword) {
        String response = "Here are the tasks with the keyword " + keyword + " in your list:\n";
        for (int i = 0; i < this.tasks.size(); i++) {
            Task task = this.tasks.get(i);
            if (task.getDescription().contains(keyword)) {
                response += ((i + 1) + "." + task.toString() + "\n");
            }
        }
        return response;
    }

    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Prints all the tasks out.
     *
     * @return a response message.
     */
    public String printList() {
        String response = "Here are the tasks in your list:\n";
        for (int i = 0; i < this.tasks.size(); i++) {
            Task task = this.tasks.get(i);
            response += ((i + 1) + "." + task.toString() + "\n");
        }
        return response;
    }

    public ArrayList<Task> getList() {
        return this.tasks;
    }
}
