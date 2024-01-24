import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Drew");
        System.out.println("What can I do for you?");

        String input = sc.nextLine();
        while (!input.equalsIgnoreCase("bye")){
            System.out.println(input);
            input = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
