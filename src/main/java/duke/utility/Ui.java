package duke.utility;

import java.util.ArrayList;

import duke.task.Task;

/**
 * Class that represents a UserInterface that handles all user interactions.
 */
public class Ui {
    /**
     * Prints out a welcome message.
     *
     * @return String containing the welcome message.
     */
    public String showWelcome() {
        return "Hello! I'm Pengu\n" + "What can I do for you?\n"
                + "\nDid you know that the noise penguins make are called \"honks\"";
    }

    /**
     * Prints out a exit message.
     *
     * @return String containing the exit message.
     */
    public String showExit() {
        return "Bye. Hope to see you again soon! **HONK HONK**";
    }

    /**
     * Prints out the Tasks in the TaskList.
     *
     * @param taskList ArrayList of Tasks.
     * @return String containing all the tasks in the TaskLists.
     */
    public String showTaskListContents(ArrayList<Task> taskList) {
        String output;
        int storageSize = taskList.size();
        if (storageSize == 0) {
            output = "*Honk!* You currently have no tasks";
        } else {
            output = "*Honk!* Pengu has listed your current tasks below:";
            for (int k = 0; k < storageSize; k++) {
                int curr = k + 1;
                Task currTask = taskList.get(k);
                output = output + "\n" + curr + ". " + currTask.toString();
            }
        }
        return output;
    }

    /**
     * Prints out the marked Task Object.
     *
     * @param markedTask Task Object that was marked.
     * @return String containg the marked task.
     */
    public String showMarkedTask(Task markedTask) {
        return "*Honk!* Good Job!, Pengu has marked this task as done:\n" + markedTask.toString();
    }

    /**
     * Prints out the Task Object that was unmarked.
     *
     * @param unmarkedTask Task Object taht was unmarked.
     * @return String containing the unmarked task.
     */
    public String showUnmarkedTask(Task unmarkedTask) {
        return "*Honk!* Pengu has marked this task as not done yet:\n" + unmarkedTask.toString();
    }

    /**
     * Prints the Task that was deleted.
     *
     * @param deletedTask Task Object that was deleted.
     * @param taskStoreSize int of the size of TaskList.
     * @return String containing the deleted task.
     */
    public String showDeletedTask(Task deletedTask, int taskStoreSize) {
        String output = String.format("*Honk* Pengu has removed the following task:\n" + deletedTask.toString()
                + "\nNow you have %s tasks left", taskStoreSize);
        return output;
    }

    /**
     * Prints out the error message.
     *
     * @param errMessage error message.
     * @return String containing an error message.
     */
    public String showError(String errMessage) {
        return errMessage;
    }

    /**
     * Prints out the added Task.
     *
     * @param addedTask Task Object that was added.
     * @param taskStoreSize int of the size of the TaskList.
     */
    public String showAddedTask(Task addedTask, int taskStoreSize) {
        String output = String.format("*Honk! Honk!* Pengu has added this task:\n" + addedTask.toString()
                + "\nGet back to work! you have %s tasks in the list\n", taskStoreSize);
        return output;
    }

    /**
     * Prints out the tasks that contains the keyword.
     *
     * @param filteredList ArrayList of the Tasks that contain the keyword.
     * @param keyword String containing the keyword that was used to search within the TaskList.
     */
    public String showFilteredTask(ArrayList<Task> filteredList, String keyword) {
        String output;
        if (filteredList.size() == 0) {
            output = String.format("*Honk! no tasks with %s keyword found! "
                    + "Maybe try looking at the list command", keyword);
        } else {
            output = String.format("*Honk! Pengu has found the following tasks containing "
                    + "the %s keyword:", keyword);
            for (int k = 0; k < filteredList.size(); k++) {
                int curr = k + 1;
                Task currTask = filteredList.get(k);
                output = output + "\n" + curr + ". " + currTask.toString();
            }
        }
        return output;
    }

    /**
     * Prints out the Task that has been updated.
     *
     * @param taskToBeUpdated Tasks that was updated.
     * @return String containing the updated task.
     */
    public String showUpdatedTask(Task taskToBeUpdated) {
        return String.format("*Honk! Pengu has updated the following task:"
                + "\n %s", taskToBeUpdated.toString());
    }
}
