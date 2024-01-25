import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        String logo = "  ____        _     ____        _   \n"
                + " |  _ \\      | |   |  _ \\      | |  \n"
                + " | |_) | ___ | |__ | |_) | ___ | |_ \n"
                + " |  _ < / _ \\| '_ \\|  _ < / _ \\| __|\n"
                + " | |_) | (_) | |_) | |_) | (_) | |_ \n"
                + " |____/ \\___/|_.__/|____/ \\___/ \\__|\n";
        System.out.println("Hellooo! I'm \n" + logo);
        System.out.println("As I am a Chatbot, I therefore have no arms.");
        System.out.println("Knock knock, who's there? Definitely not BobBot!");
        System.out.println("Jokes aside, what can I do for you?");
        System.out.println("______________________________________");
        String command = reader.nextLine();
        while (!command.equals("bye")) {
            System.out.println(command);
            System.out.println("______________________________________");
            command = reader.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        reader.close();
    }
}