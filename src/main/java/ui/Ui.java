package ui;

import task.Task;

import java.util.Scanner;

public class Ui {
    private static final String DIVIDER = "---------------------------------------------------";
    private static final String BOTNAME = "TOBIAS";
    /**
     * Prints the welcome message of Tobias in console.
     * */
    public String helloPrinter() {
        String result = DIVIDER
                + System.lineSeparator()
                + "   Hello there! I'm " + BOTNAME
                + System.lineSeparator()
                + "   How can I be of service my lord ?"
                + System.lineSeparator()
                + DIVIDER;

        return result;
    }

    /**
     * Prints the farewell message of Tobias.
     * */
    public String goodbyePrinter() {
        assert DIVIDER != null && BOTNAME != null: "Either divider missing or bot name is missing";

        String result = DIVIDER
                + System.lineSeparator()
                + "I say this with a heavy heart but Goodbye my lover :("
                + System.lineSeparator()
                + "Hope to see you soon!"
                + System.lineSeparator()
                + DIVIDER;

        return result;
    }

    /**
     * Prints the task given and the total number of tasks in the list.
     *
     * @param task Task that is provided.
     * @param size Total number of tasks in the list now.
     * */
    public String addedTaskPrinter(Task task, int size) {
        assert task != null && size >= 0: "Either task is null or size is possibly negative";
        String result = DIVIDER
                + System.lineSeparator()
                + "Got it. I've added this task: "
                + System.lineSeparator()
                + task.taskPrinter()
                + System.lineSeparator()
                + "Now you have " + size + " tasks in the list!"
                + System.lineSeparator()
                + DIVIDER;

        return result;
    }

    /**
     * Prints a divider.
     * */
    public static String printDivider() {
        assert DIVIDER != null;

        return DIVIDER;
    }

    /**
     * Takes in the input given by user and returns it as a String
     *
     * @return String command of user input.
     * */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().trim();
    }
}
