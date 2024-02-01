import java.util.Scanner;
public class OedipusRex {
    public static void main(String[] args) {
        String line = "------------------------------------------\n";

        Scanner input = new Scanner(System.in);

        System.out.println(line +
                "Hello! I'm OedipusRex\n" +
                "What can I do for you?" + "\n" +
                line);

        while(true) {
            String command = input.nextLine();
            if(command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!" + "\n" +
                        line);
                break;
            } else {
                System.out.println(command);

            }
        }

        int i = 1;
    }
}