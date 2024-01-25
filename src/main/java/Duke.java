import java.util.*;
public class Duke {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CinnamoRoll cin = new CinnamoRoll();
        cin.greet();
        while(true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            }
            String output = cin.respond(input);
            System.out.println(output);
        }
        cin.exit();
    }
}
