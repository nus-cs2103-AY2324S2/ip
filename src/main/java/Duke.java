import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Hello! I'm Blob.\nWhat can I do for you?\n");
        String message = sc.nextLine();

        while (!message.equalsIgnoreCase("bye")) {
            System.out.println(message + "\n");
            message = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!\n");
    }
}
