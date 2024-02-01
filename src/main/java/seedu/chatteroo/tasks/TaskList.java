package seedu.chatteroo.tasks;

import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> listStore = new ArrayList<>();

    public TaskList(ArrayList<Task> listStore) {
        this.listStore = listStore;
    }

    public void printTasks(int listCount) {
        for (int i = 0; i < listCount; i++) {
            int taskNum = i + 1;
            System.out.println(taskNum + ". " + listStore.get(i).toString());
        }
        System.out.println();
    }

    // function to mark task as done and print it
    public void markTaskAsDone(int taskNum) {
        listStore.get(taskNum - 1).markAsDone();
        System.out.println("Nice! I've marked this task as done:\n" + listStore.get(taskNum - 1).toString() + "\n");
    }

    //function to mark task as not done and print it
    public void markTaskAsNotDone(int taskNum) {
        listStore.get(taskNum - 1).markAsNotdone();
        System.out.println("OK, I've marked this task as not done yet:\n"
                + listStore.get(taskNum - 1).toString() + "\n");
    }

    //function to delete task and move remaining chatteroo.tasks up in the list
    public void deleteTask(int taskNum) {
        listStore.remove(taskNum - 1);
        System.out.println("Now you have " + listStore.size() + " chatteroo.tasks in the list.\n");
    }

    public int getTaskListSize() {
        return listStore.size();
    }

    public Task getTask(int index) {
        return listStore.get(index);
    }

    public void add(Task task) {
        listStore.add(task);
    }

}
