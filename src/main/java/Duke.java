import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        // Initialization
        String name = "MR. WONG";
        say("Hey man. I'm " + name + "\nWhat can I do for you?");

        // Stores user items
        Storage list = new Storage();

        // Chatbot logic
        Parser parser = new Parser();
        Scanner scanner = new Scanner(System.in);
        String userInput = "";

        while (true) {
            userInput = scanner.nextLine();
            try {
                parser.parseCommand(userInput);
            } catch (IllegalArgumentException e) {
                say("sry idk what that means =(");
            } catch (EmptyCommandDescription e) {
                say("Oh no! This command description cannot be empty =(");
            }

            String[] commandInfo = parser.getCommandInfo();
            switch (commandInfo[0]) {
                case "BYE":
                    say("Bye bro!");
                    return;
                case "LIST":
                    say("Here are the tasks in your list:\n" + list.displayList());
                    break;
                case "MARK":
                    Task marked = list.markAsDone(Integer.parseInt(commandInfo[2]));
                    say("OK! I've marked this task as done:\n" + marked.toString());
                    break;
                case "UNMARK":
                    Task unmarked = list.unmark(Integer.parseInt(commandInfo[2]));
                    say("OK! I've unmarked this task:\n" + unmarked.toString());
                    break;
                case "TODO":
                    Task todo = new Todo(commandInfo[1]);
                    list.addTask(todo);

                    say("Got it. I've added this task:\n" + todo.toString() +
                            "\nNow you have " + list.getSize() + " tasks in the list.");
                    break;
                case "DEADLINE":
                    Task deadline = new Deadline(commandInfo[1], commandInfo[2]);
                    list.addTask(deadline);

                    say("Got it. I've added this task:\n" + deadline.toString() +
                            "\nNow you have " + list.getSize() + " tasks in the list.");
                    break;
                case "EVENT":
                    Task event = new Event(commandInfo[1], commandInfo[2], commandInfo[3]);
                    list.addTask(event);
                    say("Got it. I've added this task:\n" + event.toString() +
                            "\nNow you have " + list.getSize() + " tasks in the list.");
                    break;
                case "DELETE":
                    Task deleted = list.deleteTask(Integer.parseInt(commandInfo[2]));
                    say("OK! I've deleted this task:\n" + deleted.toString() +
                            "\nNow you have " + list.getSize() + " tasks in the list.");
                    break;
                default:
                    break;
            }

        }
    }

    public static void say(String msg) {
        String horizontal = "_________________________________";
        System.out.println(horizontal);
        System.out.println(msg);
        System.out.println(horizontal);
    }

    public static String displayList(ArrayList<String> userItems) {
        String d = "";
        for (int i = 1; i <= userItems.size(); ++i) {
            d += (i + ". " + userItems.get(i-1) + '\n');
        }
        return d;
    }
}