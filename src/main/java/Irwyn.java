import java.util.Scanner;
public class Irwyn {
    public static void main(String[] args) {
        String linebreak = "____________________________________________________________\n";
        String start = linebreak
                + "Hello! I'm Irwyn\n"
                + "What can I do for you?\n"
                + linebreak;
        System.out.println(start);

        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine();
        while (!userInput.isEmpty()) {
            if (userInput.equals("bye")) {
                break;
            }
            System.out.println(linebreak
                    + userInput + "\n"
                    + linebreak);
            userInput = input.nextLine();
        }
        String end = linebreak
                + "Bye. Hope to see you again soon!\n"
                + linebreak;
        System.out.println(end);
    }
}





