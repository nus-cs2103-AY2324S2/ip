import java.util.Scanner;
public class Duke {
    private static String[] tasksArr = new String[100];
    private static int taskCount = 0;

    public static void main(String[] args) {

        String horiLine = "____________________________________________________________\n";

        String greet = "Hello! I'm Dino\n"
                + "What can I do for you?\n"
                + horiLine;
        System.out.println(greet);

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();


        while(!input.equalsIgnoreCase("bye")) {
            if (input.equalsIgnoreCase("list")) {
                System.out.println(horiLine);
                for (int i = 0; i < taskCount; i++) {
                    String item = i+1 + ". " + tasksArr[i];
                    System.out.println(item);
                }
                System.out.println(horiLine);
            } else {
                tasksArr[taskCount] = input;
                taskCount++;
                String echo = horiLine
                        + "added: " + input + "\n"
                        + horiLine;
                System.out.println(echo);
            }
            input = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }
}

