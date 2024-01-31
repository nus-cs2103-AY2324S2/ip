import java.util.Scanner;

public class Duke {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        CinnamoRoll cin = new CinnamoRoll();
        cin.greet();
        cin.load_data();
        while(true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            }
            cin.respond(input);
        }
        cin.exit();
    }
}
