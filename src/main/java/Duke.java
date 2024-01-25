import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("________________________________________");

        System.out.println("Hello! I'm NextGenerationJarvis.");
        System.out.println("What can I do for you?");
        System.out.println("________________________________________\n");

        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        ArrayList<String> taskList = new ArrayList<>();

        while (!userInput.toLowerCase().equals("bye")) {
            
            if (userInput.toLowerCase().equals("list")) {
                System.out.println("\n________________________________________");

                for (int i = 1; i <= taskList.size(); i++) {
                    System.out.println(i + ". " + taskList.get(i - 1));
                }

                System.out.println("________________________________________\n");

            } else {
                taskList.add(userInput);

                System.out.println("\n________________________________________");
                System.out.println("added:" + userInput);
                System.out.println("________________________________________\n");
            }

            userInput = scanner.nextLine();
        }    

        System.out.println("\n________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("________________________________________\n");

        scanner.close();
    }
}
