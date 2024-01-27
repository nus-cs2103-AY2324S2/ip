import java.io.*;
import java.util.*;

public class Duke {
    public static void main(String[] args) throws IOException {
        printBreak();
        System.out.println("Hello! I'm Klara");
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);
        String word = sc.nextLine();
        while (!word.equals("bye")) {
            printBreak();
            System.out.println(word);
            printBreak();
            word = sc.nextLine();
        }

        printBreak();
        System.out.println("Bye. Hope to see you again soon!");
        printBreak();
    }

    private static void printBreak() {
        String line = "____________________________________________________________";
        System.out.println(line);
    }


}
