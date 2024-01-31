import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Ui {
    static final String spacer = "____________________________________________________________\n";
    static final String name = "Dude";
    public static void print(String text) {
        System.out.println(spacer + text + spacer);
    }

    public static void greeting() {
        print("Hello! I'm Dude\nWhat can I do for you?\n");
    }
    public static void goodbye() {
        print("Bye. Hope to see you again soon!\n");
    }
}
