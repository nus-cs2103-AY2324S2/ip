import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Hello! I'm Scribbles.");
        System.out.println("What can I do for you?\n");

        String input = sc.nextLine(); // takes in input from user
        while (!(input.equals("bye"))) {
            System.out.println(input);
            input = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
