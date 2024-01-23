import java.util.Scanner;

public class Duke {
    
    /** Scanner for user input. */
    private static final Scanner input = new Scanner(System.in);
    
    private static boolean done = false;

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
    public static void main(String[] mainArgs) {
        
        // initialisation
        Command.add("list", (args) -> Duke.print(Task.taskString()));
        
        Command.add("bye", (args) -> {
            Duke.print("Ok, going to sleep...");
            Duke.exit();
        });
        
        Duke.print("Hello, my name is... Louie!!!!\n" + 
                   "What can I do for you today?");
        while (!done) {
            String str = input.nextLine();
            if (Command.has(str)) {
                Command.run(new String[]{str});
            } else {
                Task.add(str);
                Duke.print("Ok, I've added a new todo item: " + str);
            }
        }
    }
}
