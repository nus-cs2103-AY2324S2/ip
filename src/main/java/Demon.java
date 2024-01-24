import java.util.Scanner;

public class Demon {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        System.out.println("Hello Master, I'm Demon 👿\nWhat can I do for you today?");
        String input = sc.nextLine();

        System.out.println("Entered: '" + input + "'");
        while (!input.equalsIgnoreCase("bye")) {
                System.out.println("---------------------------------------------------------");
                System.out.print(input + "\n");
                System.out.println("--------------------------------------------------------");
                input = sc.nextLine();
        }
        System.out.println("--------------------------------------------------------");
        System.out.println("Bye Master 👋, hope you had a great time, see you ♥!");
        System.out.println("--------------------------------------------------------");
        }
}
