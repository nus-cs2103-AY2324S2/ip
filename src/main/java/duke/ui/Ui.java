package duke.ui;

import duke.Duke;
import duke.task.TaskList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Represents ui component of Duke.
 * Manages interaction with the user.
 */
public class Ui {

    private enum UiState {
        /*
            things to be handled in ACTIVE_TALKING state:
            - print words to say
         */
        ACTIVE_TALKING,
        /*
            things to be handled in ACTIVE_LISTENING state:
            - take in user input
            - process the input
         */
        ACTIVE_LISTENING,
    }

    private static Ui instance = null;
    private static HorizontalLine horizontalLine = null;
    private UiState currentState = null;
    private static final DateTimeFormatter PRINT_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MMM dd YYYY HH:mm");
    private static Scanner scanner = null;
    private TaskList taskList = null;

    private Ui() {
        // break the initialization into the initialization function of different classes
        horizontalLine = HorizontalLine.getInstance();
        currentState = UiState.ACTIVE_TALKING;
        scanner = new Scanner(System.in);
    }
    public static Ui getInstance() {
        if (instance == null) {
            instance = new Ui();
        }
        return instance;
    }

    /**
     * Initialises ui.
     */
    public void initUi() {
        taskList = TaskList.getInstance();
    }

    public static String printTime(LocalDateTime localDateTime) {
        return localDateTime.format(PRINT_DATE_TIME_FORMATTER);
    }

    /**
     * Prints output wrapped between two horizontal lines.
     *
     * @param output String intended to be printed.
     */
    public void speak(String output) {
        ToggleConversationState();
        System.out.println(output);
        ToggleConversationState();
    }

    /**
     *
     */
    public String greet() {
        return "Loong time no see! Liv is here at your service!";
    }

    /**
     * Switch Ui state between ACTIVE_TALKING and ACTIVE_LISTENING.
     */
    public void ToggleConversationState() {

        horizontalLine.printLine();

        if (currentState == Ui.UiState.ACTIVE_TALKING) {
            currentState = UiState.ACTIVE_LISTENING;
            return;
        }

        if (currentState == UiState.ACTIVE_LISTENING) {
            currentState = Ui.UiState.ACTIVE_TALKING;
            return;
        }
    }

    /**
     * Starts listening to user input.
     *
     * @return User input as a string.
     */
    public String StartListening() {
        // should be called from ACTIVE_TALKING STATE
        if (currentState == UiState.ACTIVE_LISTENING) ToggleConversationState();
        return scanner.nextLine();
    }

    /**
     * Stops listening to user input.
     */
    public void EndListening() {
        if (currentState == UiState.ACTIVE_TALKING) ToggleConversationState();
    }

    /**
     * Ends Duke session.
     */
    public void EndSession() {
        // should be called from ACTIVE_LISTENING STATE, exception handling?
        ToggleConversationState();
        System.out.println("Hope you find my service helpful.");
        System.out.println("Till next time!");
        Duke.getInstance().ToggleActiveState();
    }
    /*
     public void EndSession() {
        // should be called from ACTIVE_LISTENING STATE, exception handling?
        ToggleConversationState();
        System.out.println("Hope you find my service helpful.");
        System.out.println("Till next time!");
        duke.ToggleActiveState();
    }
     */

    public static void printHorizontalLine() {
        horizontalLine.printLine();
    }

    public String listTasksReturnString() {
        taskList.unfilterTasks();
        String result = "";
        for (int i = 1; i <= taskList.getNumOfTasks(); i++) {
            result += (i + "." + taskList.getTask(i).toString()) + "\n";
        }
        return result;
    }

    /**
     * Lists all existing tasks.
     */
    public void listTasks() {
        taskList.unfilterTasks();
        ToggleConversationState();
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= taskList.getNumOfTasks(); i++) {
            System.out.println(i + "." + taskList.getTask(i).toString());
        }
        ToggleConversationState();
    }

    public String listFilteredTasksReturnString() {
        String result = "";
        for (int i = 1; i <= taskList.getNumOfFilteredTasks(); i++) {
            result += (i + "." + taskList.getTask(i).toString()) + "\n";
        }
        return result;
    }

    public void listFilteredTasks() {
        ToggleConversationState();
        System.out.println("Here are tasks found:");
        for (int i = 1; i <= taskList.getNumOfFilteredTasks(); i++) {
            System.out.println(i + "." + taskList.getTask(i).toString());
        }
        ToggleConversationState();
    }
}