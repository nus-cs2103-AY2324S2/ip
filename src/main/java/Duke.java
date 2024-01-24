import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String welcome_message = "____________________________________________________________\n" +
        "Hello! I'm HASSTNT\n" +
        "What can I do for you?\n" +
                "____________________________________________________________\n";
        System.out.println(welcome_message);
        conversation();
    }
    public static void conversation(){
        Scanner scanner = new Scanner(System.in);
        String input;
        do {
            input = scanner.nextLine();
            System.out.println(input);
        } while (!input.equalsIgnoreCase("bye"));
        System.out.println("____________________________________________________________\n" +
                "Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n");
        scanner.close();
    }
    }
