import java.util.*;
public class Duke {

    public static void main(String[] args) throws CinnamoException {
        Scanner sc = new Scanner(System.in);
        CinnamoRoll cin = new CinnamoRoll();
        cin.greet();
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
