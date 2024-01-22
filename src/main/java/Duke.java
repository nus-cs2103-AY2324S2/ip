import java.util.Scanner;
public class Duke {
    private static final String[] store = new String[100];
    private static int count = 0;

    public static void main(String[] args) {
        dialogue();

        Scanner scanner = new Scanner(System.in);
        String inputs;

        do {
            inputs = scanner.nextLine();
            check(inputs);

        } while (!inputs.equalsIgnoreCase("bye"));

        System.out.println("   ____________________________________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("   ____________________________________________________________");
    }

    public static void dialogue() {

        System.out.println("   ____________________________________________________________");
        System.out.println("    Hello! I'm QuantumBot");
        System.out.println("    What can I do for you?");
        System.out.println("   ____________________________________________________________");
    }

    public static void check(String input) {
        if (input.equalsIgnoreCase("bye")) {

        } else if (input.startsWith("list")) {
            list();

        } else {
            add(input);

        }
    }

    public static void add(String task) {
            store[count++] = task;

            System.out.println("   ____________________________________________________________");
            System.out.println("    added: " + task);
            System.out.println("   ____________________________________________________________");
    }

    public static void list() {

        System.out.println("   ____________________________________________________________");

        for (int i = 0; i < count; i++) {
            System.out.println("    " + (i + 1) + ". " + store[i]);
        }
        System.out.println("   ____________________________________________________________");
    }
}


/*
Level 1
public class Duke {
    public static void main(String[] args) {
        dialogue();

        Scanner scanner = new Scanner(System.in);
        String inputs;

        do {
            inputs = scanner.nextLine();
            if (!inputs.equalsIgnoreCase("bye")) {
                echo(inputs);
            }
        } while (!inputs.equalsIgnoreCase("bye"));

        System.out.println("   ____________________________________________________________");
        System.out.println("   Bye. Hope to see you again soon!");
        System.out.println("   ____________________________________________________________");
    }

    public static void dialogue() {
        System.out.println("   ____________________________________________________________");
        System.out.println("   Hello! I'm QuantumBot");
        System.out.println("   What can I do for you?");
        System.out.println("   ____________________________________________________________");
    }

    public static void echo(String input) {
        System.out.println("   ____________________________________________________________");
        System.out.println("   " + input);
        System.out.println("   ____________________________________________________________");
    }
}*/


/*Level 0
public class Duke {

    public static void main(String[] args) {

            /*String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        dialogue();
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void dialogue() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm BotYue");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }
}*/
