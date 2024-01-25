import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("    _______________________________________________________");
        System.out.println("    Hello! I'm TALKTOMEORILLDIE");
        System.out.println("    What can I do for you?");
        System.out.println("    _______________________________________________________");

        Scanner inputs = new Scanner(System.in); //this is the scanner obj

        while (true) {
            String echo = inputs.nextLine(); //this is getting the input

            if (echo.equals("bye") || echo.equals("Bye")) {
                System.out.println("    _______________________________________________________");
                System.out.println("    Bye. Hope to see you again soon!");
                System.out.println("    _______________________________________________________");
                break;
            }

            System.out.println("    _______________________________________________________");
            System.out.println("    " + echo);
            System.out.println("    _______________________________________________________");
        }
    }
}
