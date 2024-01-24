import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int counter = 0;
        String[] l = new String[100];
        String horizontalLine = "_".repeat(60);
        System.out.println(horizontalLine);
        System.out.println("Hello! I'm Friday");
        System.out.println("What can I do for you?");
        System.out.println(horizontalLine);

        String userInput = sc.nextLine().trim();
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                System.out.println(horizontalLine);
                for (int i = 1; i <= counter; i++) {
                    System.out.println(i + ". " + l[i - 1]);
                }
                System.out.println(horizontalLine);
                userInput = sc.nextLine().trim();
            } else {
                l[counter] = userInput;
                counter++;
                System.out.println(horizontalLine);
                System.out.println("added: " + userInput);
                System.out.println(horizontalLine);
                userInput = sc.nextLine().trim();
            }

        }
        System.out.println(horizontalLine);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(horizontalLine);
        sc.close();
    }
}
