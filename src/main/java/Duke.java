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
        String input = scanner.nextLine();
        String command = input.split(" ")[0];
        int taskToMark = -1;

        while (!command.equals("bye")) {
            String output = "";
            switch (command) {
            case "list":
                int i = 1;
                for(Task todo : todolist) {
                    output = output.concat(i + ". " + todo.getTaskDescription() + "\n\t");
                    i++;
                }
                output = output.trim();
                break;
            case "mark":
                taskToMark = Integer.parseInt(input.split(" ")[1]) - 1;
                todolist.get(taskToMark).markAsDone(true);
                output = "Nice! I've marked this task as done:\n\t" + todolist.get(taskToMark).getTaskDescription();
                break;
            case "unmark":
                taskToMark = Integer.parseInt(input.split(" ")[1]) - 1;
                todolist.get(taskToMark).markAsDone(false);
                output = "OK, I've marked this task as not done yet:\n\t" + todolist.get(taskToMark).getTaskDescription();
                break;
            default:
                Task newtask = new Task(input);
                output = "added: " + input;
                addItem(newtask);
                break;
            }

            if (!output.isEmpty()) {
                System.out.println(sep);
                System.out.println("\t" + output);
                System.out.println(sep);
            }

            input = scanner.nextLine();
            command = input.split(" ")[0];
        }
    }

    public void addItem(Task item) {
        todolist.add(item);
    }
}
