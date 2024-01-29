import java.util.Scanner;

public class Stille {
    public static void main(String[] args) {
        String openingMessage = "Hello! I'm Stille\n" + "What can I do for you?\n";
        String closingMessage = "Bye. Hope to see you again soon!\n";
        String input = "";
        String closeCommand = "bye";
        Scanner sc = new Scanner(System.in);

        System.out.println(openingMessage);

        while (true) {
            input = sc.nextLine();
            if (input.equals(closeCommand)) {
                break;
            }
            System.out.println(input);
        }

        System.out.println(closingMessage);



    }
}
