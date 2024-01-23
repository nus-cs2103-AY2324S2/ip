import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Bob");
        System.out.println("What can I do for you?\n");

        Scanner scanner = new Scanner(System.in);

        while(true){
            String input = scanner.nextLine();

            if ("bye".equals(input)) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            System.out.println(input + "\n");
        }

        scanner.close();
    }
}
