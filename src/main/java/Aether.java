import java.util.Scanner;
public class Aether {

    private static void printHorizontalLine() {
        System.out.println(" _____________________________");
    }

    public static void main(String[] args) {
        String chatbotName = "Aether";

        System.out.println("_____________________________");
        System.out.println("Hello! I'm " + chatbotName + "!");
        System.out.println("What can I do for you?");
        System.out.println("_____________________________");

        String[] tasks = new String[100];
        int taskCounter = 0;
        Scanner scanner = new Scanner(System.in);
        String input;

        do {
            input = scanner.nextLine();
            printHorizontalLine();
            if (input.equalsIgnoreCase("bye")) {
                printHorizontalLine();
                System.out.println("Bye. Hope to see you again soon!");
                printHorizontalLine();
            } else if (input.equalsIgnoreCase("study")) {
                System.out.println("Sure! What topic do you want to study?");
            } else if (input.equalsIgnoreCase("list")) {
                if (taskCounter > 0) {
                    System.out.println("Tasks:");
                    for (int i = 0; i < taskCounter; i++) {
                        System.out.println((i + 1) + "." + tasks[i].substring(6));
                    }
                } else {
                    System.out.println("No tasks added yet.");
                }
            } else {
                tasks[taskCounter] = "added: " + input;
                taskCounter++;
                System.out.println(tasks[taskCounter - 1]);

            }

            printHorizontalLine();


        } while (!input.equalsIgnoreCase("bye"));
        scanner.close();
    }
}

