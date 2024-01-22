import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        System.out.println("    ____________________________________________________________");
        System.out.println("      Hello! I'm AndrewOng2066");
        System.out.println("      What can I do for you?");
        System.out.println("    ____________________________________________________________\n");
        Scanner sc = new Scanner(System.in);
        ArrayList<String> savedText = new ArrayList<>();
        String userInput = sc.nextLine().trim();
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                System.out.println("    ____________________________________________________________");
                for (int i = 0; i < savedText.size(); i++) {
                    System.out.println("      " + (i + 1) + ". " + savedText.get(i));
                }
                System.out.println("    ____________________________________________________________\n");
            } else {
                System.out.println("    ____________________________________________________________");
                System.out.println("      added: " + userInput);
                savedText.add(userInput);
                System.out.println("    ____________________________________________________________\n");
            }
            userInput = sc.nextLine().trim();
        }

        System.out.println("    ____________________________________________________________");
        System.out.println("      Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }
}
