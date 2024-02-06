package duke;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();
    private Ui ui = new Ui();

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
                        tasks.add(td);
                        taskRead = td;
                        break;
                    case "D":
                        Deadline d = new Deadline(details[2], details[3]);
                        tasks.add(d);
                        taskRead = d;
                        break;
                    case "E":
                        Event e = new Event(details[2], details[3], details[4]);
                        tasks.add(e);
                        taskRead = e;
                        break;
                }
                if (details[1].equals("X")) {
                    taskRead.markAsDone();
                }
            }
        }
    }

    public TaskList() {

    }

    /**
     * Adds a task to the task list.
     *
     * @param t Task to be added.
     * @throws DukeException If the task details are missing or the command is not supported.
     */
    public void add(Task t) {
        tasks.add(t);
        int numItems = tasks.size();
        String sOrP = numItems == 1 ? "task" : "tasks";
        ui.showMessage("Got it. I've added this task:\n" + t.toString()
                + "\nNow you have " + numItems + " " + sOrP +" in the list.");
    }

    /**
     * Prints all tasks in the task list including task name, detail, and status.
     */
    public void list() {
        ui.showMessage("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            System.out.printf("%d. %s\n", i+1, t.toString());
        }
    }

    /**
     * Removes task of specified index from the task list.
     *
     * @param index Index of task on the list to be deleted
     * @throws DukeException If index is missing or invalid.
     */
    public void delete(int index) throws DukeException {
        try {
            Task removedTask = tasks.get(index - 1);
            tasks.remove(index - 1);
            int numItems = tasks.size();
            String sOrP = numItems == 1 ? "task" : "tasks";
            ui.showMessage("Noted. I've removed this task:\n" + removedTask.toString()
                    + "\nNow you have " + numItems + " " + sOrP + " in the list.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Invalid task number... count properly xx");
        }
    }

    public TaskList find(String keyword) {
        TaskList filteredList = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {
            Task currTask = tasks.get(i);
            if (currTask.getDescription().contains(keyword)) {
                filteredList.tasks.add(currTask);
            }
        }
        return filteredList;
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int i) throws DukeException {
        try {
            return tasks.get(i);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Invalid task number... count properly xx");
        }
    }
}
