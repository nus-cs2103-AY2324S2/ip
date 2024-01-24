import java.util.Scanner;
public class hirwan {
    public static void main(String[] args) {
        String logo = "I'm hirwan \n"
                + "_________________________________\n"
                + "what can I do for you? \n"
                + "_________________________________\n";

        System.out.println("Hello! " + logo);
        while(true) {
            Scanner scanner = new Scanner(System.in);
            String text = scanner.nextLine();
            if (text.equals("bye")) {
                break;
            }
            System.out.println(text);
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
