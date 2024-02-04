package duke.core;

import java.util.Scanner;

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
    private static Scanner inputScanner = new Scanner(System.in);

    public static String readInput() {
        return inputScanner.nextLine();
    }
    public static void printLogo() {
        System.out.println(LOGO);
    }

    public static void printSpacer() {
        System.out.println(SPACER);
    }

    public static void printIntro() {
        System.out.println(INTRO);
    }

    public static void printOutro() {
        System.out.println(OUTRO);
    }

    public static void printMessage(String msg) {
        System.out.println(msg);
    }

    public static void printError(Exception e) {
        System.out.println(e.getMessage());
    }
}
