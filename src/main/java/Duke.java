import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
public class Duke {
    private List<String> todolist = new ArrayList<String>();
    private Scanner scanner = new Scanner(System.in);
    private static final String sep = "\t__________________________________________";
    public static void main(String[] args) {
        // hi
        System.out.println(sep);
        System.out.println("\tHello! I'm JOSEPH JOSHTUR!!!");
        System.out.println("\tWhat can I do for you?");
        System.out.println(sep);

        Duke duke = new Duke();
        duke.handleInput();

        // bye
        System.out.println(sep);
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println(sep);
    }

    public void handleInput() {
        String command = scanner.nextLine();
        while (!command.equals("bye")) {
            String output = new String();
            switch (command) {
                case "":
                    break;
                case "list":
                    int i = 1;
                    for(String todo : todolist) {
                        output = output.concat(i + ". " + todo + "\n\t");
                        i++;
                    }
                    break;
                default:
                    output = "added: " + command;
                    todolist.add(command);
            }

            if (!output.isEmpty()) {
                System.out.println(sep);
                System.out.println("\t" + output);
                System.out.println(sep);
            }

            command = scanner.nextLine();
        }
    }

    public void addItem(String item) {
        todolist.add(item);
    }
}
