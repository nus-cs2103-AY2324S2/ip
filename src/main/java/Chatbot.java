import java.util.Scanner;

public class Chatbot {

    private final String name;
    private final static String LINE = "----------------------------------";

    public Chatbot(String name) {
        this.name = name;
    }

    public void startChat() {
        Scanner scanner = new Scanner(System.in);

        greet();

        boolean isChatting = true;
        while (isChatting) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                bye();
                isChatting = false;
            } else {
                echo(input);
            }
        }
        scanner.close();
    }

    public void greet() {
        System.out.println(LINE);
        System.out.println("Hello! I'm " + name + ".");
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    public void bye() {
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    public void echo(String input) {
        System.out.println(LINE);
        System.out.println(input);
        System.out.println(LINE);
    }

}
