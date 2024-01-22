import java.util.Scanner;
public class Jayne {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String dash = "___________________________________";
        System.out.println(dash);
        System.out.println("Hello, I'm Jayne");
        System.out.println("What can I do for you?\n" + dash);

        while (true) {
            String input = scanner.nextLine();

            //enterd bye
            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!\n" + dash);
                break;
            }

            //Echo
            System.out.println(dash + "\n" + input + "\n" + dash);
        }

        scanner.close();
    }
}
