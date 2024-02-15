package tiny.lists;

import java.util.ArrayList;

import tiny.tasks.Task;

/**
 * Represents the tasklist to manage all the tasks.
 */
public class TaskList {
    protected ArrayList<Task> tasks = new ArrayList<>();

    public void add(Task task) {
        tasks.add(task);
    }

    public void delete(Integer ind) {
        tasks.remove(tasks.get(ind));
    }

    public Task get(Integer ind) {
        return tasks.get(ind);
    }

    public Integer size() {
        return tasks.size();
    }


    /**
     * Finds all macthing tasks in the task list.
     *
     * @param keyword Keyword to search for.
     * @return String of all of the tasks.
     */
    public String find(String keyword) {
        int listIndex = 1;
        String outputMessage = "Here are the matching tasks in your list:";
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).containsDescription(keyword)) {
                outputMessage += "\n";
                outputMessage += listIndex + ". " + tasks.get(i);
                listIndex++;
            }
        }

        if (outputMessage.equals("Here are the matching tasks in your list:")) {
            return "No matching results.";
        }

        return outputMessage;
    }

    /**
     * Lists out all the tasks in the list.
     *
     * @return String of all of the tasks.
     */
    public String list() {
        if (tasks.size() == 0) {
            return "You don't have any tasks!";
        }
        String output = "";
        for (int i = 0; i < tasks.size(); i++) {
            output += (i + 1) + ". " + tasks.get(i);
            output += "\n";
        }
        return output;
    }


    /**
     * Formats all the tasks into the correct format to save.
     *
     * @return ArrayList of tasks in the correct format to save.
     */
    public ArrayList<String> formatToSave() {
        ArrayList<String> toSave = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            toSave.add(tasks.get(i).formatToSave());
        }
        return toSave;
    }    
}
