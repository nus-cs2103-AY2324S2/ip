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
    private static final DateTimeFormatter PRINT_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    private static Scanner scanner = null;

    private static Ui instance = null;
    private TaskList taskList = null;

    private Ui() {
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

    public String greet() {
        return "Loong time no see! Liv is here at your service!";
    }

    /**
     * Returns a string representing all existing tasks unfiltered.
     */
    public String listTasksReturnString() {
        taskList.setFilteredFalse();
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