import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("    _______________________________________________________");
        System.out.println("     Hello! I'm TALKTOMEORILLDIE");
        System.out.println("     What can I do for you?");
        System.out.println("    _______________________________________________________");

        Scanner inputs = new Scanner(System.in); //this is the scanner obj

        String[] tasks = new String[100];
        int tasknum = 0;

        while (true) {
            String echo = inputs.nextLine(); //this is getting the input

            if (echo.equals("bye") || echo.equals("Bye")) {
                System.out.println("    _______________________________________________________");
                System.out.println("     Bye. Hope to see you again soon!");
                System.out.println("    _______________________________________________________");
                break;
            }

            if (echo.equals("list") || echo.equals("List")) {
                System.out.println("    _______________________________________________________");
                for (int i = 0; i < tasknum; i++) {
                    System.out.println("     " + (i + 1) + ". " + tasks[i]);
                }
                System.out.println("    _______________________________________________________");
            } else {
                System.out.println("    _______________________________________________________");
                System.out.println("     added:" + echo);
                tasks[tasknum] = echo;
                tasknum++;
                System.out.println("    _______________________________________________________");
            }
        }
    }
}
