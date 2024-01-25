import java.util.Scanner;
public class Duke{
    public static void main(String[] args) {

        String horiLine = "____________________________________________________________\n";

        String greet = "Hello! I'm Dino\n"
                + "What can I do for you?\n"
                +  horiLine;
        System.out.println(greet);

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();


        while(!input.equalsIgnoreCase("bye")) {
            String echo = horiLine
                    + input + "\n"
                    + horiLine;
            System.out.println(echo);
            input = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }
}

