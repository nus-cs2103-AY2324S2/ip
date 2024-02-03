package duke.ui;

import duke.task.TaskList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import duke.Duke;

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
    private Duke duke = null;
    private static Scanner scanner = null;
    private TaskList taskList = null;

    private Ui() {
        // break the initialisation into the initialization function of different classes
        horizontalLine = HorizontalLine.getInstance();
        currentState = UiState.ACTIVE_TALKING;
        duke = Duke.getInstance();
        scanner = new Scanner(System.in);
    }
    public static Ui getInstance() {
        if (instance == null) {
            instance = new Ui();
        }
        return instance;
    }

    /**
     *
     */
    public void initUi() {
        taskList = TaskList.getInstance();
        Greet();
    }

    public static String printTime(LocalDateTime localDateTime) {
        return localDateTime.format(PRINT_DATE_TIME_FORMATTER);
    }

    /**
     * @param output
     */
    public void speak(String output) {
        ToggleConversationState();
        System.out.println(output);
        ToggleConversationState();
    }

    /**
     *
     */
    private void Greet() {
        speak("Hello there, Liv here." + '\n' + "How may I help you?");
    }

    /**
     *
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
     * @return
     */
    public String StartListening() {
        // should be called from ACTIVE_TALKING STATE
        if (currentState == UiState.ACTIVE_LISTENING) ToggleConversationState();
        return scanner.nextLine();
    }

    /**
     *
     */
    public void EndListening() {
        if (currentState == UiState.ACTIVE_TALKING) ToggleConversationState();
    }

    /**
     *
     */
    public void EndSession() {
        // should be called from ACTIVE_LISTENING STATE, exception handling?
        ToggleConversationState();
        System.out.println("Hope you find my service helpful.");
        System.out.println("Till next time!");
        duke.ToggleActiveState();
    }

    public static void printHorizontalLine() {
        horizontalLine.printLine();
    }

    /**
     *
     */
    public void listTasks() {
        ToggleConversationState();
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= taskList.getNumOfTasks(); i++) {
            System.out.println(i + "." + taskList.getTask(i).toString());
        }
        ToggleConversationState();
    }
}
