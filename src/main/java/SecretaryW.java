import java.util.Scanner;
public class SecretaryW {
    public static void main(String[] args) {
        // Scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        // Array to store task items
        String[] tasklist = new String[100];

        // counter for number of task
        int count = 0;

        String line = "------------------------------------------\n";
        String greeting = "Hello! I'm SecretaryW\n" + "What can I do for you?\n";
        String farewell = "Bye. Hope to see you again soon!\n";

        // Greetings
        System.out.println(line + greeting + line);

        // Read user input in the loop
        while (true) {
            String userInput = scanner.nextLine();

            if (userInput.equals("bye")){
                break; // Exits loop
            } else if (userInput.equals("list")){
                System.out.println(line);
                if (count == 0) {
                    System.out.println("No tasks available");
                    System.out.println(line);
                } else {
                    for (int i = 0; i < count; i++) {
                        System.out.println(" " + (i + 1) + ". " + tasklist[i]);
                    }
                    System.out.println(line);
                }
            } else {
                // Add to tasklist
                tasklist[count] = userInput;
                count++;
                String echo = userInput + "\n";
                System.out.println(line + "added: " + echo + line);
            }
        }

        // Farewell
        System.out.println(line + farewell + line);
        scanner.close();
    }
}