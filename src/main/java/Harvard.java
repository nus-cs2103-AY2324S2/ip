import java.util.Scanner;

public class Harvard {
    public static void main(String[] args) {
        String initial = "____________________________________________________________\n"
                + "Hello! I'm Harvard\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n";
        System.out.println(initial);

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String echoInput = scanner.nextLine();
            if (echoInput.equals("bye")) {
                break;
            }
            System.out.println("____________________________________________________________\n");
            System.out.println(echoInput);
            System.out.println("____________________________________________________________\n");
        }

        String exit = "____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________\n";
        System.out.println(exit);
    }
}
