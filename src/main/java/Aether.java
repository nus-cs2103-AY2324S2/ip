import java.util.Scanner;
public class Aether {

    private static void printHorizontalLine() {
        System.out.println(" _____________________________");
    }

    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//                System.out.println("Hello from\n" + logo);

        String chatbotName = "Aether";
        System.out.println("_____________________________");
        System.out.println("Hello! I'm " + chatbotName + "!");
        System.out.println("What can I do for you?");
        System.out.println("_____________________________");
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
            } else {
                System.out.println(input);
                printHorizontalLine();
            }


        }
        while (!input.equalsIgnoreCase("bye"));
        scanner.close();
    }
}

