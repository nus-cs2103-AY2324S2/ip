import java.util.Objects;
import java.util.Scanner;
public class Duke {
    private String name;
    
    public Duke(String name) {
        this.name = name;
    }

    public void greet() {
        System.out.println("Hello! I'm " + this.name + "\nWhat can I do for you?\n");
    }

    public void exit(){
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    public void say(String message) {
        System.out.println(message);
    }

    public static void main(String[] args) {
        Duke chatbot = new Duke("Bob");
        chatbot.greet();
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        while (!Objects.equals(command, "bye")) {
            chatbot.say(command);
            command = scanner.nextLine();
        }
        chatbot.exit();
    }
}
