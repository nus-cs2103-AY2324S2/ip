import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;


public class Duke {
    private static String LINE_BREAK = "\n---------------------------------------------\n";


    private static ArrayList<String> tasks;

    private static void Squid() {
        tasks = new ArrayList<>();
    }

    private static void add(String task) {
        tasks.add(task);
        echo(String.format("Added %s. When are you going to add 'feed Squid'?", task));
    }

    private static void list() {
        echo("Here are your tasks. Sucks to be you, my only 2 tasks are eating and sleeping.");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("[ ] " + tasks.get(i));
        }
    }

    private static void hello() {
        String message = "Squid: HMm human. What do you want again?";
        System.out.println(LINE_BREAK + message + LINE_BREAK);
    }

    private static void bye() {
        String message = "You're done. Time for my food.";
        System.out.println(message);
    }

    private static void echo(String message) {
        System.out.println("Squid: " + message);
    }

    public static void main(String[] args) {
        Squid();
        hello();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            System.out.println(LINE_BREAK);
            if (Objects.equals(input, "bye")) {
                bye();
                System.out.println(LINE_BREAK);
                break;
            } else if (Objects.equals(input, "list")) {
                list();
            } else {
                add(input);
            }
            System.out.println(LINE_BREAK);

        }

    }
}
