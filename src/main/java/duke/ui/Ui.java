package duke.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import duke.task.TaskList;

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

    private static final DateTimeFormatter PRINT_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    private static Scanner scanner = null;

    private static Ui instance = null;
    private static HorizontalLine horizontalLine = null;
    private UiState currentState;

    private TaskList taskList = null;

    private Ui() {
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
        toggleConversationState();
        System.out.println(output);
        toggleConversationState();
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
    public void toggleConversationState() {

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
     * Returns a string representing all existing tasks unfiltered.
     */
    public String listTasksReturnString() {
        taskList.unfilterTasks();
        assert !TaskList.getInstance().isFiltered();
        String result = "";
        for (int i = 1; i <= taskList.getNumOfTasks(); i++) {
            result += (i + "." + taskList.getTask(i).toString()) + "\n";
        }
        return result;
    }

    /**
     * Returns a string representing all existing tasks after filtration.
     */
    public String listFilteredTasksReturnString() {
        assert TaskList.getInstance().isFiltered() : "TaskList is not filtered yet "
                + "Ui::listFilteredTasksReturnString is called";
        String result = "";
        for (int i = 1; i <= taskList.getNumOfFilteredTasks(); i++) {
            result += (i + "." + taskList.getTask(i).toString()) + "\n";
        }
        return result;
    }
}