import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("    Hello! I'm Hanxiao. \n  What can I do for you?");
        String inputs = sc.nextLine();
        while (!inputs.equals("bye")) {
            System.out.println("    "+inputs);
            inputs = sc.nextLine();
        }

        System.out.println("    Bye. Hope to see you again soon!");
    }
}
