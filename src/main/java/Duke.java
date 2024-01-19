import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello, I'm Baron. What can I do for you?");
        Scanner scanner = new Scanner(System.in);

        String input;
        List<String> storedText = new ArrayList<>();
        do {
            input = scanner.nextLine();
            switch (input) {
                case "list":
                    System.out.println("--------------------");
                    for (int i = 0; i < storedText.size(); i++) {
                        System.out.println((i + 1) + ". " + storedText.get(i));
                    }
                    System.out.println("--------------------");
                    break;
                default:
                    storedText.add(input);
                    System.out.println("--------------------");
                    System.out.println("Added: " + input);
                    System.out.println("--------------------");

            }
        } while (!input.equals("bye"));
        
        scanner.close();
        System.out.println("Bye, good riddance");
    }
}
