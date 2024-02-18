package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

import java.util.List;
import java.util.Scanner;

public class Ui {
    private static final String LOGO = "      ___          ___                     ___          ___          ___     \n"+
            "     /\\  \\        /\\  \\         ___       /\\__\\        /\\  \\        /\\__\\    \n"+
            "    /::\\  \\      /::\\  \\       /\\  \\     /::|  |      /::\\  \\      /::|  |   \n"+
            "   /:/\\:\\  \\    /:/\\:\\  \\      \\:\\  \\   /:|:|  |     /:/\\:\\  \\    /:|:|  |   \n"+
            "  /::\\~\\:\\  \\  /::\\~\\:\\  \\     /::\\__\\ /:/|:|__|__  /:/  \\:\\  \\  /:/|:|  |__ \n"+
            " /:/\\:\\ \\:\\__\\/:/\\:\\ \\:\\__\\ __/:/\\/__//:/ |::::\\__\\/:/__/ \\:\\__\\/:/ |:| /\\__\\\n"+
            " \\/__\\:\\/:/  /\\/__\\:\\/:/  //\\/:/  /   \\/__/~~/:/  /\\:\\  \\ /:/  /\\/__|:|/:/  /\n"+
            "      \\::/  /      \\::/  / \\::/__/          /:/  /  \\:\\  /:/  /     |:/:/  / \n"+
            "       \\/__/       /:/  /   \\:\\__\\         /:/  /    \\:\\/:/  /      |::/  /  \n"+
            "                  /:/  /     \\/__/        /:/  /      \\::/  /       /:/  /   \n"+
            "                  \\/__/                   \\/__/        \\/__/        \\/__/    \n";
    private static final List<String> GREETINGS = List.of(
            "Ah, there you are! Hello! Paimon wondered where you were! This is going to be so much fun, right?",
            "Ahoy there! It's great to see you! Paimon's hungry!",
            "Ah, Paimon missed you! It's been so long...",
            "Ad astra abyssosque, welcome to Paimon's house!",
            "Good morning, Traveler. Ah... what's it like out today? Paimon wants to hear your story."
    );

    private static final List<String> FAREWELLS = List.of(
            "Farewell, it was fun to meet you! Take care, see you later, and may you find many new treasures\n",
            "Farewell, until we meet again!\n",
            "Safe travels, and take care!\n",
            "Good luck! And don't spend all your Mora in one place.\n",
            "Adios!\n"
    );
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    private static final String HORIZONTAL_LINE =
            "___________________________________________________________________________";

    /**
     * @return String of Horizontal Line
     */
    public String showLine() {
        return HORIZONTAL_LINE;
    }

    /**
     * Print error messages.
     * @param errorMessage String error messages generated from Exceptions, etc...
     */
    public String showError(String errorMessage) {
        return errorMessage;
    }

    /**
     * Read the command input from console.
     * @return String command in string form to be sent for parsing and executing.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    public String readCommand(String command) {
        return command;
    }

    /**
     * Print ChatBot logo and greeting message.
     */
    public String showWelcome() {
        String outputString = LOGO;
        outputString += GREETINGS.get(1);
        return outputString;
    }

    /**
     * Close the initiated scanner and print farewell message.
     */
    public String showFarewell() {
        this.scanner.close();
        return FAREWELLS.get(1);
    }

    /**
     * Print the numbered full list of task in TaskList.
     * @param taskList TaskList to be displayed.
     */
    public String showTaskList(TaskList taskList) {
        String outputString = this.showTaskListStatus(taskList);
        int count = 1;
        for (Task task : taskList) {
            outputString += String.format("%d. %s\n", count, task);
            count++;
        }
        return outputString;
    }

    /**
     * Print success status when adding a task into TaskList.
     * @param task Task to be added.
     */
    public String showAddedTask(Task task) {
        return ("Got it. I've added this task: \n" + task.toString() + "\n");
    }

    /**
     * Print the number of tasks inside the TaskList.
     * @param taskList TaskList to be printed.
     */
    public String showTaskListStatus(TaskList taskList) {
        return String.format("Now you have %d tasks in the list.\n", taskList.size());
    }
    /**
     * Print success status when marking a task as Done status in TaskList.
     * @param task Task to be marked as Done.
     */
    public String showMarkedTask(Task task) {
        return ("Nice! I've marked this task as done: \n"
                + task.toString() + "\n");
    }
    /**
     * Print success status when unmarking a task into Undone status in TaskList.
     * @param task Task to be marked as Undone.
     */
    public String showUnmarkedTask(Task task) {
        return ("OK, I've marked this task as not done yet: \n"
                + task.toString() + "\n");
    }
    /**
     * Print success status when deleting a task from TaskList.
     * @param task Task to be deleted.
     */
    public String showDeletedTask(Task task) {
        return ("Noted. I've removed this task: \n"
                + task.toString() + "\n");
    }


    /**
     * Print the list of task containing a certain keyword.
     * @param taskListSearchResult List of Task to be printed.
     */
    public String showFindResult(List<Task> taskListSearchResult) {
        if (taskListSearchResult.isEmpty()) {
            return ("There are no matching tasks in your list.\n");
        }
        String outputString = ("Here are the matching tasks in your list:\n");
        int count = 1;
        for (Task task : taskListSearchResult) {
            outputString += String.format("%d. %s\n", count, task);
            count++;
        }
        return outputString;
    }

    /**
     * Show Date Loading Error.
     */
    public String showLoadingError() {
        return ("Loading data from file error.\n");
    }

    public String showArchiveSuccess(String fileName) {
        return String.format("Archive file %s successfully", fileName);
    }

    public String showArchiveList(List<String> archiveList) {
        if (archiveList.isEmpty()) {
            return "There is no archive currently";
        }
        String outputString = ("Here are the current archives:\n");
        int count = 1;
        for (String archiveName: archiveList) {
            outputString += String.format("%d. %s\n", count, archiveName.replace(".txt",""));
            count++;
        }
        return outputString;
    }

    public String showLoadArchiveSuccess(String fileName) {
        return String.format("Archive has been successfully loaded from %s", fileName);
    }
}
