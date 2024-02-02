package duke.utility;

import java.util.ArrayList;

import duke.task.Task;

public class Ui {
    /**
     * Prints out a welcome message.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Pengu\n" + "What can I do for you?\n" +
                "\nDid you know that the noise penguins make are called \"honks\"");
        this.showLine();
    }

    /**
     * Prints out a exit message.
     */
    public void showExit() {
        System.out.println("Bye. Hope to see you again soon! **HONK HONK**");
        this.showLine();
    }

    /**
     * Prints out a line divider String.
     */
    private void showLine() {
        System.out.println("―――――――――――――――――――――――――――――――――――");
    }

    /**
     * Prints out the Tasks in the TaskList.
     *
     * @param taskList ArrayList of Tasks.
     */
    public void showTaskListContents(ArrayList<Task> taskList) {
        int storageSize = taskList.size();
        if (storageSize == 0) {
            System.out.println("*Honk!* You currently have no tasks");
        } else {
            System.out.println("*Honk!* Pengu has listed your current tasks below:");
            for (int k = 0; k < storageSize; k++) {
                int curr = k + 1;
                Task currTask = taskList.get(k);
                System.out.println(curr + ". " + currTask.toString());
            }
        }
        this.showLine();
    }

    /**
     * Prints out the marked Task Object.
     *
     * @param markedTask Task Object that was marked.
     */
    public void showMarkedTask(Task markedTask) {
        System.out.println("*Honk!* Good Job!, Pengu has marked this task as done:\n" + markedTask.toString());
        this.showLine();
    }

    /**
     * Prints out the Task Object that was unmarked.
     *
     * @param unmarkedTask Task Object taht was unmarked.
     */
    public void showUnmarkedTask(Task unmarkedTask) {
        System.out.println("*Honk!* Pengu has marked this task as not done yet:\n" + unmarkedTask.toString());
        this.showLine();
    }

    /**
     * Prints the Task that was deleted.
     *
     * @param deletedTask Task Object that was deleted.
     * @param taskStoreSize int of the size of TaskList.
     */
    public void showDeletedTask(Task deletedTask, int taskStoreSize) {
        System.out.println(String.format("*Honk* Pengu has removed the following task:\n" + deletedTask.toString()
                + "\nNow you have %s tasks left", taskStoreSize));
        this.showLine();
    }

    /**
     * Prints out the error message.
     *
     * @param errMessage error message.
     */
    public void showError(String errMessage) {
        System.out.println(errMessage);
        this.showLine();
    }

    /**
     * Prints out the added Task.
     *
     * @param addedTask Task Object that was added.
     * @param taskStoreSize int of the size of the TaskList.
     */
    public void showAddedTask(Task addedTask, int taskStoreSize) {
        System.out.println(String.format("*Honk! Honk!* Pengu has added this task:\n" + addedTask.toString()
                + "\nGet back to work! you have %s tasks in the list\n"
                + "―――――――――――――――――――――――――――――――――――", taskStoreSize));
    }

    /**
     * Prints out the tasks that contains the keyword.
     *
     * @param filteredList ArrayList of the Tasks that contain the keyword.
     * @param keyword String containing the keyword that was used to search within the TaskList.
     */
    public void showFilteredTask(ArrayList<Task> filteredList, String keyword) {
        if (filteredList.size() == 0) {
            System.out.println(String.format("*Honk! no tasks with %s keyword found! "
                    + "Maybe try looking at the list command", keyword));
            this.showLine();
        } else {
            System.out.println(String.format("*Honk! Pengu has found the following tasks containing "
                    + "the %s keyword:", keyword));
            for (int k = 0; k < filteredList.size(); k++) {
                int curr = k + 1;
                Task currTask = filteredList.get(k);
                System.out.println(curr + ". " + currTask.toString());
            }
            this.showLine();
        }
    }
}
