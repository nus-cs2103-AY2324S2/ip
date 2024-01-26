import java.util.Scanner;
public class HughJazz {
    public static void main(String[] args) {
        greeting();
        Scanner scn = new Scanner(System.in);
        String userInput;

        while (true) {
            userInput = scn.nextLine();

            if("bye".equalsIgnoreCase(userInput)) {
                break;
            }
            System.out.println(userInput);
        }
        ending();
        scn.close();
    }

    public static void greeting() {
        System.out.println("Hello! I'm HughJazz");
        System.out.println("What can I do for you?");
    }
    public static void ending() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
