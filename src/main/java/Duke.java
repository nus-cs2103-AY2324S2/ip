import java.util.Scanner;

public class Duke {


    public static void main(String[] args) {
        System.out.println(Constant.SEPERATOR);
        System.out.print("Hello! I'm Lex\nWhat can I do for you?\n");
        System.out.println(Constant.SEPERATOR);

        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            input = scanner.nextLine();

            if ("bye".equalsIgnoreCase(input.trim())) {
                System.out.println(Constant.SEPERATOR);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(Constant.SEPERATOR);
                break;
            }

            System.out.println(Constant.SEPERATOR);
            System.out.println(input);
            System.out.println(Constant.SEPERATOR);
        }

        scanner.close();
    }
}
