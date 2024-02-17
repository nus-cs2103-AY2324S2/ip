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

    private void printHelper(int length) {
        for (int i = 0; i < length; i++) {
            String pos = String.valueOf(i + 1);
            System.out.println(pos + "." + this.todoList.get(i));
        }
    }

    public void printList() {
        int length = this.todoList.size();
        if (length == 0) {
            System.out.println("There is no task in your list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            printHelper(length);
        }
    }

    private void showResult(TaskList result, String keyword) {
        int length = result.todoList.size();
        if (length == 0) {
            System.out.println("There is no task in your list that contains the keyword '" + keyword + "'");
        } else {
            printHelper(length);
        }
    }

    private void updateHelper(Task t, Storage storage) {
        System.out.println(t);
        int length = this.todoList.size();
        if (length == 1) {
            System.out.println("Now you have " + length + " task in the list.");
        } else {
            System.out.println("Now you have " + length + " tasks in the list.");
        }
        storage.autoUpdate(this.todoList);
    }

    /**
     * List the whole TodoList after adding any tasks.
     * 
     * @param t
     * @param storage
     */

    public void listOverviewAfterAdding(Task t, Storage storage) {
        System.out.println("Got it. I've added this task:");
        updateHelper(t, storage);
    }

    /**
     * This function handles the change of marking of tasks.
     * It can mark task and unmark task.
     * 
     * @param userInput
     * @param storage
     */

    public void changeMarkingOfTask(String userInput, Storage storage) {
        String[] words = userInput.split("\\s+");
        int number = Integer.parseInt(words[1]);
        Task t = this.todoList.get(number - 1);
        if (words[0].equals("mark")) {
            t.markAsDone();
        } else {
            t.unmark();
        }
        storage.autoUpdate(this.todoList);
    }

    /**
     * This function handle the deletion of task in the TodoList.
     * 
     * @param userInput
     * @param storage
     */

    public void deleteTask(String userInput, Storage storage) {
        String[] words = userInput.split("\\s+");
        int number = Integer.parseInt(words[1]);
        Task t = this.todoList.remove(number - 1);
        System.out.println("Noted. I've removed this task:");
        updateHelper(t, storage);
    }

    public void findTask(String userInput, Storage storage) {
        String[] words = userInput.split("\\s+");
        String keyword = words[1];
        TaskList temp = new TaskList(new ArrayList<Task>());
        for (Task t : this.todoList) {
            if (t.getDescription().contains(keyword)) {
                temp.add(t);
            }
        }
        this.showResult(temp, keyword);
    }
}
