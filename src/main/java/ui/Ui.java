/**
 * This is a UI Class.
 * It is responsible for printing corresponding outputs to the user.
 */

package ui;

import task.Task;

public class Ui {
    private final String LINE = "------------------------------------------";

    public Ui() {
    }

    /**
     * Prints welcome message for the user.
     */
    public void printWelcome() {
        System.out.println(LINE);
        System.out.println("I'm Balkan Bot\n" + "Jebem ti mat");
        System.out.println(LINE);
    }

    /**
     * Prints farewell message for the user.
     */
    public void printByeMessage() {
        System.out.println(LINE);
        System.out.println("Јебаћу ти бабицу");
        System.out.println(LINE);
    }

    /**
     * Prints the current state of the task list.
     *
     * @param listOutput task list
     */
    public void printTaskList(String listOutput) {
        System.out.println(LINE);
        System.out.println("Here is your list of tasks:");
        System.out.println(listOutput);
        System.out.println(LINE);
    }

    // Mark
    /**
     * Prints error message for when user neglected to provide any argument for the mark command.
     */
    public void printMarkEmptyNumberError() {
        System.out.println("OOPS!!! The number for the mark command cannot be empty.");
    }

    /**
     * Prints confirmation of the task being successfully marked.
     */
    public void printTaskMarked(String task) {
        System.out.println("Dje si pizda materina! I've marked this task as done:" + "\n" +
                task.toString());
    }

    /**
     * Prints error message for when user provided a non-integer argument for the mark command.
     */
    public void printMarkNANError() {
        System.out.println("OOPS!!! The input after the mark command has to be an integer.");
    }

    // Unmark
    /**
     * Prints error message for when user neglected to provide any argument for the unmark command.
     */
    public void printUnmarkEmptyNumberError() {
        System.out.println("OOPS!!! The number for the unmark command cannot be empty.");
    }

    /**
     * Prints confirmation of the task being successfully unmarked.
     */
    public void printTaskUnmarked(String task) {
        System.out.println("Baga-mi-as pula, it's been undone" + "\n" +
                task.toString());
    }

    /**
     * Prints error message for when user provided a non-integer argument for the unmark command.
     */
    public void printUnmarkNANError() {
        System.out.println("OOPS!!! The input after the unmark command has to be an integer.");
    }

    // Tasks
    /**
     * Prints confirmation that a task has been added to the task list.
     */
    public void printComplexTask(Task[] arr, int current) {
        System.out.println(LINE);
        System.out.println("Got it I've now added this task:");
        System.out.println(arr[current - 1].toString());
        System.out.println("Now you have " + current + " task(s) in the list.");
        System.out.println(LINE);
    }

    /**
     * Prints confirmation that a task has been deleted from the task list.
     */
    public void printDeletion(Task deletedTask, int current) {
        System.out.println(LINE);
        System.out.println("Noted. I've removed this task:");
        System.out.println(deletedTask.toString());
        System.out.println("Now you have " + current + " task(s) in the list.");
        System.out.println(LINE);
    }

    /**
     * Prints error explaining that the text file that contains the task list has some issue.
     */
    public void showLoadingError() {
        System.out.println("There was an issue loading the file stated in the file path");
    }

    public void printFindEmptyError() {
        System.out.println("OOPS!!! Either no keyword was provided or the keyword was more than one word.");
    }

    public void printFindOutput(Task[] findOutput) {
        System.out.println(LINE);
        System.out.println("Here are the matches found:");
        StringBuilder toPrint = new StringBuilder();
        for (int i = 0; i < findOutput.length; i++) {
            if (findOutput[i] == null) {
                break;
            } else {
                toPrint.append(i + 1).append(". ").append(findOutput[i].toString()).append("\n");
            }
        }
        System.out.println(toPrint);
        System.out.println(LINE);
    }
}
