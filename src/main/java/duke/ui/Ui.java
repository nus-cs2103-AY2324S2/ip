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
            "Farewell, it was fun to meet you! Take care, see you later, and may you find many new treasures",
            "Farewell, until we meet again!",
            "Safe travels, and take care!",
            "Good luck! And don't spend all your Mora in one place.",
            "Adios!"
    );
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    private static final String HORIZONTAL_LINE =
            "___________________________________________________________________________";

    /**
     * Print a Horizontal Line in console
     */
    public void showLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Print error messages.
     * @param errorMessage String error messages generated from Exceptions, etc...
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Read the command input from console.
     * @return String command in string form to be sent for parsing and executing.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Print ChatBot logo and greeting message.
     */
    public void showWelcome() {
        System.out.println(LOGO);
        System.out.println(GREETINGS.get(1));
    }

    /**
     * Close the initiated scanner and print farewell message.
     */
    public void showFarewell() {
        this.scanner.close();
        System.out.println(FAREWELLS.get(1));
    }

    /**
     * Print the numbered full list of task in TaskList.
     * @param taskList TaskList to be displayed.
     */
    public void showTaskList(TaskList taskList) {
        this.showTaskListStatus(taskList);
        int count = 1;
        for (Task task : taskList) {
            System.out.printf("%d. %s\n", count, task);
            count++;
        }
    }

    /**
     * Print success status when adding a task into TaskList.
     * @param task Task to be added.
     */
    public void showAddedTask(Task task) {
        System.out.println("Got it. I've added this task: \n" + task.toString());
    }

    /**
     * Print the number of tasks inside the TaskList.
     * @param taskList TaskList to be printed.
     */
    public void showTaskListStatus(TaskList taskList) {
        System.out.printf("Now you have %d tasks in the list.%n", taskList.size());
    }
    /**
     * Print success status when marking a task as Done status in TaskList.
     * @param task Task to be marked as Done.
     */
    public void showMarkedTask(Task task) {
        System.out.println("Nice! I've marked this task as done: \n" + task.toString());
    }
    /**
     * Print success status when unmarking a task into Undone status in TaskList.
     * @param task Task to be marked as Undone.
     */
    public void showUnmarkedTask(Task task) {
        System.out.println("OK, I've marked this task as not done yet: \n" + task.toString());
    }
    /**
     * Print success status when deleting a task from TaskList.
     * @param task Task to be deleted.
     */
    public void showDeletedTask(Task task) {
        System.out.println("Noted. I've removed this task: \n" + task.toString());
    }

    /**
     * Print the list of task containing a certain keyword.
     * @param taskListSearchResult List of Task to be printed.
     */
    public void showFindResult(List<Task> taskListSearchResult) {
        if (taskListSearchResult.isEmpty()){
            System.out.println("There are no matching tasks in your list.");
            return;
        }
        System.out.println("Here are the matching tasks in your list:");
        int count = 1;
        for (Task task : taskListSearchResult) {
            System.out.printf("%d. %s\n", count, task);
            count++;
        }
    }
}
