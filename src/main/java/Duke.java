import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Hello! My name is Mitsuki, nice to meet you!\n" + "What can I do for you today?\n");
        String answer = "nil";
        while (!answer.equals("bye")) {
            answer = scan.next();
            if (!answer.equals("bye")) {
                System.out.println("Did you say: " + answer + "?");
            }
        }


        System.out.println("Bye! Hope to see you again soon!\n");
        System.exit(0);

    }
}
