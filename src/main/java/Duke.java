import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Duke {
    private static final String LINE = "    ____________________________________________________________\n";
    private static final String INDENT = "    ";
    protected static final ArrayList<Task> tasks = new ArrayList<Task>();

    private static boolean isRunning = true;

    public enum Command {
        BYE("bye"),
        LIST("list"),
        MARK("mark"),
        UNMARK("unmark"),
        DELETE("delete"),
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event");
        private final String name;
        private static final Map<String, Command> COMMAND_MAP = new HashMap<>();

        private Command(String name) {
            this.name = name;
        }

        static {
            for (Command c: values()) {
                COMMAND_MAP.put(c.name, c);
            }
        }

        public static Command valueOfCommandName(String name) {
            return COMMAND_MAP.get(name);
        }
    }

    public static void main(String[] args) {
        Duke.hello();
        Scanner in = new Scanner(System.in);

        while (isRunning) {
            // get next command and arguments
            String input = in.nextLine().trim();
            String[] cmdArg = input.split(" ", 2); // [command, arguments]

            System.out.print(LINE);

            try {
                Parser.parseCommand(cmdArg);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }

            System.out.print(LINE);
        }
    }

    public static void hello() {
        String logo =
                "       :::   :::           :::        :::::::::       :::::::::       ::::::::::       :::::::::\n" +
                        "      :+:   :+:         :+: :+:      :+:    :+:      :+:    :+:      :+:              :+:    :+:\n" +
                        "      +:+ +:+         +:+   +:+     +:+    +:+      +:+    +:+      +:+              +:+    +:+\n" +
                        "      +#++:         +#++:++#++:    +#++:++#+       +#++:++#+       +#++:++#         +#++:++#:\n" +
                        "      +#+          +#+     +#+    +#+             +#+             +#+              +#+    +#+\n" +
                        "     #+#          #+#     #+#    #+#             #+#             #+#              #+#    #+#\n" +
                        "    ###          ###     ###    ###             ###             ##########       ###    ###\n\n";
        System.out.print(LINE + "    What's poppin' fam, it's ya boi\n\n" + logo +
                "    Hit me up with those deets and let's vibe together!\n" + LINE);
    }

    public static void bye() {
        System.out.print(INDENT + "Peace out, fam! Stay lit and keep those good vibes rollin'!\n");
        isRunning = false;
    }

    public static void listTasks() {
        System.out.println(INDENT + "I gotchu bruv. Here's your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(INDENT + (i + 1) + "." + tasks.get(i));
        }
    }

    public static void markTask(int i) {
        Task task = tasks.get(i - 1);
        task.markAsDone();
        System.out.println(INDENT + "Yasss King/Queen! This task is officially slayed:\n  " + INDENT + task);
    }

    public static void unmarkTask(int i) {
        Task task = tasks.get(i - 1);
        task.unmark();
        System.out.println(INDENT + "You forgor:\n  " + INDENT + task);
    }

    public static void deleteTask(int i) {
        Task task = tasks.get(i - 1);
        tasks.remove(i - 1);
        System.out.println(INDENT + "Zamn! This task never happened:\n  " + INDENT + task);
    }
}