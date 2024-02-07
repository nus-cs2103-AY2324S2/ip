package duke.main;

import java.util.Scanner;

/**
 * Represents the user interface that outputs text to users and receives user text
 */



public class Ui {

    private TaskList taskList;
    private Scanner scanner;

    public Ui(TaskList taskList) {
        this.taskList = taskList;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints out a welcome message to the user when the program is first launched
     */

    public void printWelcomeMessage() {

        System.out.println("Hello! What tasks do you have?");
    }

    /**
     * Prints a confirmation message when a task in the task list has been marked as complete
     *
     * @param index  the index of the task in the task list that has been marked
     */
    public void printOnMark (int index) {
        System.out.println("Task marked as done. Good job!");
        System.out.println(taskList.get(index).toString());
    }

    /**
     * Prints a confirmation message when a task in the task list has been marked as not done
     *
     * @param index  the index of the task in the task list that has been unmarked
     */
    public void printOnUnmark(int index){
        System.out.println("Alright! Task marked as not done");
        System.out.println(taskList.get(index).toString());
    }

    /**
     * Prints a confirmation message when a new task is added to the task list, then prints the
     * total number of tasks in the task list
     */
    public void printOnAdd() {
        System.out.println("Added new task: ");
        System.out.println(taskList.get(taskList.getSize() - 1).toString());
        printTotal();
    }

    /**
     * Prints a confirmation message when a task at a specified index has been removed from the task list
     *
     * @param index  index of list element to be removed
     */
    public void printOnDelete(int index) {
        System.out.println("Removing task from list");
        System.out.println(taskList.get(index).toString());
    }
    /**
     * Iterates through the task list and prints out each task
     */
    public void printList() {
        for (int i = 0; i < taskList.getSize(); i++) {
            System.out.println((i + 1) + ": " + taskList.get(i).toString());
        }
    }

    /**
     * Prints the total number of tasks in the task list
     */
    public void printTotal() {
        System.out.println("current number of tasks: " + taskList.getSize());
    }

    /**
     * Prints an exit message when the program is closed
     */

    public void printExitMessage() {
        System.out.println("Goodbye!");
    }

    public void printList(TaskList list) {
        for (int i = 0; i < list.getSize(); i++) {
            System.out.println((i + 1) + ": " + list.get(i).toString());
        }
    }

    public void printOnFind(TaskList list) {
        System.out.println("Here are the matching tasks in your list: ");
        this.printList(list);
    }

    /**
     * Reads user input
     * @return  user input as a String
     */
    public String readLine() {
        return this.scanner.nextLine();
    }

    /**
     * Prints error message when an exception is thrown elsewhere in the program
     * @param exception  exception thrown during program execution
     */
    public void printException (Exception exception) {
        System.out.println(exception);
    }
}
