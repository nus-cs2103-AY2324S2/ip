import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String horizontalLine = "_".repeat(60);
        System.out.println(horizontalLine);
        System.out.println("Hello! I'm Friday");
        System.out.println("What can I do for you?");
        System.out.println(horizontalLine);

        String userInput = sc.nextLine();
        while (!userInput.equals("bye")) {
            System.out.println(horizontalLine);
            System.out.println(userInput);
            System.out.println(horizontalLine);
            userInput = sc.nextLine();
        }
        System.out.println(horizontalLine);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(horizontalLine);
        sc.close();
    }
}
