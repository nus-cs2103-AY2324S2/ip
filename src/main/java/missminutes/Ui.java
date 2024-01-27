package missminutes;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static final String SEPARATOR = "-".repeat(60);

    private static final String MISS_MINUTES_LOGO =
            " __  __ _           __  __ _             _                  \n" +
            " |  \\/  (_)         |  \\/  (_)           | |              \n" +
            " | \\  / |_ ___ ___  | \\  / |_ _ __  _   _| |_ ___  ___    \n" +
            " | |\\/| | / __/ __| | |\\/| | | '_ \\| | | | __/ _ \\/ __| \n" +
            " | |  | | \\__ \\__ \\ | |  | | | | | | |_| | ||  __/\\__ \\\n" +
            " |_|  |_|_|___/___/ |_|  |_|_|_| |_|\\__,_|\\__\\___||___/  \n" +
            "                                                            \n";

    private static final Scanner stdin = new Scanner(System.in);

    public static void sayHello() {
        sendMsg("Hello! I'm \n" + MISS_MINUTES_LOGO
                + "What can I do for you");
    }

    public static void sayBye() {
        sendMsg("Bye. Hope to see you again soon!");
        stdin.close();
    }

    public static String getInput() {
        return stdin.nextLine();
    }

    public static void sendMsg(String body) {
        System.out.println(SEPARATOR);
        System.out.println(body);
        System.out.println(SEPARATOR);
    }
}
