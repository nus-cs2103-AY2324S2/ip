package GUI;

/**
 * This is a UI Class.
 * It is responsible for printing corresponding outputs to the user.
 */

import task.Task;

public class GUIUi {

    public GUIUi() {
    }

    /**
     * Prints welcome message for the user.
     */
    public String printWelcome() {
        return "I'm Balkan Bot\n" + "Jebem ti mat";
    }

    /**
     * Prints farewell message for the user.
     */
    public String printByeMessage() {
        System.out.println("Јебаћу ти бабицу");
        return "I'm Balkan Bot\n" + "Jebem ti mat";
    }

    /**
     * Prints the current state of the task list.
     *
     * @param listOutput task list
     */
    public String printTaskList(String listOutput) {
        return "Here is your list of tasks:" + "\n" + listOutput;
    }

    // Mark

    /**
     * Prints error message for when user neglected to provide any argument for the mark command.
     */
    public String printMarkEmptyNumberError() {
        return "OOPS!!! The number for the mark command cannot be empty.";
    }

    /**
     * Prints confirmation of the task being successfully marked.
     */
    public String printTaskMarked(String task) {
        return "Dje si pizda materina! I've marked this task as done:" + "\n" +
                task.toString();
    }

    /**
     * Prints error message for when user provided a non-integer argument for the mark command.
     */
    public String printMarkNANError() {
        return "OOPS!!! The input after the mark command has to be an integer.";
    }

    // Unmark

    /**
     * Prints error message for when user neglected to provide any argument for the unmark command.
     */
    public String printUnmarkEmptyNumberError() {
        return "OOPS!!! The number for the unmark command cannot be empty.";
    }

    /**
     * Prints confirmation of the task being successfully unmarked.
     */
    public String printTaskUnmarked(String task) {
        return "Baga-mi-as pula, it's been undone" + "\n" +
                task.toString();
    }

    /**
     * Prints error message for when user provided a non-integer argument for the unmark command.
     */
    public String printUnmarkNANError() {
        return "OOPS!!! The input after the unmark command has to be an integer.";
    }

    // Tasks

    /**
     * Prints confirmation that a task has been added to the task list.
     */
    public String printComplexTask(Task[] arr, int current) {
        return "Got it I've now added this task:\n" + arr[current - 1].toString() +
                "\nNow you have " + current + " task(s) in the list.";
    }

    /**
     * Prints confirmation that a task has been deleted from the task list.
     */
    public String printDeletion(Task deletedTask, int current) {
        return "Noted. I've removed this task:\n" +
                deletedTask.toString() +
                "\nNow you have " + current + " task(s) in the list.";
    }

    /**
     * Prints error explaining that the text file that contains the task list has some issue.
     */
    public String showLoadingError() {
        return "There was an issue loading the file stated in the file path";
    }

    public String printFindEmptyError() {
        return "OOPS!!! Either no keyword was provided or the keyword was more than one word.";
    }

    public String printFindOutput(Task[] findOutput) {
        StringBuilder toPrint = new StringBuilder();
        for (int i = 0; i < findOutput.length; i++) {
            if (findOutput[i] == null) {
                break;
            } else {
                toPrint.append(i + 1).append(". ").append(findOutput[i].toString()).append("\n");
            }
        }
        return "Here are the matches found:\n" + toPrint;
    }

    /**
     * Prints the contents of the help function.
     *
     * @return String form of help function
     */
    public String printHelp() {
        return "Here is the list of commands:\n" +
                "1. bye: Shuts down the program and saves the tasks in the task list\n" +
                "2. find [key word]: Allows user to find tasks that match the provided one-word string\n" +
                "3. mark [positive integer]: Marks task at specified index\n" +
                "4. unmark [positive integer]: Unmarks task at specified index\n" +
                "5. todo [description]: Creates a new ToDo task with specified description\n" +
                "6. deadline [description] /by [date]: Creates a new Deadline task with specified description and\n" +
                "due date\n" +
                "7. eventdeadline [description] /from [date] /to [date]: Creates a new Deadline task with specified\n" +
                "description and start and end date\n" +
                "8. delete [positive integer]: Deletes task at specified index\n" +
                "9. help: Displays a list of all commands\n";
    }
}

