package ui;

import task.Task;

import java.util.Scanner;

/**
 * Handles the UI component of Buddy.
 */
public class Ui {

    /**
     * Prints welcome message when running Buddy.
     */
    public void showWelcome() {
        String logo =
                "                     ____            _     _       \n" +
                "                    |  _ \\          | |   | |      \n" +
                "                    | |_) |_   _  __| | __| |_   _ \n" +
                "                    |  _ <| | | |/ _` |/ _` | | | |\n" +
                "                    | |_) | |_| | (_| | (_| | |_| |\n" +
                "                    |____/ \\__,_|\\__,_|\\__,_|\\__, |\n" +
                "                                              __/ |\n" +
                "                                             |___/ ";
        showLine();
        System.out.println(logo);
        showLine();
        System.out.println("Heya bud!\nHow can I help?");
        showLine();
    }

    /**
     * Prints exit message when exiting Buddy.
     */
    public void showBye() {
        System.out.println("See ya!");
    }

    /**
     * Prints line break used in between messages.
     */
    public void showLine() {
        System.out.println("______________________________________________________________________");
    }

    /**
     * Reads input specified by user.
     *
     * @return User input.
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine().trim();
    }

    /**
     * Prints error message for when file does not load.
     */
    public void showLoadingError() {
        System.out.println("Error while loading file! Creating new data file!");
    }

    /**
     * Prints error message for when there is an error saving to file.
     */
    public void showSavingError() {
        System.out.println("Error while saving to file!");
    }

    /**
     * Prints task added into task list.
     *
     * @param task Task to be added.
     * @param n Number of task in task list.
     */
    public void showAdd(Task task, int n) {
        System.out.println("I've added the following task:");
        System.out.println(task);
        System.out.println("You have " + n + " tasks");
    }

    /**
     * Prints task deleted from task list.
     *
     * @param task Task to be deleted.
     * @param n Number of task in task list.
     */
    public void showDelete(Task task, int n) {
        System.out.println("I've deleted the following task:");
        System.out.println(task);
        System.out.println("You have " + n + " tasks remaining!");
    }

    /**
     * Prints message before listing tasks in task list.
     */
    public void showList() {
        System.out.println("Here you go buddy!:");
    }

    /**
     * Prints task that has been marked or unmarked.
     *
     * @param task Task to be marked or unmarked.
     */
    public void showMark(Task task) {
        System.out.println("I've updated the following task:");
        System.out.println(task);
    }

    /**
     * Prints error message.
     *
     * @param errorMsg Custom error message to be printed.
     */
    public void showError(String errorMsg) {
        System.out.println(errorMsg);
    }
}
