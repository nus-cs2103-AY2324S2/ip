import java.util.Scanner;

public class GrumbleBug {
    public static void main(String[] args) {
        String starter = "GrumbleBug:"
                + "_______________________________________\n"
                + "Oh hey, adventurer.\n"
                + "I'm GrumbleBug.\n"
                + "What's your- you know what, I don't care.\n"
                + "_______________________________________\n"
                + "\n"
                + "To respond, type something. To leave, reply with 'bye'.\n";
        System.out.println(starter);
        Scanner sc = new Scanner(System.in);

        while (true) {
            String input1 = sc.nextLine();
            if (input1.equals("bye")) {
                System.exit(0);
            } else { // GrumbleBug will echo
                String echo = "GrumbleBug:"
                    + "_______________________________________\n"
                    + input1
                    + "\n_______________________________________\n";
                System.out.println(echo);
            }
        }
    }
}
