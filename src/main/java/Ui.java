import tasks.Task;
import tasks.TaskType;

import java.util.Arrays;
import java.util.Scanner;

public class Ui {

    public static void greeting() {
        String greeting = "_______________________\n"
                + "what is up dawg! I'm Tony!\n"
                + "What can I do for you mate?\n"
                + "_________________________\n";
        System.out.println(greeting);
    }
    public static void goodbye() {
        String goodbye = "See ya later dawg!\n";
        line();
        System.out.println(goodbye);
        line();
        System.exit(0);
    }
    public static void line() {
        System.out.println("_______________________\n");
    }
}

