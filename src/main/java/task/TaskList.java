package task;

import java.util.ArrayList;

import storage.Storage;

/**
 * The class handling the Tasklist, a self-initiated data structure list.
 */
public class TaskList {
    protected ArrayList<Task> todoList;

    public TaskList(ArrayList<Task> todoList) {
        this.todoList = todoList;
    }

    public void add(Task t) {
        this.todoList.add(t);
    }

    private String printHelper(int length) {
        String output = "";
        for (int i = 0; i < length; i++) {
            String pos = String.valueOf(i + 1);
            output += pos + ". " + this.todoList.get(i).toString() + '\n';
        }
        return output;
    }

    /**
     * Returns the tasks inside the TodoList.
     * 
     * @return
     */

    public String printList() {
        int length = this.todoList.size();
        if (length == 0) {
            return "Great! There is no task in your list.";
        } else {
            String output = "";
            output += "Here are the tasks in your list:\n";
            output += printHelper(length);
            return output;
        }
    }

    private String showResult(TaskList result, String keyword) {
        int length = result.todoList.size();
        if (length == 0) {
            return "Huh? Check again! There is no task in your list that contains the keyword '" + keyword + "'";
        } else {
            return printHelper(length);
        }
    }

    private String updateHelper(Task t, Storage storage) {
        String output = t.toString() + '\n';
        int length = this.todoList.size();
        if (length == 1) {
            output += "Now you have " + length + " task in the list.";
        } else {
            output += "Now you have " + length + " tasks in the list.";
        }
        storage.autoUpdate(this.todoList);
        return output;
    }

    /**
     * List the whole TodoList after adding any tasks.
     * 
     * @param t
     * @param storage
     */

    public String listOverviewAfterAdding(Task t, Storage storage) {
        String output = "Got it. I've added this task to your list:\n";
        output += updateHelper(t, storage);
        return output;
    }

    /**
     * This function handles the change of marking of tasks.
     * It can mark task and unmark task.
     * 
     * @param userInput
     * @param storage
     */

    public String changeMarkingOfTask(String userInput, Storage storage) {
        String[] words = userInput.split("\\s+");
        int number = Integer.parseInt(words[1]);
        Task t = this.todoList.get(number - 1);
        String output = "";
        if (words[0].equals("mark")) {
            output = t.markAsDone();
        } else {
            output = t.unmark();
        }
        storage.autoUpdate(this.todoList);
        return output;
    }

    /**
     * This function handle the deletion of task in the TodoList.
     * 
     * @param userInput
     * @param storage
     */

    public String deleteTask(String userInput, Storage storage, Storage archived) {
        String[] words = userInput.split("\\s+");
        int number = Integer.parseInt(words[1]);
        Task t = this.todoList.remove(number - 1);
        archived.updateOneTask(t);
        String output = "Noted. I've removed this task:\n";
        output += updateHelper(t, storage);
        return output;
    }

    /**
     * This function handles the find of tasks in the TodoList with gvien keywords.
     * 
     * @param userInput
     * @param storage
     * @return The TodoList that satisfies the find function.
     */

    public String findTask(String userInput, Storage storage) {
        String[] words = userInput.split("\\s+");
        String keyword = words[1];
        TaskList temp = new TaskList(new ArrayList<Task>());
        for (Task t : this.todoList) {
            if (t.getDescription().contains(keyword)) {
                temp.add(t);
            }
        }
        return this.showResult(temp, keyword);
    }
}
