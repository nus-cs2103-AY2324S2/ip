
import java.util.Scanner;
public class Lamball {
    String indent = "    ____________________________________________________________\n";
    String welcomeMessage = "    ____________________________________________________________\n" +
            "     Hello! I'm Lamball!\n" +
            "     What can I do for you?\n" +
            "    ____________________________________________________________\n";
    String goodbyeMessage = "    ____________________________________________________________\n" +
            "     Goodbye!\n" +
            "    ____________________________________________________________\n";

    String[] tasks;
    public Lamball() {
        tasks = new String[100];
    }

    public void greetingMessage() {
        System.out.println(this.welcomeMessage);
    }

    public boolean parse(String msg) {
        String[] parts = msg.split(" ", 2);
        switch(parts[0]) {
            case "bye":
                System.out.println(goodbyeMessage);
                return false;
            default:
                System.out.println(indent + "    " + msg + "\n" + indent);
                return true;
        }
    }
}