import java.util.Scanner;

public class Duke {
    static final String NAME = "jun jie";
    static final String INTRO_MSG = "hi bro, im " + NAME + "\nwhat you want me to do?";
    static final String EXIT_MSG = "ok see you bro";

    public static void print(String str) {
        System.out.println("-------------------------");
        System.out.println(str);
        System.out.println("-------------------------");
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        print(INTRO_MSG);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            }
            print(input);
        }
        print(EXIT_MSG);
    }
}
