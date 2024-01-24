import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String DELIMITER = "______________________________________";

        System.out.println(DELIMITER);
        System.out.println("Hello! I'm Drew");
        System.out.println("What can I do for you?");
        System.out.println(DELIMITER);

        String input = sc.nextLine();
        String[] storage = new String[100];
        int listLength = 0;

        while (!input.equalsIgnoreCase("bye")){

            String reply = "";

            if (input.equalsIgnoreCase("list")) {
                for(int i = 0; i < listLength; i++) {
                    reply = reply + Integer.toString(i + 1) + ". " +  storage[i] + "\n";
                }
            } else {
                storage[listLength] = input;
                reply = String.format("added: %s\n", input);
                listLength ++;
            }

            System.out.println(DELIMITER);
            System.out.print(reply);
            System.out.println(DELIMITER);

            input = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
