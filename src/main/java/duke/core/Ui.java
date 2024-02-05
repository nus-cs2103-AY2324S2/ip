package duke.core;

import java.util.Scanner;

/**
 * This class represents the component of MeanDuke that deals with all user inputs and outputs
 */
public class Ui {
    private static final String LOGO =
            " __       __  ________   ______   __    __          _______   __    __  __    __  ________\n"
                    + "/  \\     /  |/        | /      \\ /  \\  /  |        /       \\ /  |  /  |/  |  /  |/        |\n"
                    + "$$  \\   /$$ |$$$$$$$$/ /$$$$$$  |$$  \\ $$ |        $$$$$$$  |$$ |  $$ |$$ | /$$/ $$$$$$$$/\n"
                    + "$$$  \\ /$$$ |$$ |__    $$ |__$$ |$$$  \\$$ | ______ $$ |  $$ |$$ |  $$ |$$ |/$$/  $$ |__\n"
                    + "$$$$  /$$$$ |$$    |   $$    $$ |$$$$  $$ |/      |$$ |  $$ |$$ |  $$ |$$  $$<   $$    |\n"
                    + "$$ $$ $$/$$ |$$$$$/    $$$$$$$$ |$$ $$ $$ |$$$$$$/ $$ |  $$ |$$ |  $$ |$$$$$  \\  $$$$$/\n"
                    + "$$ |$$$/ $$ |$$ |_____ $$ |  $$ |$$ |$$$$ |        $$ |__$$ |$$ \\__$$ |$$ |$$  \\ $$ |_____\n"
                    + "$$ | $/  $$ |$$       |$$ |  $$ |$$ | $$$ |        $$    $$/ $$    $$/ $$ | $$  |$$       |\n"
                    + "$$/      $$/ $$$$$$$$/ $$/   $$/ $$/   $$/         $$$$$$$/   $$$$$$/  $$/   $$/ $$$$$$$$/\n";
    private static final String SPACER = "___________________________________________________________________________________________";
    private static final String INTRO = LOGO + SPACER + "\n" + "What do you want this time?\n" + SPACER;
    private static final String OUTRO = "Finally you're finished, thought you would never stop yapping.";
    private static final Scanner inputScanner = new Scanner(System.in);

    /**
     * Reads and returns the next line of input by the user as a String.
     */
    public static String readInput() {
        return inputScanner.nextLine();
    }

    /**
     * Prints the MeanDuke logo to screen.
     */
    public static void printLogo() {
        System.out.println(LOGO);
    }

    /**
     * Prints the spacer to screen.
     */
    public static void printSpacer() {
        System.out.println(SPACER);
    }

    /**
     * Prints the intro to be given when MeanDuke is initialised to screen.
     */
    public static void printIntro() {
        System.out.println(INTRO);
    }

    /**
     * Prints the outro when MeanDuke exits to screen.
     */
    public static void printOutro() {
        System.out.println(OUTRO);
    }

    /**
     * Prints the given message to screen.
     *
     * @param msg The message to be printed.
     */
    public static void printMessage(String msg) {
        System.out.println(msg);
    }

    /**
     * Prints the given error to screen.
     *
     * @param e The error to be printed.
     */
    public static void printError(Exception e) {
        System.out.println(e.getMessage());
    }
}
