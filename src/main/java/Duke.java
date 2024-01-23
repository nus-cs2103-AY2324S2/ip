import java.util.Scanner;
import java.util.ArrayList;
public class Duke {

    public static void main(String[] args) {
        String line = "__________________________________________________";
        System.out.println(line);
        System.out.println("Hello! I'm Floofy");
        System.out.println("What can I do for you?");
        System.out.println(line);
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();
        int counter = 0;
        while (true) {
            String userInput = scanner.nextLine(); // Read user input
            if ("bye".equalsIgnoreCase(userInput)) {
                System.out.println(line);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                scanner.close();
                break;
            } else if ("list".equalsIgnoreCase(userInput)) {
                System.out.println(line);
                for (String text: list) {
                    System.out.println(text);
                }
                System.out.println(line);
            } else {
                counter++;
                String numberedOutput = String.format("%d. %s", counter, userInput);
                list.add(numberedOutput);
                System.out.println(line);
                System.out.println("added: " + userInput);
                System.out.println(line);
            }
        }
    }
}
