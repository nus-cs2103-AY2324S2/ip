import java.util.Scanner;

public class Duke {
    private String name;

    public Duke(String name) {
        this.name = name;
    }
    public String greetings() {
        String reply = "Hello! I'm " + this.name + ". \n"
                + "What can I do for you? \n";
        return reply;
    }

    public String quitApplication() {
        String reply = "Bye. Hope to see you again soon!";
        return reply;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter new name for your chatbot. \n");
        String name = scanner.nextLine();

        Duke duke = new Duke(name);
        System.out.println(duke.greetings());
        System.out.println(duke.quitApplication());
    }
}
