import java.util.Scanner;

public class UserInterface {
    /*
    This class is for the the user interface.
    */

    Scanner scan = new Scanner(System.in);

    String greeting = "Hi! My name is HAL9000";
    String exit = "Bye! See ya soon";

    public void greet() {
        System.out.println(greeting);
    }
    
    public void exit() {
        System.out.println(exit);
    }

    public void poll() {

        boolean polling = true;
        CommandProcessor cmd = new CommandProcessor();

        while (polling) {
            String input = scan.nextLine();

            switch (input) {
                case "bye":
                    polling = false;
                    break;
            
                default:
                    cmd.processData(input);
                    break;
            }
        }

    }

    public void start() {
        greet();
        poll();
        exit();
    }
}
