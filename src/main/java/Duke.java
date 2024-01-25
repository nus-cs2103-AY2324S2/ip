import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
public class Duke {
    private List<Task> todolist = new ArrayList<>();
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
                    for(Task todo : todolist) {
                        output = output.concat(i + ". " + todo.getTaskDescription() + "\n\t");
                        i++;
                    }
                    output = output.trim();
                    break;
                default:
                    Task newtask = new Task(command);
                    output = "added: " + command;
                    addItem(newtask);
            }

            if (!output.isEmpty()) {
                System.out.println(sep);
                System.out.println("\t" + output);
                System.out.println(sep);
            }

            command = scanner.nextLine();
        }
    }

    public void addItem(Task item) {
        todolist.add(item);
    }
}
