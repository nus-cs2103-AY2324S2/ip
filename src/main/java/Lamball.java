
import java.util.Scanner;
public class Lamball {
    String indent = "    ____________________________________________________________\n";
    String logo = "    ____________________________________________________________\n" +
            "     Hello! I'm Lamball!\n" +
            "     What can I do for you?\n" +
            "    ____________________________________________________________\n";

    public Lamball() {

    }

    public void greetingMessage() {
        System.out.println(this.logo);
    }

    public boolean parse(String msg) {
        System.out.println(indent + "    " + msg + "\n" + indent);
        // Check if the user wants to exit
        if (msg.equalsIgnoreCase("bye")) {
            System.out.println("Duke: Goodbye! Exiting Duke.");
            return false;
        }
        return true;
    }
}