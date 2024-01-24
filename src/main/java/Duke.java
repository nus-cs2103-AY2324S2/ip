import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Consumer;

public class Duke {
    
    /** Scanner for user input. */
    private static final Scanner input = new Scanner(System.in);
    
    private static boolean done = false;

    private static final HashMap<String, Command> commandMap = new HashMap<>();
    
    private static final ArrayList<Task> taskList = new ArrayList<>();
    
    /**
     * Prints a message to the terminal, decorated with the Louie icon. 
     * Printing an empty string just outputs the icon
     *
     * @param message The message to print.
     */
    public static void print(String message) {
        boolean isFirst = true;
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        for (String s : message.split("\n")) {
            sb.append("    ");
            if (isFirst) {
                sb.append("(>^.^<)");
                isFirst = false;
            } else {
                sb.append("       ");
            }
            sb.append(" ").append(s).append("\n");
        }
        sb.append("\n");
        System.out.print(sb);
    }
    
    public static void exit() {
        // Duke will exit at the end of the loop
        done = true;
    }


    public static void addCommand(String name, Consumer<String[]> executor) {
        commandMap.put(name, new Command(name, executor));
    }

    public static String taskStrings() {
        // here's your """""effectively final""""" value bro
        var numBox = new Object() {
            int num = 1;
        };
        return taskList.stream()
                .reduce(
                        new StringBuilder(),
                        (curr, acc) -> {
                            curr.append(numBox.num).append(".").append(acc.describe());
                            if (numBox.num < taskList.size()) {
                                curr.append("\n");
                            }
                            numBox.num++;
                            return curr;
                        },
                        StringBuilder::append)
                .toString();
    }
    public static void main(String[] mainArgs) {
        
        // initialisation
        Duke.addCommand("list", (args) -> Duke.print(
                "Here's what you've done today...\n" + taskStrings())
        );
        
        Duke.addCommand("bye", (args) -> {
            Duke.print("Ok, going to sleep...");
            Duke.exit();
        });
        
        Duke.addCommand("mark", (args) -> {
            int i = Integer.parseInt(args[1]) - 1;
            Task t = taskList.get(i);
            t.mark();
            Duke.print("CONGRATULATION!!!!!! you completed this task:\n" +
                    t.describe()
            );
        });

        Duke.addCommand("unmark", (args) -> {
            int i = Integer.parseInt(args[1]) - 1;
            Task t = taskList.get(i);
            t.unmark();
            Duke.print("CONGRATULATION!!!!!! you uncompleted this task:\n" +
                    t.describe()
            );
        });
        
        Duke.print("Hello, my name is... Louie!!!!\n" + 
                   "What can I do for you today?");
        while (!done) {
            String str = input.nextLine();
            String[] args = str.split(" ");
            if (commandMap.containsKey(args[0])) {
                commandMap.get(args[0]).run(args);
            } else {
                taskList.add(new Task(str));
                Duke.print("Ok, I've added a new todo item: " + str);
            }
        }
    }

}
