import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.stream.Collectors;

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
        
        Duke.addCommand("todo", (args) -> {
            String str = Arrays.stream(args)
                    .skip(1)
                    .collect(Collectors.joining(" "));

            var t = new ToDo(str);
            Duke.print(String.format("Ok, I've added a new todo...\n  %s", t.describe()));
            taskList.add(t);
        });
        
        Duke.addCommand("deadline", (args) -> {
            int state = 0;
            StringBuilder by = new StringBuilder();
            StringBuilder name = new StringBuilder();
            
            for (String arg : args) {
                switch (state) {
                    case 0: // read command name
                        state = 1;
                        break;
                    case 1: // reading task name
                        if (arg.equals("/by")) {
                            state = 2;
                        } else {
                            if (!name.isEmpty()) {
                                name.append(" ");
                            }
                            name.append(arg);
                        }
                        break;
                    case 2: // reading by
                        if (!by.isEmpty()) {
                            by.append(" ");
                        }
                        by.append(arg);
                        break;
                }
            }
            var t = new Deadline(name.toString(), by.toString());
            Duke.print(String.format("Ok, I've added a new deadline...\n  %s", t.describe()));
            Duke.taskList.add(t);
            
        });
        
        Duke.addCommand("event", (args) -> {
            int state = 0;
            StringBuilder from = new StringBuilder();
            StringBuilder to = new StringBuilder();
            StringBuilder name = new StringBuilder();

            for (String arg : args) {
                switch (state) {
                    case 0: // read command name
                        state = 1;
                        break;
                        
                    case 1: // reading task name
                        if (arg.equals("/from")) {
                            state = 2;
                        } else {
                            if (!name.isEmpty()) {
                                name.append(" ");
                            }
                            name.append(arg);
                        }
                        break;
                        
                    case 2: // reading from
                        if (arg.equals("/to")) {
                            state = 3;
                        } else {
                            if (!from.isEmpty()) {
                                from.append(" ");
                            }
                            from.append(arg);
                        }
                        break;
                        
                    case 3: // reading to
                        if (!to.isEmpty()) {
                            to.append(" ");
                        }
                        to.append(arg);
                        break;
                }
            }

            var t = new Event(name.toString(), from.toString(), to.toString());
            Duke.print(String.format("Ok, I've added a new Event...\n  %s", t.describe()));
            Duke.taskList.add(t);
        });
        
        Duke.print("Hello, my name is... Louie!!!!\n" + 
                   "What can I do for you today?");
        while (!done) {
            String str = input.nextLine();
            String[] args = str.split(" ");
            if (commandMap.containsKey(args[0])) {
                commandMap.get(args[0]).run(args);
            } else {
                Duke.print("no matching command...");
            }
        }
    }

}
